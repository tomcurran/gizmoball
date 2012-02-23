package physicswrapper;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physicsprototype.CircleBumper;
import physicsprototype.IGizmo;
import physicsprototype.SquareBumper;
import physicsprototype.TriangleBumper;

public class PhysicsModeler
{
	public List<IPhysicsObject> modelGizmo(IGizmo gizmo)
	{
		switch (gizmo.getType())
		{
			case CircleBumper:
				return modelCircleBumper((CircleBumper)gizmo);
				
			case SquareBumper:
				return modelSquareBumper((SquareBumper)gizmo);
				
			case TriangleBumper:
				return modelTriangleBumper((TriangleBumper)gizmo);
				
			case Flipper:
			case Absorber:
			default:
				throw new IllegalStateException(String.format("Could not model gizmo: %s", gizmo.getType()));
		}
	}
	
	public List<IPhysicsObject> modelCircleBumper(CircleBumper bumper)
	{
		List<IPhysicsObject> objects = new ArrayList<IPhysicsObject>();
		objects.add(new Circle(0.5 + bumper.getX(), 0.5 + bumper.getY(), 1.0));
		
		return objects;
	}
	
	public List<IPhysicsObject> modelSquareBumper(SquareBumper bumper)
	{
		List<IPhysicsObject> objects = new ArrayList<IPhysicsObject>();
		double x = bumper.getX(), y = bumper.getY();
		
		objects.add(new LineSegment(x, y, x + 1, y)); //top
		objects.add(new LineSegment(x + 1, y, x + 1, y + 1)); //right
		objects.add(new LineSegment(x, y + 1, x + 1, y + 1)); //bottom
		objects.add(new LineSegment(x, y, x, y + 1)); //left
		
		objects.add(new Circle(x, y, 0)); //top left
		objects.add(new Circle(x + 1, y, 0)); //top right
		objects.add(new Circle(x + 1, y + 1, 0)); //bottom right
		objects.add(new Circle(x, y + 1, 0)); //bottom left
		
		return objects;
	}
	
	public List<IPhysicsObject> modelTriangleBumper(TriangleBumper bumper)
	{
		List<IPhysicsObject> objects = new ArrayList<IPhysicsObject>();
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
		
		return objects;
	}
	
	/*public List<IPhysicsObject> modelFlipper(Flipper flipper)
	{
		
	}
	
	public List<IPhysicsObject> modelAbsorber(Absorber absorber)
	{
		
	}*/
}
