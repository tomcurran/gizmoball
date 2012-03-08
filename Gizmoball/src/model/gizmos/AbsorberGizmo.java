package model.gizmos;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import model.Ball;
import model.GizmoType;
import model.IBoardItem;

public class AbsorberGizmo extends Gizmo implements Observer
{
	private Queue<Ball> balls;
	private Ball ejectingBall;
	
	public AbsorberGizmo(int x1, int y1, int x2, int y2)
	{
		super(x1, y1, x2 - x1, y2);
		
		balls = new LinkedList<Ball>();
		ejectingBall = null;
	}

	@Override
	public GizmoType getType()
	{
		return GizmoType.Absorber;
	}
	
	@Override
	public void trigger(IBoardItem item)
	{
		if (ejectingBall == null && balls.isEmpty() == false)
		{
			ejectingBall = balls.remove();
			ejectingBall.setVelocity(0, -50);
			ejectingBall.move(this.x + this.width - 0.35, this.y - 0.3);
		}
		else if (item instanceof Ball)
		{
			Ball ball = (Ball)item;
			
			if (ball != ejectingBall)
			{
				ball.move(this.x + this.width - 0.25, this.y + this.height - 0.35);
				ball.setVelocity(0, 0);
				
				if (!balls.contains(ball))
				{
					balls.add(ball);
					ball.addObserver(this);
				}
			}
		}
	}
	
	@Override
	public void update(Observable source, Object arg)
	{
		if (source == ejectingBall)
		{
			if (ejectingBall.getY() < this.getY() - 0.35)
			{
				ejectingBall = null;
			}
		}
	}
}
