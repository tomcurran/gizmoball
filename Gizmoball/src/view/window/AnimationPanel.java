package view.window;

import java.awt.Canvas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.Ball;
import model.Board;
import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.RightFlipper;
import model.gizmos.TriangleBumper;

public class AnimationPanel extends Canvas {
	private Board map;
	private Boolean editMode;

	private Image bufferImage;
	private int validSquareX;
	private int validSquareY;
	private int validSquareW;
	private int validSquareH;
	private Color validColor;
	private boolean mouseOver;

	public AnimationPanel(Board map) {
		mouseOver = false;
		this.map = map;
		this.setBackground(Color.black);
		this.setSize(map.getWidth(), map.getHeight());
		System.out.println(map.getWidth() + " " + map.getHeight());

		this.setPreferredSize(new Dimension(map.getWidth(), map.getHeight()));
		this.setMaximumSize(new Dimension(map.getWidth(), map.getHeight()));
		this.setMinimumSize(new Dimension(map.getWidth(), map.getHeight()));
		this.editMode = true;
	}

	@Override
	public void paint(Graphics g) {
		if ((bufferImage == null) || (bufferImage.getWidth(null) != getWidth())
				|| (bufferImage.getHeight(null) != getHeight())) { // start
																	// without
																	// resize
			bufferImage = createImage(getWidth(), getHeight());
		}

		Graphics buffer = bufferImage.getGraphics();
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, map.getWidth(), map.getHeight());
		((Graphics2D) buffer).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		buffer.setColor(Color.gray);
		if (editMode) {
			for (int i = 0; i < map.getWidth() / Board.L; i++) {
				buffer.drawLine(0, i * (map.getWidth() / Board.L), map.getWidth(), i
						* (map.getWidth() / Board.L));
			}

			for (int i = 0; i < map.getHeight() / Board.L; i++) {
				buffer.drawLine(i * (map.getHeight() / Board.L), 0,
						i * (map.getHeight() / Board.L), map.getHeight());
			}
		}

		double xscale = this.getWidth() / map.getWidth();
		double yscale = this.getHeight() / map.getHeight();

		buffer.setColor(new Color(0));

		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color orange = new Color(255, 204, 0);
		Color pink = new Color(255, 0, 255);

		int x, y, w, h;

		for (IGizmo gizmo : map.getGizmos()) {
			x = (int) (Board.L * gizmo.getX());
			y = (int) (Board.L * gizmo.getY());
			w = (int) (Board.L * gizmo.getWidth());
			h = (int) (Board.L * gizmo.getHeight());

			switch (gizmo.getType()) {
			case SquareBumper:
				buffer.setColor(red);
				buffer.fillRect(x, y, w, h);
				break;

			case CircleBumper:
				buffer.setColor(green);
				buffer.fillOval(x, y, w, h);
				break;

			case TriangleBumper:
				buffer.setColor(blue);
				int xTrianglePoints[][] = { { x, x + w, x, x },
						{ x, x + w, x, x }, { x, x + w, x + w, x },
						{ x + w, x + w, x, x + w } };

				int yTrianglePoints[][] = { { y, y + h, y + h, y },
						{ y, y, y + h, y }, { y, y, y + h, y },
						{ y, y + h, y + h, y } };
				int orientation = ((TriangleBumper) gizmo).getOrientation();
				buffer.fillPolygon(xTrianglePoints[orientation],
						yTrianglePoints[orientation], 4);
				break;

			case Absorber:
				buffer.setColor(pink);
				buffer.fillRect(x, y, w, h);
				break;

			case Flipper:

				if (gizmo.getClass().equals(RightFlipper.class)) {
					rightFlipper(gizmo, buffer);
				} else {
					leftFlipper(gizmo, buffer);
				}

				break;
			}

		}

		buffer.setColor(orange);

		for (Ball ball : map.getBalls()) {
			x = (int) ((ball.getX() - ball.getRadius()) * xscale);
			y = (int) ((ball.getY() - ball.getRadius()) * yscale);
			w = (int) (ball.getRadius() * 2 * xscale);
			h = (int) (ball.getRadius() * 2 * yscale);

			buffer.fillOval(x, y, w, h);
		}

