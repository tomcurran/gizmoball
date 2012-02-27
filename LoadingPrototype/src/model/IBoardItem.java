package model;

public interface IBoardItem {

	public double getX();
	public double getY();
	public int getHeight();
	public int getWidth();
	public void move(int x, int y);
	public void move(double x, double y);
	public void rotate();
	public int getOrientation();

}