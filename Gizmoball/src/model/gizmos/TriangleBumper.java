package model.gizmos;

import model.GizmoType;

public class TriangleBumper extends Bumper {
	
	private int orientation;

	/**
	 * Constructs a new TriangleBumper.
	 * 
	 * @param x - topleft x point. 
	 * @param y - topleft y point.
	 * @param orientation - the triangles orientation.
	 */
	public TriangleBumper(int x, int y, int orientation) {
		super(x, y, 1, 1);
		this.orientation = orientation;
	}

	public GizmoType getType() {
		return GizmoType.TriangleBumper;
	}

	public int getOrientation() {
		return orientation;
	}

	@Override
	public void rotate() {
		orientation = (orientation + 1) % 4;
	}
}
