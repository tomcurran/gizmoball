package model;

public class SquareBumper extends Bumper
{
	public SquareBumper(int x, int y)
	{
		super(x, y, 1, 1);
	}
	
	public GizmoType getType()
	{
		return GizmoType.SquareBumper;
	}
}
