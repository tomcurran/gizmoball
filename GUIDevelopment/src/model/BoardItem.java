package model;

import java.util.Observable;
;

public abstract class BoardItem extends Observable {

	private int x, y;
	private int width, height, orientation;

	public BoardItem(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
	}

	public int getX() {
		return x;
	}

	
	public int getY() {
		return y;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void move(int x, int y) {
		System.out.printf("Move: corner@x=%d,y=%d\n", x, y);
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}

	
	public void move(double x, double y) {
		System.out.printf("Move: center@x=%.1f,y=%.1f\n", x, y);
		this.x = (int) (x - (width / 2));
		this.y = (int) (y - (height / 2));
		setChanged();
		notifyObservers();
	}

	
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

	
	public int getOrientation() {
		return orientation;
	}

}