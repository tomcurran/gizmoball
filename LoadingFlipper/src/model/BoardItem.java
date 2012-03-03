package model;

import java.awt.Color;
import java.util.Observable;

public abstract class BoardItem extends Observable implements IBoardItem {

	private double x, y, width, height;
	private int orientation;
	private Color color;

	public BoardItem(double x, double y, double width, double height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.orientation = 0;
		this.color = color;
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
	public double getHeight() {
		return height;
	}

	@Override
	public double getWidth() {
		return width;
	}

	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;
		setChanged();
		notifyObservers();
	}

	@Override
	public void move(double x, double y) {
		this.x = x - (width / 2);
		this.y = y - (height / 2);
		setChanged();
		notifyObservers();
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
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

}