package model.gizmos;

import model.GizmoType;
import model.IBoardItem;

public interface IGizmo extends IBoardItem {
	
	GizmoType getType();

	int getX();

	int getY();

	int getWidth();

	int getHeight();

	int getOrientation();
}
