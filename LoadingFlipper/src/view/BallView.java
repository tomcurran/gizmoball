package view;

import java.awt.Graphics;

import javax.swing.JComponent;

import model.Ball;
import model.Board;

@SuppressWarnings("serial")
public class BallView extends JComponent implements IBoardItemView {

	private Ball model;

	public BallView(Ball model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double x = Board.L * model.getX();
		double y = Board.L * model.getY();
		double halfL = Board.L / 2;
		g.setColor(model.getColor());
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(halfL), (int)Math.round(halfL));
	}

}