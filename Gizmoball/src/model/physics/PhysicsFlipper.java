package model.physics;

import java.util.Observable;
import java.util.Observer;

import model.RotatablePoint;
import model.gizmos.Flipper;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;

public class PhysicsFlipper extends PhysicsGizmo implements Observer {

	private Flipper flipper;

	/**
	 * Creates a physics representation of a flipper. 
	 * 
	 * @param flipper - the flipper to represent.
	 */
	public PhysicsFlipper(Flipper flipper) {
		this.flipper = flipper;
		flipper.addObserver(this);
		build();
	}

	@Override
	public void update(Observable source, Object arg) {
		build();
	}

	/**
	 * Builds the physicsflipper out of rotatable objects. 
	 */
	private void build() {
		int orientation = flipper.getOrientation();
		int x = flipper.getX(), y = flipper.getY();
		double angle = flipper.getAngle();

		objects.clear();

		RotatablePoint pivot = new RotatablePoint(x + 0.25, y + 0.25);
		RotatablePoint centre = new RotatablePoint(x + 1.0, y + 1.0);

		RotatablePoint pivotcircle = pivot.rotate(centre, orientation);
		RotatablePoint endcircle = rotate(
				new RotatablePoint(x + 0.25, y + 1.75), centre, pivot,
				orientation, angle);
		RotatablePoint line1s = rotate(new RotatablePoint(x, y + 0.25), centre,
				pivot, orientation, angle);
		RotatablePoint line1e = rotate(new RotatablePoint(x, y + 1.75), centre,
				pivot, orientation, angle);
		RotatablePoint line2s = rotate(new RotatablePoint(x + 0.5, y + 0.25),
				centre, pivot, orientation, angle);
		RotatablePoint line2e = rotate(new RotatablePoint(x + 0.5, y + 1.75),
				centre, pivot, orientation, angle);

		double m = flipper.getAngularMomentum();

		if (m > 0) {
			objects.add(new RotatingCircle(pivotcircle.x, pivotcircle.y, 0.25,
					pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
			objects.add(new RotatingCircle(endcircle.x, endcircle.y, 0.25,
					pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
			objects.add(new RotatingWall(line1s.x, line1s.y, line1e.x,
					line1e.y, pivotcircle.x, pivotcircle.y, flipper
							.getAngularMomentum()));
			objects.add(new RotatingWall(line2s.x, line2s.y, line2e.x,
					line2e.y, pivotcircle.x, pivotcircle.y, flipper
							.getAngularMomentum()));
		} else {
			objects.add(new Circle(pivotcircle.x, pivotcircle.y, 0.25));
			objects.add(new Circle(endcircle.x, endcircle.y, 0.25));
			objects.add(new LineSegment(line1s.x, line1s.y, line1e.x, line1e.y));
			objects.add(new LineSegment(line2s.x, line2s.y, line2e.x, line2e.y));
		}
	}

	private RotatablePoint rotate(RotatablePoint p, RotatablePoint centre,
			RotatablePoint pivot, int orientation, double angle) {
		// apply rotation for flipper angle
		p = p.rotate(pivot, angle);

		// apply rotation for orientation
		p = p.rotate(centre, orientation);

		return p;
	}
}
