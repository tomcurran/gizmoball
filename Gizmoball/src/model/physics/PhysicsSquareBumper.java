package model.physics;

import model.gizmos.SquareBumper;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;

public class PhysicsSquareBumper extends PhysicsGizmo {
	
	/**
	 * Creates a physics representation of a square bumper.
	 * 
	 * @param bumper - the SquareBumper to represent. 
	 */
	public PhysicsSquareBumper(SquareBumper bumper) {
		double x = bumper.getX();
		double y = bumper.getY();

		objects.add(new LineSegment(x, y, x + 1, y)); // top
		objects.add(new LineSegment(x + 1, y, x + 1, y + 1)); // right
		objects.add(new LineSegment(x, y + 1, x + 1, y + 1)); // bottom
		objects.add(new LineSegment(x, y, x, y + 1)); // left

		objects.add(new Circle(x, y, 0)); // top left
		objects.add(new Circle(x + 1, y, 0)); // top right
		objects.add(new Circle(x + 1, y + 1, 0)); // bottom right
		objects.add(new Circle(x, y + 1, 0)); // bottom left
	}
}
