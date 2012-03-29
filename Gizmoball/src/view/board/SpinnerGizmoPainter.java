package view.board;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import model.gizmos.IGizmo;
import model.gizmos.SpinnerGizmo;

public class SpinnerGizmoPainter implements IGizmoPainter {

	@Override
	public void paint(Graphics2D g, IGizmo gizmo) {
		int x = gizmo.getX(), y = gizmo.getY();

		SpinnerGizmo spinner = (SpinnerGizmo) gizmo;

		Path2D.Double path = new Path2D.Double();
		path.moveTo(x + 0.75, y + 0.25);
		path.lineTo(x + 0.75, y + 1.75);
		path.curveTo(x + 0.75, y + 2, x + 1.25, y + 2, x + 1.25, y + 1.75);
		path.lineTo(x + 1.25, y + 0.25);
		path.curveTo(x + 1.25, y, x + 0.75, y, x + 0.75, y + 0.25);

		if (spinner.getAngle() != 0)
			path.transform(AffineTransform.getRotateInstance(spinner.getAngle(), x + 1, y + 1));

		g.setColor(Color.ORANGE);
		g.fill(path);

		g.setColor(Color.ORANGE.darker());
		g.draw(path);
	}

}
