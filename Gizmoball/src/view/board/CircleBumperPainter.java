package view.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.gizmos.IGizmo;

public class CircleBumperPainter implements IGizmoPainter
{
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight());
		
		g.setColor(Color.GREEN);
		g.fill(circle);
		
		g.setColor(Color.GREEN.darker());
		g.draw(circle);
	}
}
