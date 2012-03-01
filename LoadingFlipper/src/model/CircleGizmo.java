package model;

public class CircleGizmo extends BoardItem implements IGizmo, IBoardItem {

	public CircleGizmo(int x, int y) {
		super(x, y, 1, 1);
		System.out.printf("Circle: x=%d,y=%d\n", x, y);
	}

}