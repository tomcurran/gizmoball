package model;

public class OuterWallsGizmo extends Gizmo
{
	public OuterWallsGizmo(int width, int height)
	{
		super(0, 0, width, height);
	}
	
	@Override
	public GizmoType getType()
	{
		return GizmoType.OuterWalls;
	}

}
