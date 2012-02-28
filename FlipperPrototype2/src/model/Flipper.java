package model;

import java.util.Observable;

public class Flipper extends Observable implements IFlipper {

	private double x, y, angle, startAngle, endAngle;
	private int width, height, orientation;
	private boolean active;

	public Flipper(int x, int y, int width, int height, double angle, double startAngle, double endAngle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
		this.angle = angle;
		this.startAngle = startAngle;
		this.endAngle = endAngle;
		this.active = false;
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
	public boolean getActive() {
		return active;
	}

	@Override
	public void activate() {
		active = true;
		setChanged();
		notifyObservers();
	}

	@Override
	public void deactivate() {
		active = false;
		setChanged();
		notifyObservers();
	}

}