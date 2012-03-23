package view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.RotatablePoint;
import model.gizmos.Flipper;

@SuppressWarnings("serial")
public class FlipperView extends GizmoView {

	public FlipperView(Flipper model) {
		super(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Flipper model = (Flipper)getGizmo();
		Point scale = getScale();
		int orientation = model.getOrientation();
		int x = model.getX();
		int y = model.getY();
		double angle = model.getAngle();

		RotatablePoint pivot = new RotatablePoint(x + 0.25, y + 0.25);
		RotatablePoint centre = new RotatablePoint(x + 1.0, y + 1.0);

		RotatablePoint pivotcircle = pivot.rotate(centre, orientation);
		pivotcircle.x -= 0.25;
		pivotcircle.y -= 0.25;

		RotatablePoint endcircle = new RotatablePoint(x + 0.25, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);
		endcircle.x -= 0.25;
		endcircle.y -= 0.25;

		RotatablePoint line1s = new RotatablePoint(x, y + 0.25).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line1e = new RotatablePoint(x, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line2s = new RotatablePoint(x + 0.5, y + 0.25).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line2e = new RotatablePoint(x + 0.5, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);

		int xPoints[] = { line1s.getScaledX(scale.x), line1e.getScaledX(scale.x), line2e.getScaledX(scale.x), line2s.getScaledX(scale.x) };
		int yPoints[] = { line1s.getScaledY(scale.y), line1e.getScaledY(scale.y), line2e.getScaledY(scale.y), line2s.getScaledY(scale.y) };

		g.setColor(Color.ORANGE);
		g.fillPolygon(xPoints, yPoints, 4);
		g.fillOval(pivotcircle.getScaledX(scale.x), pivotcircle.getScaledY(scale.y), (int)Math.round(0.5 * scale.x), (int)Math.round(0.5 * scale.y));
		g.fillOval(endcircle.getScaledX(scale.x), endcircle.getScaledY(scale.y), (int)Math.round(0.5 * scale.x), (int)Math.round(0.5 * scale.y));
	}

}