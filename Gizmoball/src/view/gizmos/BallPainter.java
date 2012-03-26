package view.gizmos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import model.Ball;

public class BallPainter
{
	public void paint(Graphics2D g, Ball ball)
	{
		double r = ball.getRadius();
		
		g.setColor(Color.YELLOW);
		g.fill(new Ellipse2D.Double(ball.getX() - r, ball.getY() - r, r * 2, r * 2));
	}
}
