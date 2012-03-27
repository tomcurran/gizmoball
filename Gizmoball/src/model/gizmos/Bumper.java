package model.gizmos;

public abstract class Bumper extends Gizmo {
	
	public Bumper() {
	}

	/**
	 * Constructs a bumper.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point.
	 * @param width - width of the bumper.
	 * @param height - height of the bumper.
	 */
	public Bumper(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
}
