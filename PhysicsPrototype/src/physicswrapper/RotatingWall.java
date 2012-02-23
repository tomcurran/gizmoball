package physicswrapper;

import physics.LineSegment;
import physics.Vect;

public class RotatingWall implements IPhysicsObject
{
	private LineSegment line;
	private Vect centre;
	private double angularVelocity;
	
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
