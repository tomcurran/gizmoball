package view.board;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import model.Ball;

@SuppressWarnings("serial")
public class BallView extends ScalableComponent {

	private Ball model;

	public BallView(Ball model) {
		this.model = model;
	}

	public Ball getBall() {
		return model;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Point scale = getScale();
		double x = ((model.getX() - model.getRadius()) * scale.x);
		double y = ((model.getY() - model.getRadius()) * scale.y);
		double w = (model.getRadius() * 2 * scale.x);
		double h = (model.getRadius() * 2 * scale.y);
		g.setColor(Color.YELLOW);
		g.fillOval((int)x, (int)y, (int)w, (int)h);
	}

}