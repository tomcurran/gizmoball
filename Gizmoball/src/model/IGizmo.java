package model;

import java.util.Observer;

public interface IGizmo extends IBoardItem
{
	GizmoType getType();
	int getX();
	int getY();
	int getWidth();
	int getHeight();
	int getOrientation();
}
