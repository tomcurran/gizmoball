package model.gizmos;

import model.GizmoType;

public class SpinnerGizmo extends Gizmo implements ISpinningGizmo
{
	private static final double ANGULAR_MOMENTUM = 2.0 * Math.PI;
	private double angle, angularMomentum;
	
	public SpinnerGizmo(int x, int y)
	{
		super(x, y, 2, 2);
		angularMomentum = ANGULAR_MOMENTUM;
	}
	
	public double getAngularMomentum()
	{
		return angularMomentum;
	}
	
	public void setAngle(double angle)
	{
		this.angle = angle;// % (2 * Math.PI);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	public double getAngle()
	{
		return angle;
	}

	@Override
	public GizmoType getType()
	{
		return GizmoType.SpinnerGizmo;
	}
	
	@Override
	public void doAction()
	{
		if (super.triggered)
		{
			angularMomentum = 0;
		}
		else
		{
			angularMomentum = ANGULAR_MOMENTUM;
		}
		
		this.setChanged();
		this.notifyObservers();
	}
}
