package model.gizmos;

import model.GizmoType;

public class Flipper extends Gizmo {
	
	public static final double ANGULAR_MOMENTUM = 6 * Math.PI;

	private double angle, startAngle, endAngle;
	protected int orientation;
	protected double angularMomentum;

	/**
	 * Constructs a new flipper.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point.
	 * @param width - width of the flipper.
	 * @param height - height of the flipper.
	 * @param angle - the angle it starts at.
	 * @param startAngle - angle lower limit. 
	 * @param endAngle - angle upper limit.
	 */
	public Flipper(int x, int y, int width, int height, double angle,
			double startAngle, double endAngle) {
		super(x, y, width, height);
		this.orientation = 0;
		this.angle = angle;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
	}

	@Override
	public void move(int x, int y) {
		super.move(x, y);
	}

	@Override
	public void move(double x, double y) {
		super.move(x, y);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public void rotate() {
		if (orientation < 3) {
			orientation++;
		} else {
			orientation = 0;
		}
	}

	@Override
	public int getOrientation() {
		return orientation;
	}

	public void setAngle(double angle) {
		this.angle = angle;

		if (angle <= getEndAngle()) {
			this.angle = getEndAngle();
			this.angularMomentum = 0;
		} else if (angle >= getStartAngle()) {
			this.angle = getStartAngle();
			this.angularMomentum = 0;
		}

		this.setChanged();
		this.notifyObservers();
	}

	public double getAngle() {
		return angle;
	}

	public double getStartAngle() {
		return startAngle;
	}

	public double getEndAngle() {
		return endAngle;
	}

	public double getAngularMomentum() {
		return angularMomentum;
	}

	public void setAngularMomentum(double angularMomentum) {
		this.angularMomentum = angularMomentum;
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public GizmoType getType() {
		return GizmoType.Flipper;
	}
	
	@Override
	public boolean canRotate() {
		return true;
	}
}