package view;

import java.awt.Graphics;

import javax.swing.JComponent;

import model.Board;
import model.TriangleGizmo;

@SuppressWarnings("serial")
public class TriangleView extends JComponent {

	private TriangleGizmo model;

	public TriangleView(TriangleGizmo model) {
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
		int xTrianglePoints[][] = {
				{ (int)Math.round(x),   (int)Math.round(x+w), (int)Math.round(x),   (int)Math.round(x)   },
				{ (int)Math.round(x),   (int)Math.round(x+w), (int)Math.round(x+w), (int)Math.round(x)   },
				{ (int)Math.round(x+w), (int)Math.round(x+w), (int)Math.round(x),   (int)Math.round(x+w) },
				{ (int)Math.round(x),   (int)Math.round(x+w), (int)Math.round(x),   (int)Math.round(x)   }
		};
		int yTrianglePoints[][] = {
				{ (int)Math.round(y), (int)Math.round(y),   (int)Math.round(y+h), (int)Math.round(y) },
				{ (int)Math.round(y), (int)Math.round(y),   (int)Math.round(y+h), (int)Math.round(y) },
				{ (int)Math.round(y), (int)Math.round(y+h), (int)Math.round(y+h), (int)Math.round(y) },
				{ (int)Math.round(y), (int)Math.round(y+h), (int)Math.round(y+h), (int)Math.round(y) }
		};
		int orientation = model.getOrientation();
		g.fillPolygon(xTrianglePoints[orientation], yTrianglePoints[orientation], 4);
	}

}