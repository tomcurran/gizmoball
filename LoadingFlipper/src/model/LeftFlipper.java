package model;

public class LeftFlipper extends Flipper implements IGizmo, IBoardItem {

	public LeftFlipper(int x, int y, double angle) {
		super(x, y, angle);
	}

	public LeftFlipper(int x, int y) {
		this(x, y, 0);
	}

}