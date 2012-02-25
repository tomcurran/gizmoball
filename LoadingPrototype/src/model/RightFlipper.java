package model;

public class RightFlipper extends BoardItem implements IGizmo, IBoardItem {

	public RightFlipper(int x, int y) {
		super(x, y, 2, 2);
		System.out.printf("RightFlipper: x=%d,y=%d\n", x, y);
	}

}