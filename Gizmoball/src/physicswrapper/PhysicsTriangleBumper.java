package physicswrapper;

import physicsengine.Circle;
import physicsengine.LineSegment;
import model.TriangleBumper;

public class PhysicsTriangleBumper extends RotatablePhysicsGizmo
{
	public PhysicsTriangleBumper(TriangleBumper bumper)
	{
		double x = bumper.getX(), y = bumper.getY();
		int orientation = bumper.getOrientation();
		
		Point centre = new Point(x + 0.5, y + 0.5);
		Point p1 = rotate(new Point(x, y), centre, rot[orientation][0], rot[orientation][1]);
		Point p2 = rotate(new Point(x + 1, y), centre, rot[orientation][0], rot[orientation][1]);
		Point p3 = rotate(new Point(x, y + 1), centre, rot[orientation][0], rot[orientation][1]);
		
		objects.add(new LineSegment(p1.x, p1.y, p2.x, p2.y));
		objects.add(new LineSegment(p3.x, p3.y, p2.x, p2.y));
		objects.add(new LineSegment(p1.x, p1.y, p3.x, p3.y));
		
		objects.add(new Circle(p1.x, p1.y, 0));
		objects.add(new Circle(p2.x, p2.y, 0));
		objects.add(new Circle(p3.x, p3.y, 0));
	}
}