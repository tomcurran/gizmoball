package view.gizmos;

import java.awt.Graphics2D;

import model.gizmos.IGizmo;

public interface IGizmoPainter
{
	void paint(Graphics2D g, IGizmo gizmo);
}
