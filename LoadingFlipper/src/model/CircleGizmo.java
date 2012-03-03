package model;

import java.awt.Color;

public class CircleGizmo extends BoardItem implements IGizmo, IBoardItem {

	private Color c1, c2;
	
	public CircleGizmo(int x, int y) {
		super(x, y, 1, 1, Color.GREEN);
		c1 = Color.GREEN;
		c2 = Color.YELLOW;
	}

	@Override
	public void action() {
		setColor(getColor() == c1 ? c2 : c1);
	}

}