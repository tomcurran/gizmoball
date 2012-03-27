package model.gizmos;

import model.GizmoType;

public class GateGizmo extends CircleBumper {
	
	/**
	 * Constructs a new gate gizmo.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point. 
	 */
	public GateGizmo(int x, int y) {
		super(x, y);
	}

	@Override
	public GizmoType getType() {
		return GizmoType.GateGizmo;
	}
}
