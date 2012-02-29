package model;

import java.util.Observable;

public class Flipper extends Observable implements IFlipper {

	private double x, y, angle, startAngle, endAngle;
	private int width, height, orientation;
	protected double angularMomentum;

	public Flipper(int x, int y, int width, int height, double angle, double startAngle, double endAngle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
		this.angle = angle;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
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
		setChanged();
		notifyObservers();
	}

	@Override
	public int getOrientation() {
		return orientation;
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle;
		
		if (angle >= getEndAngle())
		{
			this.angle = getEndAngle();
			angularMomentum = 0;
		}
		else if (angle <= getStartAngle())
		{
			this.angle = getStartAngle();
			angularMomentum = 0;
		}
		
		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public double getStartAngle() {
		return startAngle;
	}

	@Override
	public double getEndAngle() {
		return endAngle;
	}

	@Override
	public void activate() {
		angularMomentum = 6 * Math.PI;
		setChanged();
		notifyObservers();
	}

	@Override
	public void deactivate() {
		angularMomentum = - 6 * Math.PI;
		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngularMomentum() {
		return angularMomentum;
	}
}