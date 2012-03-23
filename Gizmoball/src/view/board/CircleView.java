package view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.gizmos.CircleBumper;
import model.gizmos.IGizmo;

@SuppressWarnings("serial")
public class CircleView extends GizmoView {

	public CircleView(CircleBumper model) {
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
		g.setColor(Color.GREEN);
		g.fillOval((int)x, (int)y, (int)w, (int)h);
	}

}