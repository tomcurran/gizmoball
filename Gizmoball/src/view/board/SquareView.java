package view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.gizmos.IGizmo;
import model.gizmos.SquareBumper;

@SuppressWarnings("serial")
public class SquareView extends GizmoView {

	public SquareView(SquareBumper model) {
		super(model);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		IGizmo model = getGizmo();
		Point scale = getScale();
		double x = scale.x * model.getX();
		double y = scale.y * model.getY();
		double w = scale.x * model.getWidth();
		double h = scale.y * model.getHeight();
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, (int)w, (int)h);
	}

}