package view.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import model.Ball;
import model.Board;
import model.RotatablePoint;
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
//
//				if (gizmo.getClass().equals(RightFlipper.class)) {
//					rightFlipper(gizmo, buffer);
//				} else {
//					leftFlipper(gizmo, buffer);
//				}
				flipper((Flipper)gizmo, buffer);
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

	
	private void flipper(Flipper flipper, Graphics g)
	{
		int orientation = flipper.getOrientation();
		int x = flipper.getX(), y = flipper.getY();
		double angle = flipper.getAngle();
		
		RotatablePoint pivot = new RotatablePoint(x + 0.25, y + 0.25);
		RotatablePoint centre = new RotatablePoint(x + 1.0, y + 1.0);
		
		RotatablePoint pivotcircle = pivot.rotate(centre, orientation);
		pivotcircle.x -= 0.25;
		pivotcircle.y -= 0.25;
				
		RotatablePoint endcircle = new RotatablePoint(x + 0.25, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);
		endcircle.x -= 0.25;
		endcircle.y -= 0.25;
	
		RotatablePoint line1s = new RotatablePoint(x, y + 0.25).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line1e = new RotatablePoint(x, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line2s = new RotatablePoint(x + 0.5, y + 0.25).rotate(pivot, angle).rotate(centre, orientation);
		RotatablePoint line2e = new RotatablePoint(x + 0.5, y + 1.75).rotate(pivot, angle).rotate(centre, orientation);
		
		int xPoints[] = { line1s.getScaledX(Board.L), line1e.getScaledX(Board.L), line2e.getScaledX(Board.L), line2s.getScaledX(Board.L) };
		int yPoints[] = { line1s.getScaledY(Board.L), line1e.getScaledY(Board.L), line2e.getScaledY(Board.L), line2s.getScaledY(Board.L) };
		
		g.setColor(Color.orange);
		g.fillPolygon(xPoints, yPoints, 4);
		g.fillOval(pivotcircle.getScaledX(Board.L), pivotcircle.getScaledY(Board.L), (int)Math.round(0.5 * Board.L), (int)Math.round(0.5 * Board.L));
		g.fillOval(endcircle.getScaledX(Board.L), endcircle.getScaledY(Board.L), (int)Math.round(0.5 * Board.L), (int)Math.round(0.5 * Board.L));
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
