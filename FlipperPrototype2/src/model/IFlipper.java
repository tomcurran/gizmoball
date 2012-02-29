package model;

public interface IFlipper {

	public double getX();
	public double getY();
	public int getHeight();
	public int getWidth();
	public void rotate();
	public int getOrientation();
	public void setAngle(double angle);
	public double getAngle();
	public double getStartAngle();
	public double getEndAngle();
	public void activate();
	public void deactivate();
	public double getAngularMomentum();
}