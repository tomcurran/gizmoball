package physicswrapper;

public class CollisionResult
{
	private PhysicsBall ball;
	private Object collidingObject;
	private double timeUntilCollision;
	
	public CollisionResult(PhysicsBall ball, Object collidingObject, double timeUntilCollision)
	{
		this.ball = ball;
		this.collidingObject = collidingObject;
		this.timeUntilCollision = timeUntilCollision;
	}
}
