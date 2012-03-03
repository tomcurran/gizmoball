package model;

import java.awt.Color;

public class Ball extends BoardItem implements IBoardItem {

	private double vx;
	private double vy;
	
	public Ball(double x, double y, double vx, double vy) {
		super(x, y, 1, 1, Color.BLUE);
		this.vx = vx;
		this.vy = vy;
	}

	public double getVelocityX() {
		return vx;
	}

	public void setVelocityX(double vx) {
		this.vx = vx;
	}

	public double getVelocityY() {
		return vy;
	}

	public void setVelocityY(double vy) {
		this.vy = vy;
	}

}