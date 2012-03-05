package physicswrapper;

import physicsengine.Circle;
import physicsengine.LineSegment;
import model.TriangleBumper;

public class PhysicsTriangleBumper extends PhysicsGizmo
{
	public PhysicsTriangleBumper(TriangleBumper bumper)
	{
		double x = bumper.getX(), y = bumper.getY();
		double x1, x2, x3, y1, y2, y3;
		
		switch (bumper.getOrientation())
		{
			case 0:
				x1 = x3 = x;
				x2 = x + 1;
				y1 = y;
				y2 = y3 = y + 1;
				break;
				
			case 1:
				x1 = x + 1;
				x2 = x3 = x;
				y1 = y3 = y;
				y2 = y + 1;
				break;
				
			case 2:
				x1 = x3 = x + 1;
				x2 = x;
				y1 = y + 1;
				y2 = y3 = y;
				break;
				
			case 3:
				x1 = x;
				x2 = x3 = x + 1;
				y1 = y3 = y + 1;
				y2 = y;
				break;
				
			default:
				throw new IllegalStateException(String.format("%d is not a valid rotation value.", bumper.getOrientation()));
		}
		
		objects.add(new LineSegment(x1, y1, x2, y2));
		objects.add(new LineSegment(x3, y3, x2, y2));
		objects.add(new LineSegment(x1, y1, x3, y3));
		
		objects.add(new Circle(x1, y1, 0));
		objects.add(new Circle(x2, y2, 0));
		objects.add(new Circle(x3, y3, 0));
	}
}