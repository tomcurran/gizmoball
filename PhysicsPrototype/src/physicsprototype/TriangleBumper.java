package physicsprototype;

public class TriangleBumper extends Bumper
{
	private int orientation;
	
	public TriangleBumper(int x, int y, int orientation)
	{
		super(x, y, 1, 1);
		this.orientation = orientation;
	}
	
	public GizmoType getType()
	{
		return GizmoType.TriangleBumper;
	}
	
	public int getOrientation()
	{
		return orientation;
	}
}
