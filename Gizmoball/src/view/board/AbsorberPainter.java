package view.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import model.gizmos.AbsorberGizmo;
import model.gizmos.IGizmo;

public class AbsorberPainter implements IGizmoPainter {
	
	@Override
	public void paint(Graphics2D g, IGizmo gizmo) {
		Rectangle2D.Double rect = new Rectangle2D.Double(gizmo.getX(), gizmo.getY(), gizmo.getWidth(), gizmo.getHeight());

		g.setColor(Color.MAGENTA);
		g.fill(rect);

		g.setColor(Color.MAGENTA.darker());
		g.draw(rect);
		
		g.drawString("" + ((AbsorberGizmo)gizmo).getCapturedBalls().size(), gizmo.getX(), gizmo.getY() + 1);
	}
}
