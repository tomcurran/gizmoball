package model;

public class LeftFlipper extends Flipper implements IFlipper {

	public LeftFlipper(int x, int y, double angle) {
		super(x, y, 2, 2, angle, 0, Math.PI / 2);
	}

	public LeftFlipper(int x, int y) {
		this(x, y, 0);
	}

}