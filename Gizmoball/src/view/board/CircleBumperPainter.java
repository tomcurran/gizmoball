package view.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.GizmoType;
import model.gizmos.IGizmo;

public class CircleBumperPainter implements IGizmoPainter
{	
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		Ellipse2D.Double circle = new Ellipse2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight());
		
		Color colour;
		
		switch (gizmo.getType())
		{
			case CircleBumper:
				colour = Color.GREEN;
				break;
				
			case AcceleratorGizmo:
				colour = Color.CYAN;
				break;
				
			//case PortalGizmo:
			default:
				colour = new Color(139, 0, 244);
				break;
		}
		
		g.setColor(colour);
		g.fill(circle);
		
		g.setColor(colour.darker());
		g.draw(circle);
	}
}
