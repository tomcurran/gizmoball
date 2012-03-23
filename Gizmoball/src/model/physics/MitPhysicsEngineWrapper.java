package model.physics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.Ball;
import model.Board;
import model.GizmoType;
import model.IBoardItem;
import model.IPhysicsEngine;
import model.gizmos.AbsorberGizmo;
import model.gizmos.CircleBumper;
import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.OuterWallsGizmo;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;

public class MitPhysicsEngineWrapper implements IPhysicsEngine, Observer
{
	public static double DEFAULT_MU = 0.025, DEFAULT_MU2 = 0.025, DEFAULT_GRAVITY = 25;
	
	public Board map;
	public List<PhysicsBall> balls;
	private List<Flipper> flippers;
	public Map<IGizmo, PhysicsGizmo> objects;
	private double mintime;
	private PhysicsBall collidingBall;
	private IPhysicsObject collidingObject;
	private IBoardItem collidingBoardItem;
	private double mu, mu2, gravity;
	
	
	public MitPhysicsEngineWrapper()
	{
		balls = new ArrayList<PhysicsBall>();
		objects = new HashMap<IGizmo, PhysicsGizmo>();
		flippers = new ArrayList<Flipper>();
		
		mu = DEFAULT_MU;
		mu2 = DEFAULT_MU2;
		gravity = DEFAULT_GRAVITY;
	}
	
	@Override
	public void initialise(Board map)
	{
		this.map = map;
		map.addObserver(this);

		objects.clear();
		balls.clear();
		flippers.clear();
		
		objects.put(new OuterWallsGizmo(map.getWidth(), map.getHeight()), new OuterWalls(map));
		
		for (IGizmo gizmo: map.getGizmos())
		{
			objects.put(gizmo, modelGizmo(gizmo));
			
			if (gizmo.getType() == GizmoType.Flipper)
			{
				flippers.add((Flipper)gizmo);
			}
		}
		
		for (Ball ball: map.getBalls())
		{
			balls.add(new PhysicsBall(ball));
		}
	}

	@Override
	public void calculateState(double timedelta)
	{ 
		calculateTimeUntilNextCollision();
		
		if (mintime < timedelta)
		{
			//we have a collision in this time step
			//do the reflection and move the balls
			collidingBall.reflect(collidingObject);
			collidingBall.getBall().trigger(collidingBoardItem);
			collidingBoardItem.trigger(collidingBall.getBall());
			
			moveBalls(mintime);
			moveFlippers(mintime);
			
			//continue for the rest of the time step
			calculateState(timedelta - mintime);
		}
		else
		{
			moveBalls(timedelta);
			moveFlippers(timedelta);
		}
	}
	
	
	private void calculateTimeUntilNextCollision()
	{
		mintime = Double.POSITIVE_INFINITY;
		collidingObject = null;
		
		for (int i = 0; i < balls.size(); i++)
		{
			PhysicsBall ball = balls.get(i);
			
			//collide against all gizmo objects in the scene
			for (Map.Entry<IGizmo, PhysicsGizmo> entry: objects.entrySet())
			{
				for (IPhysicsObject object: entry.getValue().getPhysicsObjects())
				{
					collide(ball, object, entry.getKey());
				}
			}
			
			//collide against the other balls
			for (int u = i; u < balls.size(); u++)
			{
				collide(ball, balls.get(u), balls.get(u).getBall());
			}
		}	
	}
	
	
	private void collide(PhysicsBall ball, IPhysicsObject object, IBoardItem item)
	{
		double time = ball.timeUntilCollision(object);
		
		if (time < mintime && !ball.getBall().getIsCaptured()) //&& ball.getIsStationary() == false)
		{
			mintime = time;
			collidingObject = object;
			collidingBall = ball;
			collidingBoardItem = item;
		}
	}
	
	
	private void moveBalls(double timedelta)
	{
		for (PhysicsBall ball: balls)
		{
			ball.applyFrictionAndGravity(timedelta/2, gravity, mu, mu2);
			ball.move(timedelta);
			ball.applyFrictionAndGravity(timedelta/2, gravity, mu, mu2);
		}
	}
	
	
	private void moveFlippers(double timedelta)
	{
		for (Flipper flipper: flippers)
		{
			if (flipper.getAngularMomentum() != 0)
				flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum() * timedelta);
		}
	}
	
	
	private PhysicsGizmo modelGizmo(IGizmo gizmo)
	{
		switch (gizmo.getType())
		{
			case CircleBumper:
				return new PhysicsCircleBumper((CircleBumper)gizmo);
				
			case SquareBumper:
				return new PhysicsSquareBumper((SquareBumper)gizmo);
				
			case TriangleBumper:
				return new PhysicsTriangleBumper((TriangleBumper)gizmo);
				
			case Flipper:
				return new PhysicsFlipper((Flipper)gizmo);
				
			case Absorber:
				return new PhysicsAbsorberGizmo((AbsorberGizmo)gizmo);
				
			default:
				throw new UnsupportedOperationException(String.format("Could not model gizmo: %s", gizmo.getType()));
		}
	}
	

	@Override
	public void update(Observable source, Object arg)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFriction(double mu, double mu2)
	{
		this.mu = mu;
		this.mu2 = mu2;
	}

	@Override
	public void setGravity(double gravity)
	{
		this.gravity = gravity;
	}
}
