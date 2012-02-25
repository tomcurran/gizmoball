package model;

public class Ball extends BoardItem implements IBoardItem {

	public Ball(double x, double y, double vx, double vy) {
		// TODO take double not int in super()
		super((int)x, (int)y, 1, 1);
		System.out.printf("Ball: center@x=%.1f,y=%.1f, velocity@vx=%.1f vy=%.1f\n", x, y, vx, vy);
	}

}