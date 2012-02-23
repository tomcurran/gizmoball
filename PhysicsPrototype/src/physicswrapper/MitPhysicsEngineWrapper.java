package physicswrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import physics.LineSegment;
import physicsprototype.Ball;
import physicsprototype.GizmoMap;
import physicsprototype.IGizmo;
import physicsprototype.IPhysicsEngine;
import physicsprototype.SquareBumper;

public class MitPhysicsEngineWrapper implements IPhysicsEngine, Observer
{
	private static final double GRAVITY = 25, MU = 0.025, MU2 = 0.025; 
	private GizmoMap map;
	private List<PhysicsBall> balls;
	private Map<IGizmo, List<IPhysicsObject>> objects;
	private double mintime;
	private PhysicsBall collidingBall;
	private IPhysicsObject collidingObject;
	
	@Override
	public void initialise(GizmoMap map)
	{
		this.map = map;
		map.addObserver(this);
		
		balls = new ArrayList<PhysicsBall>();
		objects = new HashMap<IGizmo, List<IPhysicsObject>>();
		
		List<IPhysicsObject> walls = new ArrayList<IPhysicsObject>();
		walls.add(new LineSegment(0, 0, map.getWidth(), 0));
		walls.add(new LineSegment(map.getWidth(), 0, map.getWidth(), map.getHeight()));
		walls.add(new LineSegment(0, map.getHeight(), map.getWidth(), map.getHeight()));
		walls.add(new LineSegment(0, 0, 0, map.getHeight()));
		objects.put(null, walls);
		
		PhysicsModeler modeler = new PhysicsModeler();
		
		for (IGizmo gizmo: map.getGizmos())
		{
			objects.put(gizmo, modeler.modelGizmo(gizmo));
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
			moveBalls(mintime);
			
			//continue for the rest of the time step
			calculateState(timedelta - mintime);
		}
		else
		{
			moveBalls(timedelta);
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
			for (List<IPhysicsObject> gizmo: objects.values())
			{
				for (IPhysicsObject object: gizmo)
				{
					collide(ball, object);
				}
			}
			
			//collide against the other balls
			for (int u = i; u < balls.size(); u++)
			{
				collide(ball, balls.get(u));
			}
		}	
	}
	
	
	private void collide(PhysicsBall ball, IPhysicsObject object)
	{
		double time = ball.timeUntilCollision(object);
		
		if (time < mintime)
		{
			mintime = time;
			collidingObject = object;
			collidingBall = ball;
		}
	}
	
	
	private void moveBalls(double timedelta)
	{
		for (PhysicsBall ball: balls)
		{
			ball.applyFrictionAndGravity(timedelta/2, GRAVITY, MU, MU2);
			ball.move(timedelta);
			ball.applyFrictionAndGravity(timedelta/2, GRAVITY, MU, MU2);
		}
	}
	
	

	@Override
	public void update(Observable source, Object arg)
	{
		// TODO Auto-generated method stub
		
	}
}
