package model;

public class Absorber extends BoardItem implements IBoardItem, IGizmo {

	public Absorber(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2-x1, y2-y1);
	}

	@Override
	public void activate() {
		System.out.println("absorber shoots ball");
	}

	@Override
	public void deactivate() {}

}