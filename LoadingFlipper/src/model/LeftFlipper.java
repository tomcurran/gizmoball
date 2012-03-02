package model;

public class LeftFlipper extends Flipper implements IBoardItem, IFlipper, IGizmo {

	public LeftFlipper(int x, int y, double angle) {
		super(x, y, angle);
	}

	public LeftFlipper(int x, int y) {
		this(x, y, 0);
	}

}