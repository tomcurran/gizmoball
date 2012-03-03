package view;

import java.awt.Color;
import java.awt.Component;
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

import model.AbsorberGizmo;
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

	private static final int TICK = 20;

	private Board model;
	private Timer timer;
	private MagicKeyListener listener;
	private BoardController controller;

	public BoardView(Board model, TriggerSystem trigsys) {
		this.model = model;
		setPreferredSize(new Dimension((int) Board.L * model.getWidth(),
				(int) Board.L * model.getHeight()));
		setBackground(Color.BLACK);

		Class<? extends IBoardItem> cls;
		for (IBoardItem boardItem : model.getItems()) {
			cls = boardItem.getClass();
			if (cls.equals(SquareGizmo.class)) {
				add(new SquareView((SquareGizmo) boardItem));
			} else if (cls.equals(CircleGizmo.class)) {
				add(new CircleView((CircleGizmo) boardItem));
			} else if (cls.equals(TriangleGizmo.class)) {
				add(new TriangleView((TriangleGizmo) boardItem));
			} else if (cls.equals(LeftFlipper.class)) {
				add(new LeftFlipperView((LeftFlipper) boardItem));
			} else if (cls.equals(RightFlipper.class)) {
				add(new RightFlipperView((RightFlipper) boardItem));
			} else if (cls.equals(AbsorberGizmo.class)) {
				add(new AbsorberView((AbsorberGizmo) boardItem));
			} else if (cls.equals(Ball.class)) {
				add(new BallView((Ball) boardItem));
			}
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

		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			components[i].paint(g);
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		this.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		List<IFlipper> flippers = model.getFlippers();
		for (IFlipper flipper : flippers) {
			if (flipper.getAngularMomentum() != 0) {
				flipper.setAngle(flipper.getAngle() + flipper.getAngularMomentum()
						* (double) TICK / 1000);
			}
		}
	}

	public boolean isFocusable() {
		return true;
	}

}