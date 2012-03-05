package model;

public class RightFlipper extends Flipper
{
	public RightFlipper(int x, int y, double angle)
	{
		super(x, y, 2, 2, angle, 0, Math.PI / 2);
	}

	public RightFlipper(int x, int y)
	{
		this(x, y, 0);
	}

	@Override
	public void trigger(IBoardItem item)
	{
		super.trigger(item);
		
		if (getTriggeredState())
			this.angularMomentum = -Flipper.ANGULAR_MOMENTUM;
		else
			this.angularMomentum = Flipper.ANGULAR_MOMENTUM;
	}
}