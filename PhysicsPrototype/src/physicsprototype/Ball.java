package physicsprototype;

import java.util.Observable;

public class Ball extends Observable
{
	private double x, y, radius, mass;
	
	public Ball(double x, double y, double radius, double mass)
	{
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.mass = mass;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getRadius()
	{
		return radius;
	}
	
	public double getMass()
	{
		return mass;
	}
	
	public void setX(double value)
	{
		this.x = value;
	}
	
	public void setY(double value)
	{
		this.y = value;
	}
	
	public void move(double dx, double dy)
	{
		x += dx;
		y += dy;
		this.setChanged();
		this.notifyObservers();
	}
}
