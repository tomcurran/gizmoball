package view.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Ball;
import model.Board;
import model.gizmos.AbsorberGizmo;
import model.gizmos.CircleBumper;
import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;
import view.board.AbsorberView;
import view.board.BallView;
import view.board.CircleView;
import view.board.FlipperView;
import view.board.GizmoView;
import view.board.ScalableComponent;
import view.board.SquareView;
import view.board.TriangleView;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel implements Observer {

	private final static int L = 20;

	private Board board;
	private Point scale;
	private Boolean editMode;

	private boolean validation;
	private int validationX;
	private int validationY;
	private int validationW;
	private int validationH;
	private Color validationColor;

	public AnimationPanel(Board board) {
		this.validation = false;
		this.board = board;
		this.setBackground(Color.black);
		int width = board.getWidth() * AnimationPanel.L;
		int height = board.getHeight() * AnimationPanel.L;
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.editMode = true;
		this.scale = new Point();
		((Observable) board).addObserver(this);
	}

	public double getScaleX() {
		return scale.x;
	}

	public double getScaleY() {
		return scale.y;
	}

	public Point getScale() {
		return scale;
	}

	public void toggleMode() {
		editMode = !editMode;
	}

	public void removeValidation() {
		validation = false;
		validationX = 0;
		validationY = 0;
		validationW = 0;
		validationH = 0;
		repaint();
	}

	public void setValidation(int x, int y, int w, int h, Color c) {
		validation = true;
		validationX = x;
		validationY = y;
		validationW = w;
		validationH = h;
		validationColor = c;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();

		scale.x = (int)Math.round((double)getWidth() / boardWidth);
		scale.y = (int)Math.round((double)getHeight() / boardHeight);

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (editMode) {
			g.setColor(Color.GRAY);
			for (int i = 0; i < boardHeight; i++) {
				g.drawLine(0, (int) Math.round((i * scale.y)), (int)(scale.x * boardWidth), (int)Math.round((i * scale.y)));
			}
			for (int i = 0; i < boardWidth; i++) {
				g.drawLine((int)Math.round((i * scale.x)), 0, (int)Math.round((i * scale.x)), (int)(scale.y * boardHeight));
			}
		}

		Component[] components = getComponents();
		for (int i = 0; i < components.length; i++) {
			((ScalableComponent)components[i]).setScale(scale);
			components[i].paint(g);
		}

		if (validation) {
			g.setColor(validationColor);
			g.drawRect((int)Math.round(validationX * scale.x), (int)Math.round(validationY * scale.y), (int)Math.round(validationW * scale.x), (int)Math.round(validationH * scale.y));
		}

	}

	// TODO don't use catching
	@Override
	public void update(Observable o, Object arg) {
		List<IGizmo> gizmos = board.getGizmos();
		List<Ball> balls = board.getBalls();
		Component[] components = getComponents();

		if (gizmos.size() + balls.size() > components.length) {
			// add gizmos
			List<IGizmo> gs = new ArrayList<IGizmo>();
			List<Ball> bs = new ArrayList<Ball>();
			for (int i  = 0; i < components.length; i++) {
				try {
					gs.add(((GizmoView)components[i]).getGizmo());
				} catch (ClassCastException e) {
					bs.add(((BallView)components[i]).getBall());
				}
			}
			for (IGizmo gizmo : gizmos) {
				if (!gs.contains(gizmo)) {
					switch (gizmo.getType()) {
					case SquareBumper:
						add(new SquareView((SquareBumper) gizmo));
						break;
					case CircleBumper:
						add(new CircleView((CircleBumper) gizmo));
						break;
					case TriangleBumper:
						add(new TriangleView((TriangleBumper) gizmo));
						break;
					case Flipper:
						add(new FlipperView((Flipper) gizmo));
						break;
					case Absorber:
						add(new AbsorberView((AbsorberGizmo) gizmo));
						break;
					}
					break;
				}
			}
			for (Ball ball : balls) {
				if (!bs.contains(ball)) {
					add(new BallView(ball));
				}
			}
		} else if (gizmos.size() + balls.size() < components.length) {
			// remove gizmos
			for (int i = 0; i < components.length; i++) {
				try {
					if (!gizmos.contains(((GizmoView)components[i]).getGizmo())) {
						remove(components[i]);
						break;
					}
				} catch (ClassCastException e) {
					if (!balls.contains(((BallView)components[i]).getBall())) {
						remove(components[i]);
						break;
					}
				}
			}
		}

		validate();
		repaint();
	}

}