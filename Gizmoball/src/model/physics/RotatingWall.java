package model.physics;

import model.physics.mit.LineSegment;
import model.physics.mit.Vect;

public class RotatingWall implements IPhysicsObject {
	
	private LineSegment line;
	private Vect centre;
	private double angularVelocity;

	/**
	 * A rotating wall, used for building flippers in the physics engine.
	 * 
	 * @param x1 - the start x of the line.
	 * @param y1 - the start y of the line.
	 * @param x2 - the end x of the line.
	 * @param y2 - the end y of the line. 
	 * @param cx - the centre x point.
	 * @param cy - the centre y point. 
	 * @param angularVelocity - the angular velocity of the wall. 
	 */
	public RotatingWall(double x1, double y1, double x2, double y2, double cx, double cy, double angularVelocity) {
		line = new LineSegment(x1, y1, x2, y2);
		centre = new Vect(cx, cy);
		this.angularVelocity = angularVelocity;
	}

	public PhysicsObjectType getType() {
		return PhysicsObjectType.RotatingWall;
	}

	public LineSegment getLine() {
		return line;
	}

	public Vect getCentre() {
		return centre;
	}

	public double getAngularVelocity() {
		return angularVelocity;
	}
}
