package model.gizmos;

import model.GizmoType;

public class SquareBumper extends Bumper
{
	public SquareBumper(int x, int y)
	{
		super(x, y, 20, 20);
	}
	
	public GizmoType getType()
	{
		return GizmoType.SquareBumper;
	}
}
