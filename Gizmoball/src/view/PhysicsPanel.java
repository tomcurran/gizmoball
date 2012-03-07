package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.physics.IPhysicsObject;
import model.physics.MitPhysicsEngineWrapper;
import model.physics.PhysicsBall;
import model.physics.PhysicsGizmo;
import model.physics.RotatingCircle;
import model.physics.RotatingWall;
import model.physics.mit.Circle;
import model.physics.mit.LineSegment;


public class PhysicsPanel extends JPanel
{
	private MitPhysicsEngineWrapper engine;
	
	public PhysicsPanel(MitPhysicsEngineWrapper engine)
	{
		this.engine = engine;
	}
	
	@Override
	public void paint(Graphics g)
	{
		double xscale = (double)this.getWidth() / engine.map.getWidth();
		double yscale = (double)this.getHeight() / engine.map.getHeight();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		
		for (PhysicsGizmo gizmo: engine.objects.values())
		{
			for (IPhysicsObject object: gizmo.getPhysicsObjects())
			{
				switch (object.getType())
				{
					case Circle:
						Circle circle = (Circle)object;
						double r = circle.getRadius();
						
						if (r == 0)
							r = 0.2;
						
						paintCircle(g, Color.BLUE, circle.getCenter().x(), circle.getCenter().y(), r, xscale, yscale);
						break;
						
					case LineSegment:
						LineSegment line = (LineSegment)object;
						paintLine(g, Color.BLUE, line.p1().x(), line.p1().y(), line.p2().x(), line.p2().y(), xscale, yscale);
						break;
						
					case RotatingCircle:
						Circle rcircle = ((RotatingCircle)object).getCircle();
						paintCircle(g, Color.cyan, rcircle.getCenter().x(), rcircle.getCenter().y(), rcircle.getRadius(), xscale, yscale);
						break;
						
					case RotatingWall:
						LineSegment rline = ((RotatingWall)object).getLine();
						paintLine(g, Color.CYAN, rline.p1().x(), rline.p1().y(), rline.p2().x(), rline.p2().y(), xscale, yscale);
						break;
				}
			}
		}
		
		g.setColor(new Color(255, 128, 255));
		
		for (PhysicsBall ball: engine.balls)
		{
			double br = ball.getBall().getRadius();
			double bx = ball.getBall().getX() - br;
			double by = ball.getBall().getY() - br;
			
			g.drawOval((int)(bx * xscale), (int)(by * yscale), (int)(2 * br * xscale), (int)(2 * br * yscale));
			break;
		}
	}
	
	
	private void paintCircle(Graphics g, Color c, double x, double y, double r, double xscale, double yscale)
	{
		g.setColor(c);
		g.drawOval((int)Math.round((x - r) * xscale), (int)Math.round((y - r) * yscale), (int)Math.round(2 * r * xscale), (int)Math.round(2 * r * yscale));
	}
	
	
	private void paintLine(Graphics g, Color c, double x1, double y1, double x2, double y2, double xscale, double yscale)
	{
		g.setColor(c);
		g.drawLine((int)Math.round(x1 * xscale), (int)Math.round(y1 * yscale), (int)Math.round(x2 * xscale), (int)Math.round(y2 * yscale));
	}
}
