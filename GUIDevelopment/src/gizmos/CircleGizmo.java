package gizmos;

import model.BoardItem;



public class CircleGizmo extends BoardItem {

	public CircleGizmo(int x, int y) {
		super(x, y, 1, 1);
		System.out.printf("Circle: x=%d,y=%d\n", x, y);
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