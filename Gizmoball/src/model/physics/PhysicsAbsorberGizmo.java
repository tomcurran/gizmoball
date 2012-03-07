package model.physics;

import model.gizmos.AbsorberGizmo;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;


public class PhysicsAbsorberGizmo extends PhysicsGizmo
{
	public PhysicsAbsorberGizmo(AbsorberGizmo absorber)
	{
		double x = absorber.getX();
		double y = absorber.getY();
		double w = absorber.getWidth();
		double h = absorber.getHeight();
		
		objects.add(new LineSegment(x, y, x + w, y)); //top
		objects.add(new LineSegment(x + w, y, x + w, y + h)); //right
		objects.add(new LineSegment(x, y + h, x + w, y + h)); //bottom
		objects.add(new LineSegment(x, y, x, y + h)); //left
		
		objects.add(new Circle(x, y, 0)); //top left
		objects.add(new Circle(x + w, y, 0)); //top right
		objects.add(new Circle(x + w, y + h, 0)); //bottom right
		objects.add(new Circle(x, y + h, 0)); //bottom left
	}
}
