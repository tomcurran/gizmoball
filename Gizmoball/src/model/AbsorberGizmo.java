package model;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

public class AbsorberGizmo extends Gizmo implements Observer
{
	private Queue<Ball> balls;
	private Ball ejectingBall;
	
	public AbsorberGizmo(int x, int y, int width, int height)
	{
		super(x, y, width, height);
		
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
			ejectingBall.setVelocity(0, -25);
			ejectingBall.move(this.x + this.width - 0.25, this.y - 0.3);
		}
		else if (item instanceof Ball)
		{
			Ball ball = (Ball)item;
			
			if (ball != ejectingBall)
			{
				ball.move(this.x + this.width - 0.25, this.y + this.height - 0.25);
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
			if (ejectingBall.getY() < this.getY() - 0.25)
			{
				ejectingBall = null;
			}
		}
	}
}
