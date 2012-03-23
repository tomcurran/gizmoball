package view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.RotatablePoint;
import model.gizmos.IGizmo;
import model.gizmos.TriangleBumper;

@SuppressWarnings("serial")
public class TriangleView extends GizmoView {

	public TriangleView(TriangleBumper model) {
		super(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		IGizmo model = getGizmo();
		int x = model.getX();
		int y = model.getY();
		int orientation = model.getOrientation();
		Point scale = getScale();

		RotatablePoint centre = new RotatablePoint(x + 0.5, y + 0.5);
		RotatablePoint p1 = new RotatablePoint(x, y).rotate(centre, orientation);
		RotatablePoint p2 = new RotatablePoint(x + 1, y).rotate(centre, orientation);
		RotatablePoint p3 = new RotatablePoint(x, y + 1).rotate(centre, orientation);

		int xpoints[] = new int[] {p1.getScaledX(scale.x), p2.getScaledX(scale.x), p3.getScaledX(scale.x)};
		int ypoints[] = new int[] {p1.getScaledY(scale.y), p2.getScaledY(scale.y), p3.getScaledY(scale.y)};
		
		g.fillPolygon(xpoints, ypoints, 3);
	}

}