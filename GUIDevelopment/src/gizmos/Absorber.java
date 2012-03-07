package gizmos;

import model.BoardItem;


public class Absorber extends BoardItem{

	public Absorber(int x1, int y1, int x2, int y2) {
		super(x1, y1, x2-x1, y2-y1);
		System.out.printf("Absorber: x1=%d y1=%d x2=%d y2=%d\n", x1, y1, x2, y2);
	}

	@Override
	public void move(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double dx, double dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub
		
	}

}