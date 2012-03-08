package view.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import model.Ball;
import model.Board;
import model.gizmos.IGizmo;
import model.gizmos.TriangleBumper;

public class AnimationPanel extends Canvas
{
	private Board map;
	
	public AnimationPanel(Board map)
	{
		this.map = map;
		this.setBackground(Color.black);
		this.setSize(map.getWidth(), map.getHeight());
	}
	
	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.gray);
		if(true){
			for(int i = 0; i < map.getWidth(); i++){
				g.drawLine(0, i*(this.getWidth()/20), this.getWidth() ,i*(this.getWidth()/20));
			}
			
			for(int i = 0; i < map.getHeight(); i++){
				g.drawLine(i*(map.getHeight()/20), 0,i*(map.getHeight()/20), this.getHeight());
			}
		}
		
		double xscale = this.getWidth() / map.getWidth();
		double yscale = this.getHeight() / map.getHeight();
		
		g.setColor(new Color(0));
		
		
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
					System.out.println("Circle bumper to paint");
					g.setColor(green);
					System.out.println("Circle x: " +x);
					System.out.println("Circle y: " + y );
					System.out.println("Circle w: " + w );
					System.out.println("Circle h: " + h );
					g.fillOval(x, y, 20, 20);
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
