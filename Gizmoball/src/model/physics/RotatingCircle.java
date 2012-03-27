package model.physics;

import model.physics.mit.Circle;
import model.physics.mit.Vect;

public class RotatingCircle implements IPhysicsObject {
	
	private Circle circle;
	private Vect centre;
	private double angularVelocity;

	/**
	 * A rotating circle, for building flippers in the physics engine.
	 * 
	 * @param x - x point of the circle.
	 * @param y - y point of the circle.
	 * @param radius - radius of the circle.
	 * @param cx - centre x of the circle.
	 * @param cy - centre y of the circle.
	 * @param angularVelocity - the angularVelocity of the gizmo. 
	 */
	public RotatingCircle(double x, double y, double radius, double cx,
			double cy, double angularVelocity) {
		circle = new Circle(x, y, radius);
		centre = new Vect(cx, cy);
		this.angularVelocity = angularVelocity;
	}

	public PhysicsObjectType getType() {
		return PhysicsObjectType.RotatingCircle;
	}

	public Circle getCircle() {
		return circle;
	}

	public Vect getCentre() {
		return centre;
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}
}
