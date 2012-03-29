package model.physics;

import java.util.Observable;
import java.util.Observer;

import model.Ball;
import model.RotatablePoint;
import model.gizmos.SpinnerGizmo;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;

public class PhysicsSpinnerGizmo extends PhysicsGizmo implements Observer
{
	private SpinnerGizmo spinner;
	
	public PhysicsSpinnerGizmo(SpinnerGizmo spinner)
	{
		this.spinner = spinner;
		spinner.addObserver(this);
		update(null, null);
	}
	
	public void update(Observable source, Object arg)
	{
		int x = spinner.getX(), y = spinner.getY();
		double angle = spinner.getAngle();
		double cos = Math.cos(angle), sin = Math.sin(angle);

		objects.clear();

		RotatablePoint pivot = new RotatablePoint(x + 1.0, y + 1.0);

		RotatablePoint startcircle = new RotatablePoint(x + 1, y + 0.25).rotate(pivot, cos, sin);
		RotatablePoint endcircle = new RotatablePoint(x + 1, y + 1.75).rotate(pivot, cos, sin);
		RotatablePoint line1s = new RotatablePoint(x + 0.75, y + 0.25).rotate(pivot, cos, sin);
		RotatablePoint line1e = new RotatablePoint(x + 0.75, y + 1.75).rotate(pivot, cos, sin);
		RotatablePoint line2s = new RotatablePoint(x + 1.25, y + 0.25).rotate(pivot, cos, sin);
		RotatablePoint line2e = new RotatablePoint(x + 1.25, y + 1.75).rotate(pivot, cos, sin);

		double m = spinner.getAngularMomentum();

		if (m != 0) {
			objects.add(new RotatingCircle(startcircle.x, startcircle.y, 0.25, pivot.x, pivot.y, m));
			objects.add(new RotatingCircle(endcircle.x, endcircle.y, 0.25, pivot.x, pivot.y, m));
			objects.add(new RotatingWall(line1s.x, line1s.y, line1e.x, line1e.y, pivot.x, pivot.y, m));
			objects.add(new RotatingWall(line2s.x, line2s.y, line2e.x, line2e.y, pivot.x, pivot.y, m));
			//objects.add(new PhysicsBall(new Ball(startcircle.x, startcircle.y, 0.25, 1)));
			//objects.add(new PhysicsBall(new Ball(endcircle.x, endcircle.y, 0.25, 1)));
		} else {
			objects.add(new Circle(startcircle.x, startcircle.y, 0.25));
			objects.add(new Circle(endcircle.x, endcircle.y, 0.25));
			objects.add(new LineSegment(line1s.x, line1s.y, line1e.x, line1e.y));
			objects.add(new LineSegment(line2s.x, line2s.y, line2e.x, line2e.y));
		}
	}
}
