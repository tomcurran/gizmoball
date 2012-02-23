package physicsprototype;

public abstract class Bumper extends Gizmo
{
	public Bumper()
	{
		
	}
	
	public Bumper(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	@Override
	public void trigger()
	{
		this.triggered = !this.triggered;
	}
}
