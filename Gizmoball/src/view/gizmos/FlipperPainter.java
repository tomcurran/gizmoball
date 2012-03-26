package view.gizmos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import model.gizmos.Flipper;
import model.gizmos.IGizmo;

public class FlipperPainter implements IGizmoPainter
{
	@Override
	public void paint(Graphics2D g, IGizmo gizmo)
	{
		int orientation = gizmo.getOrientation();
		int x = gizmo.getX(), y = gizmo.getY();
		
		Flipper flipper = (Flipper)gizmo;
		
		Path2D.Double path = new Path2D.Double();
		path.moveTo(x, y + 0.25);
		path.lineTo(x, y + 1.75);
		path.curveTo(x, y + 2, x + 0.5, y + 2, x + 0.5, y + 1.75);
		path.lineTo(x + 0.5, y + 0.25);
		path.curveTo(x + 0.5, y, x, y, x, y + 0.25);
		
		if (flipper.getAngle() != 0)
			path.transform(AffineTransform.getRotateInstance(flipper.getAngle(), x + 0.25, y + 0.25));
		
		if (orientation != 0)
			path.transform(AffineTransform.getRotateInstance(Math.PI / 2 * orientation, x + 1, y + 1));
		
		g.setColor(Color.ORANGE);
		g.fill(path);
	}
}
