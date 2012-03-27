package model;

public class Ball extends BoardItemBase {
	
	private double x, y, radius, mass;
	private double vx, vy;
	private boolean captured;

	/**
	 * Constructs a new ball.
	 * 
	 * @param x - the x point of the ball. 
	 * @param y - the y point of the ball. 
	 * @param radius - the balls radius.
	 * @param mass - the balls mass. 
	 * @param vx - the vector x of the ball.
	 * @param vy - the vector y of the ball. 
	 */
	public Ball(double x, double y, double radius, double mass, double vx, double vy) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.mass = mass;
		this.vx = vx;
		this.vy = vy;
		this.captured = false;
	}

	/**
	 * Constructs a new ball.
	 * 
	 * @param x - the x point of the ball. 
	 * @param y - the y point of the ball. 
	 * @param radius - the balls radius.
	 * @param mass - the balls mass. 
	 */
	public Ball(double x, double y, double radius, double mass) {
		this(x, y, radius, mass, 0, 0);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getXVelocity() {
		return vx;
	}

	public double getYVelocity() {
		return vy;
	}

	public double getRadius() {
		return radius;
	}

	public double getMass() {
		return mass;
	}

	@Override
	public void move(double x, double y) {
		this.x = x;
		this.y = y;

		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void move(int x, int y) {
		this.x = x;
		this.y = y;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Moves the ball by the specified amount.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void moveBy(double dx, double dy) {
		this.x += dx;
		this.y += dy;

		this.setChanged();
		this.notifyObservers();
	}

	public void setVelocity(double vx, double vy) {
		this.vx = vx;
		this.vy = vy;
		this.captured = false;

		this.setChanged();
		this.notifyObservers();
	}

	public void multiplyVelocity(double amount) {
		this.vx *= amount;
		this.vy *= amount;

		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void rotate() {
		throw new UnsupportedOperationException();
	}

	public void capture() {
		this.vx = 0;
		this.vy = 0;
		this.captured = true;
		this.setChanged();
		this.notifyObservers();
	}

	public boolean getIsCaptured() {
		return captured;
	}
}
