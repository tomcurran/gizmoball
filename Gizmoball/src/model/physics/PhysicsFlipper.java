package model.physics;

import java.util.Observable;
import java.util.Observer;

import model.gizmos.Flipper;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;

public class PhysicsFlipper extends RotatablePhysicsGizmo implements Observer
{
	
	private Flipper flipper;
	
	public PhysicsFlipper(Flipper flipper)
	{
		this.flipper = flipper;
		flipper.addObserver(this);
		build();
	}

	@Override
	public void update(Observable source, Object arg)
	{
		build();
	}
	
	
	private void build()
	{
		int orientation = flipper.getOrientation();
		int x = flipper.getX(), y = flipper.getY();
		double angle = flipper.getAngle();
		
		objects.clear();
		System.out.printf("Flipper angle %f\n", angle);
		
		Point pivot = new Point(x + 0.25, y + 0.25);
		Point centre = new Point(x + 1.0, y + 1.0);
		
		Point pivotcircle = rotate(pivot, centre, rot[orientation][0], rot[orientation][1]);
		Point endcircle = rotate(new Point(x + 0.25, y + 1.75), centre, pivot, orientation, angle);
		Point line1s = rotate(new Point(x, y + 0.25), centre, pivot, orientation, angle);
		Point line1e = rotate(new Point(x, y + 1.75), centre, pivot, orientation, angle);
		Point line2s = rotate(new Point(x + 0.5, y + 0.25), centre, pivot, orientation, angle);
		Point line2e = rotate(new Point(x + 0.5, y + 1.75), centre, pivot, orientation, angle);
		
		double m = flipper.getAngularMomentum();
		
//		if (m > 0)
//		{
//			objects.add(new RotatingCircle(pivotcircle.x, pivotcircle.y, 0.25, pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
//			objects.add(new RotatingCircle(endcircle.x, endcircle.y, 0.25, pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
//			objects.add(new RotatingWall(line1s.x, line1s.y, line1e.x, line1e.y, pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
//			objects.add(new RotatingWall(line2s.x, line2s.y, line2e.x, line2e.y, pivotcircle.x, pivotcircle.y, flipper.getAngularMomentum()));
//		}
//		else
//		{
			objects.add(new Circle(pivotcircle.x, pivotcircle.y, 0.25));
			objects.add(new Circle(endcircle.x, endcircle.y, 0.25));
			objects.add(new LineSegment(line1s.x, line1s.y, line1e.x, line1e.y));
			objects.add(new LineSegment(line2s.x, line2s.y, line2e.x, line2e.y));
		//}
	}
	
	
	private Point rotate(Point p, Point centre, Point pivot, int orientation, double angle)
	{
		//apply rotation for flipper angle
		p = rotate(p, pivot,Math.cos(angle), Math.sin(angle));
		
		//apply rotation for orientation
		p = rotate(p, centre, rot[orientation][0], rot[orientation][1]);
		
		return p;
	}
}
