package model.gizmos;

import model.GizmoType;

public class SquareBumper extends Bumper {
	
	/**
	 * Constructs a new SquareBumper.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point. 
	 */
	public SquareBumper(int x, int y) {
		super(x, y, 1, 1);
	}

	public GizmoType getType() {
		return GizmoType.SquareBumper;
	}
}
