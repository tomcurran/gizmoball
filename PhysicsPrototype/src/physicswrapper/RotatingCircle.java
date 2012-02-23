package physicswrapper;

import physics.Circle;
import physics.Vect;

public class RotatingCircle implements IPhysicsObject
{
	private Circle circle;
	private Vect centre;
	private double angularVelocity;
	
	public PhysicsObjectType getType()
	{
		return PhysicsObjectType.RotatingCircle;
	}
	
	public Circle getCircle()
	{
		return circle;
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
