package model;

public class RightFlipper extends Flipper implements IFlipper {

	public RightFlipper(int x, int y, double angle) {
		super(x, y, 2, 2, angle, 0, Math.PI / 2);
	}

	public RightFlipper(int x, int y) {
		this(x, y, 0);
	}

}