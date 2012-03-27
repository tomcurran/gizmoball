package model.gizmos;

import model.GizmoType;

public class CircleBumper extends Bumper implements IGizmo {
	
	/**
	 * Constructs a new CircleBumper.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point.
	 */
	public CircleBumper(int x, int y) {
		super(x, y, 1, 1);
	}

	public GizmoType getType() {
		return GizmoType.CircleBumper;
	}
}
