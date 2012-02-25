package model;

public class LeftFlipper extends BoardItem implements IGizmo, IBoardItem {

	public LeftFlipper(int x, int y) {
		super(x, y, 2, 2);
		System.out.printf("LeftFlipper: x=%d,y=%d\n", x, y);
	}

}