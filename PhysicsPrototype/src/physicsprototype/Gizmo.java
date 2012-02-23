package physicsprototype;

import java.util.Observable;

public abstract class Gizmo extends Observable implements IGizmo 
{
	protected int x, y, width, height;
	protected boolean triggered;

	public Gizmo()
	{
		
	}
	
	public Gizmo(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int getX()
	{
		return x;
	}

	@Override
	public int getY()
	{
		return y;
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}
	
	@Override
	public boolean getIsTriggered()
	{
		return triggered;
	}
}
