package gizmos;

import model.BoardItem;



public class Ball extends BoardItem {

	public Ball(double x, double y, double vx, double vy) {
		// TODO take double not int in super()
		super((int)x, (int)y, 1, 1);
		System.out.printf("Ball: center@x=%.1f,y=%.1f, velocity@vx=%.1f vy=%.1f\n", x, y, vx, vy);
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