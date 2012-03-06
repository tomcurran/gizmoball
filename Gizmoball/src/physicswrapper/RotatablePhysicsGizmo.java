package physicswrapper;


public class RotatablePhysicsGizmo extends PhysicsGizmo
{
	protected double rot[][];
	
	public RotatablePhysicsGizmo()
	{
		rot = new double[][]
		{
			{1, 0},  //cos 0, sin 0
			{0, 1},  //cos pi/2, sin pi/2
			{-1, 0}, //cos pi, sin pi
			{0, -1}  //cos 3pi/2, sin 3pi/2
		};
	}
	
	
	protected Point rotate(Point p, Point centre, double cos, double sin)
	{
		Point r = new Point();
		p.x -= centre.x;
		p.y -= centre.y;
		r.x = p.x * cos - p.y * sin + centre.x;
		r.y = p.x * sin + p.y * cos + centre.y;
		return r;
	}
	
	
	protected class Point
	{
		double x, y;
		
		public Point()
		{
		}
		
		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
	}
}
