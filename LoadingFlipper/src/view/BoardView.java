package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.Absorber;
import model.Ball;
import model.Board;
import model.CircleGizmo;
import model.IBoardItem;
import model.IFlipper;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import model.TriggerSystem;
import controller.BoardController;
import controller.MagicKeyListener;

@SuppressWarnings("serial")
public class BoardView extends JPanel implements ActionListener, Observer {

	private static final double L = 20.0;
	private static final int TICK = 20;

	private Board model;
	private Timer timer;
	private MagicKeyListener listener;
	private BoardController controller;

	public BoardView(Board model, TriggerSystem trigsys) {
		this.model = model;
		setPreferredSize(new Dimension((int)L * model.getWidth(), (int)L
				* model.getHeight()));
		setBackground(new Color(0));
		for (IBoardItem boardItem : model.getItems()) {
			((Observable) boardItem).addObserver(this);
		}

		controller = new BoardController(trigsys);
		listener = new MagicKeyListener(controller);
		addKeyListener(listener);
		requestFocus();

		timer = new Timer(TICK, this);
		timer.start();
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

		int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		double x, y, w, h, flipW, angle;
		int orientation;
		double halfL = L / 2, qrtL = L / 4;

		for (IBoardItem boardItem : model.getItems()) {

			cls = boardItem.getClass();
			x = L * boardItem.getX();
			y = L * boardItem.getY();
			w = L * boardItem.getWidth();
			h = L * boardItem.getHeight();

			if (cls.equals(SquareGizmo.class)) {
				g.setColor(red);
				fillRect(g, x, y, w, h);
			} else if (cls.equals(CircleGizmo.class)) {
				g.setColor(green);
				fillOval(g, x, y, w, h);
			} else if (cls.equals(TriangleGizmo.class)) {
				g.setColor(blue);
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
				orientation = boardItem.getOrientation();
				g.fillPolygon(xTrianglePoints[orientation],
						yTrianglePoints[orientation], 4);

			} else if (cls.equals(RightFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;

				IFlipper f = (IFlipper)boardItem;
				angle = f.getAngle();
				
				// pivot
				fillOval(g, x + (flipW * 3), y, halfL);

				// some stick points
				sx1 = (int)Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle + ((3*Math.PI)/ 2)))));
				sy1 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + ((3*Math.PI) / 2)))));
				sx2 = (int)Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle + (Math.PI / 2)))));
				sy2 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + (Math.PI / 2)))));

				// end point
				h -= flipW;
				x = (x + w - flipW  + (h * Math.sin(-angle)));
				y = (y + (h * Math.cos(-angle)));

				fillOval(g, x, y, halfL);

				// some more stick points
				sx3 = (int)Math.round((x + qrtL + (qrtL * Math.sin(-angle + (Math.PI / 2)))));
				sy3 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + (Math.PI / 2)))));
				sx4 = (int)Math.round((x + qrtL + (qrtL * Math.sin(-angle + ((3*Math.PI)/ 2)))));
				sy4 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + ((3*Math.PI) / 2)))));

				int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
				int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
				g.fillPolygon(xPoints, yPoints, 5);

			} else if (cls.equals(LeftFlipper.class)) {
				g.setColor(orange);

				flipW = w / 4;
				
				IFlipper f = (IFlipper)boardItem;
				angle = f.getAngle();

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
				
			} else if (cls.equals(Absorber.class)) {
				g.setColor(pink);
				fillRect(g, x, y, w, h);
			} else if (cls.equals(Ball.class)) {
				g.setColor(blue);
				fillOval(g, x, y, halfL);
			}

		}
	}

	private void fillOval(Graphics g, double x, double y, double halfL) {
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(halfL), (int)Math.round(halfL));
	}

	private void fillOval(Graphics g, double x, double y, double w, double h) {
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(w), (int)Math.round(h));
	}

	private void fillRect(Graphics g, double x, double y, double width, double height) {
		g.fillRect((int)Math.round(x), (int)Math.round(y), (int)Math.round(width), (int)Math.round(height));
	}

//	private void fillRoundRect(Graphics g, double x, double y, double w, double h, double halfL) {
//		g.fillRoundRect((int)Math.round(x), (int)Math.round(y), (int)Math.round(w), (int)Math.round(h), (int)Math.round(halfL), (int)Math.round(halfL));
//	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<IFlipper> flippers = model.getFlippers();
		for (IFlipper flipper: flippers) {
			flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum() * (double)TICK / 1000);
		}
	}

	public boolean isFocusable() {
		return true;
	}

}