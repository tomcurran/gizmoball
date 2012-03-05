package physicswrapper;

import model.CircleBumper;
import physicsengine.Circle;

public class PhysicsCircleBumper extends PhysicsGizmo
{
	public PhysicsCircleBumper(CircleBumper bumper)
	{
		objects.add(new Circle(0.5 + bumper.getX(), 0.5 + bumper.getY(), 1.0));
	}
}
