package model;

public class SquareGizmo extends BoardItem implements IGizmo, IBoardItem {

	public SquareGizmo(int x, int y) {
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