package model;

public class TriangleGizmo extends BoardItem implements IGizmo, IBoardItem {

	public TriangleGizmo(int x, int y) {
		super(x, y, 1, 1);
		System.out.printf("Triangle: x=%d,y=%d\n", x, y);
	}

}