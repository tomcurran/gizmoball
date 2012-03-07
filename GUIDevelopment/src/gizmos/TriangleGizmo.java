package gizmos;

import model.BoardItem;



public class TriangleGizmo extends BoardItem {

	public TriangleGizmo(int x, int y) {
		super(x, y, 1, 1);
		System.out.printf("Triangle: x=%d,y=%d\n", x, y);
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

	
	public void connect(IBoardItem item) {
		// TODO Auto-generated method stub
		
	}

	
	public void trigger(IBoardItem item) {
		// TODO Auto-generated method stub
		
	}

	
	public boolean getTriggeredState() {
		// TODO Auto-generated method stub
		return false;
	}



}