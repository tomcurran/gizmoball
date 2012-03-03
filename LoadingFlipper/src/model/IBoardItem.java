package model;

import java.awt.Color;

public interface IBoardItem {

	public double getX();
	public double getY();
	public double getHeight();
	public double getWidth();
	public void move(int x, int y);
	public void move(double x, double y);
	public void rotate();
	public int getOrientation();
	public Color getColor();
	public void setColor(Color color);

}