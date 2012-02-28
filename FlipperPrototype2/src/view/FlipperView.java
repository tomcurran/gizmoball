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

@SuppressWarnings("serial")
public class FlipperView extends JPanel implements ActionListener, Observer {

	private static final int L = 30;
	private static final int TICK = 20;

	private List<IFlipper> flippers;
	private Timer timer;

	public FlipperView() {
		flippers = new ArrayList<IFlipper>();
		setPreferredSize(new Dimension(L * 20, L * 20));
		setBackground(new Color(0));
		
		flippers.add(new LeftFlipper(1, 1, Math.PI/2));
		flippers.add(new RightFlipper(3, 1, Math.PI/2));

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

		timer = new Timer(TICK, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Color orange = new Color(255, 204, 0);
		Color red = new Color(255, 0, 0);
		Color pink = new Color(255, 0, 255);
		Color green = new Color(0, 255, 0);
		Class<? extends IFlipper> cls;
		int x, y, w, h, halfL, qrtL, orientation, flipW, sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		double angle;

		for (IFlipper f : flippers) {

			cls = f.getClass();
			x = L * (int) f.getX();
			y = L * (int) f.getY();
			w = L * f.getWidth();
			h = L * f.getHeight();
			halfL = L / 2;
			qrtL = L / 4;
			angle = f.getAngle();

			if (cls.equals(RightFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;

				if (f.getAngle() == Math.PI / 2) {
					g.fillRoundRect(x, y, h, flipW, halfL, halfL);
				} else {
					g.fillRoundRect(x + (flipW * 3), y, flipW, h, halfL, halfL);
				}

				// pivot
				g.setColor(red);
				g.fillOval(x + (flipW * 3), y, halfL, halfL);

				// some stick points
				sx1 = (int) (x + w - flipW + qrtL + (qrtL * Math.sin(-angle + ((3*Math.PI)/ 2))));
				sy1 = (int) (y + qrtL + (qrtL * Math.cos(-angle + ((3*Math.PI) / 2))));
				sx2 = (int) (x + w - flipW + qrtL + (qrtL * Math.sin(-angle + (Math.PI / 2))));
				sy2 = (int) (y + qrtL + (qrtL * Math.cos(-angle + (Math.PI / 2))));

				// end point
				g.setColor(green);
				h -= flipW;
				x = (int)(x + w - flipW  + (h * Math.sin(-angle)));
				y = (int)(y + (h * Math.cos(-angle)));
				g.fillOval(x, y, halfL, halfL);

				// some more stick points
				sx3 = (int) (x + qrtL + (qrtL * Math.sin(-angle + (Math.PI / 2))));
				sy3 = (int) (y + qrtL + (qrtL * Math.cos(-angle + (Math.PI / 2))));
				sx4 = (int) (x + qrtL + (qrtL * Math.sin(-angle + ((3*Math.PI)/ 2))));
				sy4 = (int) (y + qrtL + (qrtL * Math.cos(-angle + ((3*Math.PI) / 2))));

				int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
				int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
				g.setColor(pink);
				g.fillPolygon(xPoints, yPoints, 5);

			} else if (cls.equals(LeftFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;

				if (f.getAngle() == Math.PI / 2) {
					g.fillRoundRect(x, y, h, flipW, halfL, halfL);
				} else {
					g.fillRoundRect(x, y, flipW, h, halfL, halfL);
				}

				// pivot
				g.setColor(red);
				g.fillOval(x, y, halfL, halfL);

				// some stick points
				sx1 = (int) (x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2))));
				sy1 = (int) (y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2))));
				sx2 = (int) (x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2))));
				sy2 = (int) (y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2))));


				// end point
				g.setColor(green);
				h -= flipW;
				x = (int)(x + (h * Math.sin(angle)));
				y = (int)(y + (h * Math.cos(angle)));
				g.fillOval(x, y, halfL, halfL);

				// some more stick points
				sx3 = (int) (x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2))));
				sy3 = (int) (y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2))));
				sx4 = (int) (x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2))));
				sy4 = (int) (y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2))));

				int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
				int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
				g.setColor(pink);
				g.fillPolygon(xPoints, yPoints, 5);

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