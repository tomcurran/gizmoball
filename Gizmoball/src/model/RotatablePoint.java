package model;

/**
 * Represents a 2D point in cartesian coordinates.
 */
public class RotatablePoint
{
	private static double rot[][] = new double[][]
	{
		{1, 0},  //cos 0, sin 0
		{0, 1},  //cos pi/2, sin pi/2
		{-1, 0}, //cos pi, sin pi
		{0, -1}  //cos 3pi/2, sin 3pi/2
	};
	
	public double x, y;
	
	
	/**
	 * Default constructor.
	 */
	public RotatablePoint()
	{
	}
	
	
	/**
	 * Creates a new point with the specified values of x and y.
	 * @param x
	 * @param y
	 */
	public RotatablePoint(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	/**
	 * Creates a copy of this point.
	 */
	public RotatablePoint clone()
	{
		return new RotatablePoint(x, y);
	}
	
	
	/**
	 * Rotates the point around a specified centre point.
	 * @param centre The point to rotate the point around.
	 * @param cos The value of cos theta.
	 * @param sin The value of sin theta.
	 * @return
	 */
	public RotatablePoint rotate(RotatablePoint centre, double cos, double sin)
	{
		RotatablePoint translated = new RotatablePoint(x - centre.x, y - centre.y);
		
		return new RotatablePoint(
	        translated.x * cos - translated.y * sin + centre.x,
	        translated.x * sin + translated.y * cos + centre.y
	    );
	}
	
	
	/**
	 * Rotates the point by the specified multiple of 90 degrees.
	 * @param centre
	 * @param orientation
	 * @return
	 */
	public RotatablePoint rotate(RotatablePoint centre, int orientation)
	{
		return rotate(centre, rot[orientation][0], rot[orientation][1]);
	}
	
	/**
	 * Rotates the point by the specified angle around a centre point.
	 * @param centre
	 * @param theta
	 * @return
	 */
	public RotatablePoint rotate(RotatablePoint centre, double theta)
	{
		return rotate(centre, Math.cos(theta), Math.sin(theta));
	}
	
	/**
	 * Returns the x value scaled by the specified amount and rounded to an int.
	 * @param xscale
	 * @return
	 */
	public int getScaledX(double xscale)
	{
		return (int)Math.round(x * xscale);
	}
	
	/**
	 * Returns the y value scaled by the specified amount and rounded to an int.
	 * @param yscale
	 * @return
	 */
	public int getScaledY(double yscale)
	{
		return (int)Math.round(y * yscale);
	}
}
