package model.gizmos;

import model.GizmoType;

public class GateGizmo extends CircleBumper
{
	public GateGizmo(int x, int y)
	{
		super(x, y);
	}
	
	@Override
	public GizmoType getType()
	{
		return GizmoType.GateGizmo;
	}
}
