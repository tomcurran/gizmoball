package model.gizmos;

import model.GizmoType;

public class TriangleBumper extends Bumper
{
	private int orientation;
	
	public TriangleBumper(int x, int y, int orientation)
	{
		super(x, y, 1, 1);
		this.orientation = orientation;
	}
	
	public GizmoType getType()
	{
		return GizmoType.TriangleBumper;
	}
	
	public int getOrientation()
	{
		return orientation;
	}
	
	@Override
	public void rotate()
	{
		System.out.println("BEFORE R orientation : " + orientation);
		System.out.println("ROTATED THE TRIANGLE");
		
		
		orientation = (orientation + 1) % 4;
		System.out.println("AFTER R orientation : " + orientation);
	}
}
