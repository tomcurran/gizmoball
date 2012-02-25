package model;

public class Absorber extends BoardItem implements IBoardItem {

	public Absorber(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2-x1, y2-y1);
		System.out.printf("Absorber: x1=%d x2=%d y1=%d y2=%d\n", x1, y1, x2, y2);
	}

}