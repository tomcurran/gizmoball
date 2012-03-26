package view.gizmos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.gizmos.IGizmo;

public class CircleBumperPainter implements IGizmoPainter
{
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		g.setColor(Color.GREEN);
		g.fill(new Ellipse2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight()));
	}
}
