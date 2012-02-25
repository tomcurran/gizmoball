package model;

public class SquareGizmo extends BoardItem implements IGizmo, IBoardItem {

	public SquareGizmo(int x, int y) {
		super(x, y, 1, 1);
		System.out.printf("Square: x=%d,y=%d\n", x, y);
	}

}