package view.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import model.Ball;
import model.Board;
import model.RotatablePoint;
import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.TriangleBumper;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel {
	private Board board;
	private Boolean editMode;

	private Image bufferImage;
	private int validSquareX;
	private int validSquareY;
	private int validSquareW;
	private int validSquareH;
	private Color validColor;
	private boolean locationIndicator;
	private double xscale;
	private double yscale;

	public AnimationPanel(Board board) {
		locationIndicator = false;
		this.board = board;
		this.setBackground(Color.black);
		int width = board.getWidth() * 20;
		int height = board.getHeight() * 20;
		this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setMinimumSize(new Dimension(width, height));
		this.editMode = true;
	}

	public double getScaleX() {
		return xscale;
	}

	public double getScaleY() {
		return yscale;
	}
	
	@Override
	public void paint(Graphics g) {
		if ((bufferImage == null) || (bufferImage.getWidth(null) != getWidth())
				|| (bufferImage.getHeight(null) != getHeight())) { // start without resize
			bufferImage = createImage(getWidth(), getHeight());
		}

		int boardWidth = board.getWidth();
		int boardHeight = board.getHeight();
		xscale = Math.round(this.getWidth() / boardWidth);
		yscale = Math.round(this.getHeight() / boardHeight);

		Graphics buffer = bufferImage.getGraphics();
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, (int)Math.round((boardWidth * xscale)), (int)Math.round((boardHeight * yscale)));
		((Graphics2D) buffer).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		buffer.setColor(Color.gray);

		if (editMode) {
			for (int i = 0; i < boardHeight; i++) {
				buffer.drawLine(0, (int) Math.round((i * yscale)), this.getWidth(), (int)Math.round((i * yscale)));
			}

			for (int i = 0; i < boardWidth; i++) {
				buffer.drawLine((int)Math.round((i * xscale)), 0, (int)Math.round((i * xscale)), this.getHeight());
			}
		}

		int x, y, w, h;

		for (IGizmo gizmo : board.getGizmos()) {
			x = (int) (xscale * gizmo.getX());
			y = (int) (yscale * gizmo.getY());
			w = (int) (xscale * gizmo.getWidth());
			h = (int) (yscale * gizmo.getHeight());

			switch (gizmo.getType()) {
			case SquareBumper:
				buffer.setColor(Color.RED);
				buffer.fillRect(x, y, w, h);
				break;

			case CircleBumper:
				buffer.setColor(Color.GREEN);
				buffer.fillOval(x, y, w, h);
				break;

			case TriangleBumper:
				buffer.setColor(Color.BLUE);
				int gx = gizmo.getX();
				int gy = gizmo.getY();
				
				int orientation = gizmo.getOrientation();
				RotatablePoint centre = new RotatablePoint(gx + 0.5, gy + 0.5);
				RotatablePoint p1 = new RotatablePoint(gx, gy).rotate(centre, orientation);
				RotatablePoint p2 = new RotatablePoint(gx + 1, gy).rotate(centre, orientation);
				RotatablePoint p3 = new RotatablePoint(gx, gy + 1).rotate(centre, orientation);

				int xpoints[] = new int[] {p1.getScaledX(xscale), p2.getScaledX(xscale), p3.getScaledX(xscale)};
				int ypoints[] = new int[] {p1.getScaledY(yscale), p2.getScaledY(yscale), p3.getScaledY(yscale)};
				
				buffer.fillPolygon(xpoints, ypoints, 3);
				
				break;

			case Absorber:
				buffer.setColor(Color.MAGENTA);
				buffer.fillRect(x, y, w, h);
				break;

			case Flipper:
				flipper((Flipper)gizmo, buffer);
				break;
			}

		}

		buffer.setColor(Color.yellow);

		for (Ball ball : board.getBalls()) {
			x = (int) ((ball.getX() - ball.getRadius()) * xscale);
			y = (int) ((ball.getY() - ball.getRadius()) * yscale);
			w = (int) (ball.getRadius() * xscale * 2);
			h = (int) (ball.getRadius() * yscale * 2);
			
			//System.out.println("Ball x, y, w, h, xscale, yscale: " + x + ", " + y + ", " + w + ", " + h + ", " + xscale + ", " + yscale);

			buffer.fillOval(x, y, w, h);
		}

		if (locationIndicator) {
			buffer.setColor(validColor);
			buffer.drawRect((int)Math.round(validSquareX * xscale), (int)Math.round(validSquareY * yscale),
					(int)Math.round(validSquareW * xscale), (int)Math.round(validSquareH * yscale));
		}

		g.drawImage(bufferImage, 0, 0, null);
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
		
		int xPoints[] = { line1s.getScaledX(xscale), line1e.getScaledX(xscale), line2e.getScaledX(xscale), line2s.getScaledX(xscale) };
		int yPoints[] = { line1s.getScaledY(yscale), line1e.getScaledY(yscale), line2e.getScaledY(yscale), line2s.getScaledY(yscale) };
		
		g.setColor(Color.ORANGE);
		g.fillPolygon(xPoints, yPoints, 4);
		g.fillOval(pivotcircle.getScaledX(xscale), pivotcircle.getScaledY(yscale), (int)Math.round(0.5 * xscale), (int)Math.round(0.5 * yscale));
		g.fillOval(endcircle.getScaledX(xscale), endcircle.getScaledY(yscale), (int)Math.round(0.5 * xscale), (int)Math.round(0.5 * yscale));
	}

	public void setMode() {
		editMode = !editMode;
	}

	public void removeLocationIndicator() {
		locationIndicator = false;
		validSquareX = 0;
		validSquareY = 0;
		validSquareW = 0;
		validSquareH = 0;
		paint(this.getGraphics());
	}

	public void setLocationIndicator(int x, int y, int w, int h, Color c) {
		locationIndicator = true;
		validSquareX = x;
		validSquareY = y;
		validSquareW = w;
		validSquareH = h;
		validColor = c;
		paint(this.getGraphics());
	}

}