package model.gizmos;

import model.IBoardItem;

public class RightFlipper extends Flipper
{
	public RightFlipper(int x, int y, double angle)
	{
		super(x, y, 2, 2, angle, 0, 3.0 * Math.PI / 2);
		super.orientation = 1;
		//Removed the setting of the orientation, could this be screwing up
		//the physics flippers?
	}

	public RightFlipper(int x, int y)
	{
		//Changed this.
		this(x, y, 3.0 * Math.PI / 2.0);
		//Could this be what was screwing up the physics flippers?
		//this(x, y, 0);
	}

	@Override
	public void trigger(IBoardItem item)
	{
		super.trigger(item);
		
		if (getTriggeredState())
			this.setAngularMomentum(-Flipper.ANGULAR_MOMENTUM);
		else
			this.setAngularMomentum(Flipper.ANGULAR_MOMENTUM);
	}
}