package model;

import java.awt.Color;


public abstract class Flipper extends BoardItem implements IBoardItem, IFlipper, IGizmo {

	private static final double ANGULAR_DELTA = 6 * Math.PI;

	private double angle, startAngle, endAngle, angularMomentum;

	public Flipper(int x, int y, double angle) {
		super(x, y, 2, 2, Color.ORANGE);
		this.angle = angle;
		this.startAngle = 0;
		this.endAngle = Math.PI / 2;
		this.angularMomentum = 0;
	}

	public Flipper(int x, int y) {
		this(x, y, 0);
	}

	@Override
	public void setAngle(double angle) {
		if (angle >= endAngle) {
			this.angle = endAngle;
			angularMomentum = 0;
		} else if (angle <= startAngle) {
			this.angle = startAngle;
			angularMomentum = 0;
		} else {
			this.angle = angle;
		}

		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public void action() {
		if (angularMomentum == 0) {
			if (angle == startAngle) {
				angularMomentum = ANGULAR_DELTA;
			} else /*if (angle == endAngle)*/ {
				angularMomentum = -ANGULAR_DELTA;
			}
		} else if (angularMomentum == ANGULAR_DELTA) {
			angularMomentum = -ANGULAR_DELTA;
		} else if (angularMomentum == -ANGULAR_DELTA) {
			angularMomentum = ANGULAR_DELTA;
		}
		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngularMomentum() {
		return angularMomentum;
	}

}