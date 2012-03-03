package model;

import java.awt.Color;

public class SquareGizmo extends BoardItem implements IGizmo, IBoardItem {

	private Color c1, c2;

	public SquareGizmo(int x, int y) {
		super(x, y, 1, 1, Color.RED);
		c1 = Color.RED;
		c2 = Color.GREEN;
	}

	@Override
	public void action() {
		setColor(getColor() == c1 ? c2 : c1);
	}

}