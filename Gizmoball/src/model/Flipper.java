package model;

import java.util.Observable;

public class Flipper extends Gizmo
{
	public static final double ANGULAR_MOMENTUM = Math.PI * 6.0;
	
	private int x, y;
	private double angle, startAngle, endAngle;
	private int width, height, orientation;
	protected double angularMomentum;

	public Flipper(int x, int y, int width, int height, double angle, double startAngle, double endAngle)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
		this.angle = angle;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
	}
	
	public int getHeight() {
		return height;
	}

	
	public int getWidth() {
		return width;
	}

	
	public void rotate() {
		if (orientation < 3) {
			orientation++;
		} else {
			orientation = 0;
		}
	}

	
	public int getOrientation() {
		return orientation;
	}

	
	public void setAngle(double angle)
	{
		this.angle = angle;
		
		if (angle >= getEndAngle())
		{
			this.angle = getEndAngle();
			angularMomentum = 0;
		}
		else if (angle <= getStartAngle())
		{
			this.angle = getStartAngle();
			angularMomentum = 0;
		}
	}

	
	public double getAngle()
	{
		return angle;
	}

	
	public double getStartAngle()
	{
		return startAngle;
	}

	
	public double getEndAngle()
	{
		return endAngle;
	}

	
	public double getAngularMomentum()
	{
		return angularMomentum;
	}

	@Override
	public GizmoType getType()
	{
		return GizmoType.Flipper;
	}
}