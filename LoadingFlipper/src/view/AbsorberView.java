package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import model.AbsorberGizmo;
import model.Board;

@SuppressWarnings("serial")
public class AbsorberView extends JComponent implements IBoardItemView {

	private AbsorberGizmo model;

	public AbsorberView(AbsorberGizmo model) {
		this.model = model;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double x = Board.L * model.getX();
		double y = Board.L * model.getY();
		double w = Board.L * model.getWidth();
		double h = Board.L * model.getHeight();
		g.setColor(new Color(255, 0, 255));
		g.fillRect((int)Math.round(x), (int)Math.round(y), (int)Math.round(w), (int)Math.round(h));
	}

}