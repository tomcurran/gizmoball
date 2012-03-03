package view;

import java.awt.Graphics;

import javax.swing.JComponent;

import model.Board;
import model.IFlipper;
import model.LeftFlipper;

@SuppressWarnings("serial")
public class LeftFlipperView extends JComponent implements IBoardItemView {

	private LeftFlipper model;

	public LeftFlipperView(LeftFlipper model) {
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		double x = Board.L * model.getX();
		double y = Board.L * model.getY();
		double w = Board.L * model.getWidth();
		double h = Board.L * model.getHeight();
		double flipW = w / 4;
		double halfL = Board.L / 2, qrtL = Board.L / 4;
		IFlipper f = (IFlipper)model;
		double angle = f.getAngle();
		int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		g.setColor(model.getColor());

		// pivot
		fillOval(g, x, y, halfL);

		// some stick points
		sx1 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2)))));
		sy1 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2)))));
		sx2 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2)))));
		sy2 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2)))));

		// end point
		h -= flipW;
		x = (x + (h * Math.sin(angle)));
		y = (y + (h * Math.cos(angle)));
		fillOval(g, x, y, halfL);

		// some more stick points
		sx3 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2)))));
		sy3 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2)))));
		sx4 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2)))));
		sy4 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2)))));

		int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
		int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
		g.fillPolygon(xPoints, yPoints, 5);
	}

	private void fillOval(Graphics g, double x, double y, double halfL) {
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(halfL), (int)Math.round(halfL));
	}

}