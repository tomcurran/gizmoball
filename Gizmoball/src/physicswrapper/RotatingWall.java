package physicswrapper;

import physicsengine.LineSegment;
import physicsengine.Vect;

public class RotatingWall implements IPhysicsObject
{
	private LineSegment line;
	private Vect centre;
	private double angularVelocity;
	
	
	public RotatingWall(double x1, double y1, double x2, double y2, double cx, double cy, double angularVelocity)
	{
		line = new LineSegment(x1, y1, x2, y2);
		centre = new Vect(cx, cy);
		this.angularVelocity = angularVelocity;
	}
	
	public PhysicsObjectType getType()
	{
		return PhysicsObjectType.RotatingWall;
	}
	
	public LineSegment getLine()
	{
		return line;
	}
	
	public Vect getCentre()
	{
		return centre;
	}
	
	public double getAngularVelocity()
	{
		return angularVelocity;
	}
}
