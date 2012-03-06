package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import model.Ball;
import model.GizmoMap;
import model.IGizmo;
import model.TriangleBumper;

public class AnimationPanel extends JPanel
{
	private GizmoMap map;
	
	public AnimationPanel(GizmoMap map)
	{
		this.map = map;
	}
	
	@Override
	public void paint(Graphics g)
	{
		double xscale = this.getWidth() / map.getWidth();
		double yscale = this.getHeight() / map.getHeight();
		
		g.setColor(new Color(0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color orange = new Color(255, 204, 0);
		Color pink = new Color(255, 0, 255);
		
		int x, y, w, h;
		
		for (IGizmo gizmo: map.getGizmos())
		{
			x = (int)(xscale * gizmo.getX());
			y = (int)(yscale * gizmo.getY());
			w = (int)(xscale * gizmo.getWidth());
			h = (int)(yscale * gizmo.getHeight());
			
			switch (gizmo.getType())
			{
				case SquareBumper:
					g.setColor(red);
					g.fillRect(x, y, w, h);
					break;
					
				case CircleBumper:
					g.setColor(green);
					g.fillOval(x, y, w, h);
					break;
					
				case TriangleBumper:
					g.setColor(blue);
					int xTrianglePoints[][] = {
							{ x,   x+w, x,   x   },
							{ x,   x+w, x,   x   },
							{ x,   x+w, x+w, x   },
							{ x+w, x+w, x,   x+w }
					};
					int yTrianglePoints[][] = {
							{ y, y+h, y+h, y },
							{ y, y,   y+h, y },
							{ y, y,   y+h, y },
							{ y, y+h, y+h, y }
					};
					int orientation = ((TriangleBumper)gizmo).getOrientation();
					g.fillPolygon(xTrianglePoints[orientation],
							yTrianglePoints[orientation], 4);
					break;
					
				case Absorber:
					g.setColor(pink);
					g.fillRect(x, y, w, h);
					break;
			}
		}
		
		g.setColor(orange);
		
		for (Ball ball: map.getBalls())
		{
			x = (int)((ball.getX() - ball.getRadius()) * xscale);
			y = (int)((ball.getY() - ball.getRadius()) * yscale); 
			w = (int)(ball.getRadius() * 2 * xscale);
			h = (int)(ball.getRadius() * 2 * yscale);
			
			g.fillOval(x, y, w, h);
		}
	}
}
