package physicsprototype;

import java.util.Observable;

public class CircleBumper extends Observable implements IGizmo
{
	public GizmoType getType()
	{
		return GizmoType.CircleBumper;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getIsTriggered() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void trigger() {
		// TODO Auto-generated method stub
		
	}
}
