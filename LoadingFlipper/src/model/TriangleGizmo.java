package model;

import java.awt.Color;

public class TriangleGizmo extends BoardItem implements IGizmo, IBoardItem {

	private Color c1, c2;

	public TriangleGizmo(int x, int y) {
		super(x, y, 1, 1, Color.BLUE);
		c1 = Color.BLUE;
		c2 = Color.CYAN;
	}

	@Override
	public void action() {
		setColor(getColor() == c1 ? c2 : c1);
	}

}