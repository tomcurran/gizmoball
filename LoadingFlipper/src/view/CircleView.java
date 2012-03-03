package view;

import java.awt.Graphics;

import javax.swing.JComponent;

import model.Board;
import model.CircleGizmo;

@SuppressWarnings("serial")
public class CircleView extends JComponent {

	private CircleGizmo model;

	public CircleView(CircleGizmo model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double x = Board.L * model.getX();
		double y = Board.L * model.getY();
		double w = Board.L * model.getWidth();
		double h = Board.L * model.getHeight();
		g.setColor(model.getColor());
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(w), (int)Math.round(h));
	}

}