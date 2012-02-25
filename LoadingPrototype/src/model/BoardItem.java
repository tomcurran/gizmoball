package model;

public abstract class BoardItem implements IBoardItem {

	private double x, y;
	private int width, height;

	public BoardItem(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void move(int x, int y) {
		System.out.printf("Move: corner@x=%d,y=%d\n", x, y);
		// x,y are corner point
		this.x = x;
		this.y = y;
	}

	@Override
	public void move(double x, double y) {
		System.out.printf("Move: center@x=%d,y=%d\n", x, y);
		// x,y are center point
		this.x = x;
		this.y = y;
	}

	@Override
	public void rotate() {
		// default implementation does not rotate
		System.out.printf("Rotate\n");
	}

}