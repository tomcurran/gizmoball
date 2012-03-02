package model;

public class TriangleGizmo extends BoardItem implements IGizmo, IBoardItem {

	public TriangleGizmo(int x, int y) {
		super(x, y, 1, 1);
	}

	@Override
	public void activate() {
		// non required
	}

	@Override
	public void deactivate() {
		// non required
	}

}