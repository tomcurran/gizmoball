package physicsprototype;

import java.util.Observer;

public interface IGizmo
{
	GizmoType getType();
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	boolean getIsTriggered();
	void trigger();
	void addObserver(Observer observer);
}
