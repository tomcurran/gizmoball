package model;

import java.util.Observable;

public abstract class BoardItem extends Observable implements IBoardItem {

	private double x, y;
	private int width, height, orientation;

	public BoardItem(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
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
	public void move(int x, int y) {
		System.out.printf("Move: corner@x=%d,y=%d\n", x, y);
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}

	@Override
	public void move(double x, double y) {
		System.out.printf("Move: center@x=%.1f,y=%.1f\n", x, y);
		this.x = x - (width / 2);
		this.y = y - (height / 2);
		setChanged();
		notifyObservers();
	}

	@Override
	public void rotate() {
		System.out.printf("Rotate\n");
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

}