		if (mouseOver) {
			buffer.setColor(validColor);

			buffer.drawRect(validSquareX * Board.L, validSquareY * Board.L,
					validSquareW * Board.L, validSquareH * Board.L);

		}
		g.drawImage(bufferImage, 0, 0, null);
	}

	private void fillOval(Graphics g, double x, double y, double halfL) {
		g.fillOval((int) Math.round(x), (int) Math.round(y),
				(int) Math.round(halfL), (int) Math.round(halfL));
	}

	private void leftFlipper(IGizmo gizmo, Graphics g) {
		double x = Board.L * gizmo.getX();
		double y = Board.L * gizmo.getY();
		double w = Board.L * gizmo.getWidth();
		double h = Board.L * gizmo.getHeight();
		double flipW = w / 4;
		double halfL = Board.L / 2, qrtL = Board.L / 4;
		Flipper f = (Flipper) gizmo;
		double angle = f.getAngle();
		int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		g.setColor(Color.orange);

		// pivot
		fillOval(g, x, y, halfL);

		// some stick points
		sx1 = (int) Math.round((x + qrtL + (qrtL * Math.sin(angle
				+ ((3 * Math.PI) / 2)))));
		sy1 = (int) Math.round((y + qrtL + (qrtL * Math.cos(angle
				+ ((3 * Math.PI) / 2)))));
		sx2 = (int) Math.round((x + qrtL + (qrtL * Math.sin(angle
				+ (Math.PI / 2)))));
		sy2 = (int) Math.round((y + qrtL + (qrtL * Math.cos(angle
				+ (Math.PI / 2)))));

		// end point
		h -= flipW;
		x = (x + (h * Math.sin(angle)));
		y = (y + (h * Math.cos(angle)));
		fillOval(g, x, y, halfL);

		// some more stick points
		sx3 = (int) Math.round((x + qrtL + (qrtL * Math.sin(angle
				+ (Math.PI / 2)))));
		sy3 = (int) Math.round((y + qrtL + (qrtL * Math.cos(angle
				+ (Math.PI / 2)))));
		sx4 = (int) Math.round((x + qrtL + (qrtL * Math.sin(angle
				+ ((3 * Math.PI) / 2)))));
		sy4 = (int) Math.round((y + qrtL + (qrtL * Math.cos(angle
				+ ((3 * Math.PI) / 2)))));

		int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
		int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
		g.fillPolygon(xPoints, yPoints, 5);
	}

	private void rightFlipper(IGizmo gizmo, Graphics g) {
		double x = Board.L * gizmo.getX();
		double y = Board.L * gizmo.getY();
		double w = Board.L * gizmo.getWidth();
		double h = Board.L * gizmo.getHeight();
		double flipW = w / 4;
		double halfL = Board.L / 2, qrtL = Board.L / 4;
		Flipper f = (Flipper) gizmo;
		double angle = f.getAngle();
		int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
		g.setColor(Color.orange);

		// pivot
		fillOval(g, x + (flipW * 3), y, halfL);

		// some stick points
		sx1 = (int) Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle
				+ ((3 * Math.PI) / 2)))));
		sy1 = (int) Math.round((y + qrtL + (qrtL * Math.cos(-angle
				+ ((3 * Math.PI) / 2)))));
		sx2 = (int) Math.round((x + w - flipW + qrtL + (qrtL * Math.sin(-angle
				+ (Math.PI / 2)))));
		sy2 = (int) Math.round((y + qrtL + (qrtL * Math.cos(-angle
				+ (Math.PI / 2)))));

		// end point
		h -= flipW;
		x = (x + w - flipW + (h * Math.sin(-angle)));
		y = (y + (h * Math.cos(-angle)));

		fillOval(g, x, y, halfL);

		// some more stick points
		sx3 = (int) Math.round((x + qrtL + (qrtL * Math.sin(-angle
				+ (Math.PI / 2)))));
		sy3 = (int) Math.round((y + qrtL + (qrtL * Math.cos(-angle
				+ (Math.PI / 2)))));
		sx4 = (int) Math.round((x + qrtL + (qrtL * Math.sin(-angle
				+ ((3 * Math.PI) / 2)))));
		sy4 = (int) Math.round((y + qrtL + (qrtL * Math.cos(-angle
				+ ((3 * Math.PI) / 2)))));

		int xPoints[] = { sx1, sx2, sx3, sx4, sx1 };
		int yPoints[] = { sy1, sy2, sy3, sy4, sy1 };
		g.fillPolygon(xPoints, yPoints, 5);
	}

	public void setMode() {
		editMode = !editMode;
	}

	public void addMouseFollower(int x, int y, int w, int h, Color c) {

		validSquareX = x;
		validSquareY = y;
		validSquareW = w;
		validSquareH = h;
		validColor = c;
		paint(this.getGraphics());

	}

	public void mouseOverGrid() {
		mouseOver = !mouseOver;
		paint(this.getGraphics());
	}
}
