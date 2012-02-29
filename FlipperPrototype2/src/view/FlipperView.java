package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.IFlipper;
import model.LeftFlipper;
import model.RightFlipper;
import controller.FlipperController;
import controller.MagicKeyListener;

@SuppressWarnings("serial")
public class FlipperView extends JPanel implements ActionListener, Observer {

	private static final double L = 30.0;
	private static final int TICK = 20;

	private List<IFlipper> flippers;
	private Timer timer;
	private MagicKeyListener listener;
	private FlipperController controller;

	public FlipperView() {
		flippers = new ArrayList<IFlipper>();
		setPreferredSize(new Dimension((int)L * 20, (int)L * 20));
		setBackground(new Color(0));

		flippers.add(new LeftFlipper(1, 1, 0));
		flippers.add(new RightFlipper(3, 1, 0));

		flippers.add(new LeftFlipper(1, 4, Math.PI/3));
		flippers.add(new RightFlipper(3, 4, Math.PI/3));

		flippers.add(new LeftFlipper(1, 7, Math.PI/4));
		flippers.add(new RightFlipper(3, 7, Math.PI/4));

		flippers.add(new LeftFlipper(1, 10, Math.PI/5));
		flippers.add(new RightFlipper(3, 10, Math.PI/5));

		flippers.add(new LeftFlipper(1, 13));
		flippers.add(new RightFlipper(3, 13));

		for (IFlipper f : flippers) {
			((Observable) f).addObserver(this);
		}

		controller = new FlipperController(flippers);
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
		Color pink = new Color(255, 0, 255);
		Color green = new Color(0, 255, 0);
		Class<? extends IFlipper> cls;
		int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		double x, y, w, h, halfL, qrtL, flipW, angle;

		for (IFlipper f : flippers) {

			cls = f.getClass();
			x = L * f.getX();
			y = L * f.getY();
			w = L * f.getWidth();
			h = L * f.getHeight();
			halfL = L / 2;
			qrtL = L / 4;
			angle = f.getAngle();

			if (cls.equals(RightFlipper.class)) {
				flipW = w / 4;

				// pivot
				g.setColor(red);
				fillOval(g, x + (flipW * 3), y, halfL);

				// some stick points
				sx1 = (int)Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle + ((3*Math.PI)/ 2)))));
				sy1 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + ((3*Math.PI) / 2)))));
				sx2 = (int)Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle + (Math.PI / 2)))));
				sy2 = (int)Math.round((y + qrtL + (qrtL * Math.cos(-angle + (Math.PI / 2)))));

				// end point
				g.setColor(green);
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
				g.setColor(pink);
				g.fillPolygon(xPoints, yPoints, 5);

			} else if (cls.equals(LeftFlipper.class)) {
				flipW = w / 4;

				// pivot
				g.setColor(red);
				fillOval(g, x, y, halfL);

				// some stick points
				sx1 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2)))));
				sy1 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2)))));
				sx2 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2)))));
				sy2 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2)))));


				// end point
				g.setColor(green);
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
				g.setColor(pink);
				g.fillPolygon(xPoints, yPoints, 5);

			}
		}
	}

	private void fillOval(Graphics g, double x, double y, double halfL) {
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(halfL), (int)Math.round(halfL));
	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (IFlipper flipper: flippers)
		{
			flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum() * (double)TICK / 1000);
		}
	}

	public boolean isFocusable() {
		return true;
	}
}