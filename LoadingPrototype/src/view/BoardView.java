package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Absorber;
import model.Ball;
import model.Board;
import model.CircleGizmo;
import model.IBoardItem;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;

@SuppressWarnings("serial")
public class BoardView extends JPanel implements ActionListener, Observer {

	private static final int L = 20;

	private Board model;

	public BoardView(Board model) {
		this.model = model;
		setPreferredSize(new Dimension(L * model.getWidth(), L
				* model.getHeight()));
		setBackground(new Color(0));
		for (IBoardItem boardItem : model.getItems()) {
			((Observable) boardItem).addObserver(this);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color orange = new Color(255, 204, 0);
		Color pink = new Color(255, 0, 255);

		Class<? extends IBoardItem> cls;

		int x, y, w, h, halfL, orientation, flipW;

		for (IBoardItem boardItem : model.getItems()) {

			cls = boardItem.getClass();
			x = L * (int) boardItem.getX();
			y = L * (int) boardItem.getY();
			w = L * boardItem.getWidth();
			h = L * boardItem.getHeight();
			halfL = L / 2;

			if (cls.equals(SquareGizmo.class)) {
				g.setColor(red);
				g.fillRect(x, y, w, h);
			} else if (cls.equals(CircleGizmo.class)) {
				g.setColor(green);
				g.fillOval(x, y, w, h);
			} else if (cls.equals(TriangleGizmo.class)) {
				g.setColor(blue);
				int xTrianglePoints[][] = {
						{ x,   x+w, x,   x   },
						{ x,   x+w, x+w, x   },
						{ x+w, x+w, x,   x+w },
						{ x,   x+w, x,   x   }
				};
				int yTrianglePoints[][] = {
						{ y, y,   y+h, y },
						{ y, y,   y+h, y },
						{ y, y+h, y+h, y },
						{ y, y+h,  +h, y }
				};
				orientation = boardItem.getOrientation();
				g.fillPolygon(xTrianglePoints[orientation],
						yTrianglePoints[orientation], 4);
			} else if (cls.equals(RightFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;
				g.fillRoundRect(x + (flipW * 3), y, flipW, h, halfL, halfL);
			} else if (cls.equals(LeftFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;
				g.fillRoundRect(x, y, flipW, h, halfL, halfL);
			} else if (cls.equals(Absorber.class)) {
				g.setColor(pink);
				g.fillRect(x, y, w, h);
			} else if (cls.equals(Ball.class)) {
				g.setColor(blue);
				g.fillOval(x, y, halfL, halfL);
			}

		}
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}