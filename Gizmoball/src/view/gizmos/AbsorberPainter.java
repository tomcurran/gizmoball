package view.gizmos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import model.gizmos.IGizmo;

public class AbsorberPainter implements IGizmoPainter
{
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		g.setColor(Color.MAGENTA);
		g.fill(new Rectangle2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight()));
	}
}
