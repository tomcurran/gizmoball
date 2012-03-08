package model.gizmos;

import model.GizmoType;


public class CircleBumper extends Bumper implements IGizmo
{
	public CircleBumper(int x, int y)
	{
		super(x, y, 20, 20);
	}
	
	public GizmoType getType()
	{
		return GizmoType.CircleBumper;
	}
}
