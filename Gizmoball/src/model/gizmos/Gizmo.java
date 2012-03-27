package model.gizmos;

import model.BoardItemBase;

public abstract class Gizmo extends BoardItemBase implements IGizmo {
	
	protected int x, y, width, height;
	protected boolean triggered;

	public Gizmo() {
	}

	/**
	 * Constructs a new gizmo.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point. 
	 * @param width - width of the gizmo.
	 * @param height - height of the gizmo. 
	 */
	public Gizmo(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void move(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	@Override
	public int getOrientation() {
		return 0;
	}

	@Override
	public void rotate() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean canRotate() {
		return false;
	}
}
