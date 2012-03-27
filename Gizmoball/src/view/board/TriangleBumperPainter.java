package view.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import model.RotatablePoint;
import model.gizmos.IGizmo;

public class TriangleBumperPainter implements IGizmoPainter {
	
	@Override
	public void paint(Graphics2D g, IGizmo gizmo) {
		int orientation = gizmo.getOrientation();
		int x = gizmo.getX(), y = gizmo.getY();

		Path2D.Double path = new Path2D.Double();
		path.moveTo(x, y);
		path.lineTo(x + 1, y);
		path.lineTo(x, y + 1);
		path.lineTo(x, y);

		if (orientation != 0)
			path.transform(AffineTransform.getRotateInstance(Math.PI / 2* orientation, x + 0.5, y + 0.5));

		g.setColor(Color.BLUE);
		g.fill(path);

		g.setColor(Color.BLUE.darker());
		g.draw(path);
	}
}
