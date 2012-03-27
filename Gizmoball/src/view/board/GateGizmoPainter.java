package view.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.gizmos.IGizmo;

public class GateGizmoPainter implements IGizmoPainter
{
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight());
		
		if (gizmo.getTriggeredState())
		{
			g.setColor(Color.GRAY);
			g.fill(circle);
		}
		
		g.setColor(Color.GRAY.darker());
		g.draw(circle);
	}

}
