package model.gizmos;

import model.Ball;
import model.GizmoType;
import model.IBoardItem;

public class AcceleratorGizmo extends CircleBumper
{
	public AcceleratorGizmo(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public GizmoType getType()
	{
		return GizmoType.AcceleratorGizmo;
	}

	@Override
	public void trigger(IBoardItem item)
	{
		if (item instanceof Ball)
		{
			Ball ball = (Ball)item;
			ball.multiplyVelocity(1.4);
		}
		
		super.trigger(item);
	}
}
