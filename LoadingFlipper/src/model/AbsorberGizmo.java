package model;

import java.awt.Color;

public class AbsorberGizmo extends BoardItem implements IBoardItem, IGizmo {

	public AbsorberGizmo(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2-x1, y2-y1, Color.PINK);
	}

	@Override
	public void action() {
		System.out.println("absorber shoots ball");
	}

}