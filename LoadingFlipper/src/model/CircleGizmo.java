package model;

public class CircleGizmo extends BoardItem implements IGizmo, IBoardItem {

	public CircleGizmo(int x, int y) {
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