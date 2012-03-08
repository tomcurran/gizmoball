package view.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import model.Ball;
import model.Board;

import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.TriangleBumper;

public class AnimationPanel extends Canvas
{
	private Board map;
	private Boolean editMode;
	
	public AnimationPanel(Board map)
	{
		this.map = map;
		this.setBackground(Color.black);
		this.setSize(map.getWidth(), map.getHeight());
		this.editMode = true;
	}
	
	private Image bufferImage;
	@Override
	public void paint(Graphics g)
	{
		if ( (bufferImage==null) || (bufferImage.getWidth(null)!=getWidth()) || (bufferImage.getHeight(null)!=getHeight()) ){ //start without resize
			bufferImage = createImage(getWidth(), getHeight());
		}
		
		Graphics buffer = bufferImage.getGraphics();
		buffer.setColor(Color.black);
		buffer.fillRect(0, 0, getWidth(), getHeight());
		((Graphics2D) buffer).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		buffer.setColor(Color.gray);
		if(editMode){
			for(int i = 0; i < map.getWidth(); i++){
				buffer.drawLine(0, i*(this.getWidth()/20), this.getWidth() ,i*(this.getWidth()/20));
			}
			
			for(int i = 0; i < map.getHeight(); i++){
				buffer.drawLine(i*(map.getHeight()/20), 0,i*(map.getHeight()/20), this.getHeight());
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
		
		for (IGizmo gizmo: map.getGizmos())
		{
			x = (int)(xscale * gizmo.getX());
			y = (int)(yscale * gizmo.getY());
			w = (int)(xscale * gizmo.getWidth());
			h = (int)(yscale * gizmo.getHeight());
			
			switch (gizmo.getType())
			{
				case SquareBumper:
					buffer.setColor(red);
					buffer.fillRect(x, y, w, h);
					break;
					
				case CircleBumper:
					buffer.setColor(green);
					buffer.fillOval(x, y, 20, 20);
					break;
					
				case TriangleBumper:
					buffer.setColor(blue);
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
					buffer.fillPolygon(xTrianglePoints[orientation],
							yTrianglePoints[orientation], 4);
					break;
					
				case Absorber:
					buffer.setColor(pink);
					buffer.fillRect(x, y, w, h);
					break;
					
				case Flipper:
					//TODO this surely can be a lot cleaner and use relative values to allow for scaling. 
					double halfL = 20 / 2, qrtL = 20 / 4;
					Flipper f = (Flipper) gizmo;
					double flipW = w / 4;
					double angle = f.getAngle();
					
					int sx1, sx2, sx3, sx4, sy1, sy2, sy3, sy4;
					buffer.setColor(orange);

					// pivot
					fillOval(buffer, x, y, halfL);

					// some stick points
					sx1 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2)))));
					sy1 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2)))));
					sx2 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2)))));
					sy2 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2)))));

					// end point
					h -= 10;
					x = (int)(x + (h * Math.sin(angle)));
					y = (int) (y + (h * Math.cos(angle)));;
					System.out.println("X after calculating end point: " + x);
					System.out.println("Y after calculating end point: " + y);
					
					fillOval(buffer, x, y, halfL);

					// some more stick points
					sx3 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + (Math.PI / 2)))));
					sy3 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + (Math.PI / 2)))));
					sx4 = (int)Math.round((x + qrtL + (qrtL * Math.sin(angle + ((3*Math.PI)/ 2)))));
					sy4 = (int)Math.round((y + qrtL + (qrtL * Math.cos(angle + ((3*Math.PI) / 2)))));
					System.out.println("qrtl: " + qrtL);
					int xPoints[] = { sx1, sx2, sx3, sx4 };
					int yPoints[] = { sy1, sy2, sy3, sy4 };
					for (int i : yPoints) {
						System.out.println("y points : " + i);
					}
					for (int i : xPoints) {
						System.out.println("x points : " + i);
					}
					
					buffer.fillPolygon(xPoints, yPoints, 4);
					break;
			}
		}
		
		buffer.setColor(orange);
		
		for (Ball ball: map.getBalls())
		{
			x = (int)((ball.getX() - ball.getRadius()) * xscale);
			y = (int)((ball.getY() - ball.getRadius()) * yscale); 
			w = (int)(ball.getRadius() * 2 * xscale);
			h = (int)(ball.getRadius() * 2 * yscale);
			
			buffer.fillOval(x, y, w, h);
		}
		g.drawImage(bufferImage, 0, 0, null);
	}
	
	private void fillOval(Graphics g, double x, double y, double halfL) {
		g.fillOval((int)Math.round(x), (int)Math.round(y), (int)Math.round(halfL), (int)Math.round(halfL));
	}
}
