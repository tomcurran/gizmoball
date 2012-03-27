package model.gizmos;

import model.GizmoType;

public class OuterWallsGizmo extends Gizmo {
	
	/**
	 * Constructs an outerwall gizmo. 
	 * 
	 * @param width - its width.
	 * @param height - its height.
	 */
	public OuterWallsGizmo(int width, int height) {
		super(0, 0, width, height);
	}

	@Override
	public GizmoType getType() {
		return GizmoType.OuterWalls;
	}

}
