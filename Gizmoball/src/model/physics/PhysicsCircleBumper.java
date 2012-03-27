package model.physics;

import model.gizmos.CircleBumper;
import model.physics.mit.Circle;

public class PhysicsCircleBumper extends PhysicsGizmo {
	
	/**
	 * Creates a physics representation of a CircleBumper.
	 * 
	 * @param bumper - the CircleBumper to represent.
	 */
	public PhysicsCircleBumper(CircleBumper bumper) {
		objects.add(new Circle(0.5 + bumper.getX(), 0.5 + bumper.getY(), 0.5));
	}
}
