package model;

public class RightFlipper extends Flipper implements IGizmo, IBoardItem {

	public RightFlipper(int x, int y, double angle) {
		super(x, y, angle);
	}

	public RightFlipper(int x, int y) {
		this(x, y, 0);
	}

}