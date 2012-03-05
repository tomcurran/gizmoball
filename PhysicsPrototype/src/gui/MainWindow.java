package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.Timer;

import physicsprototype.Ball;
import physicsprototype.CircleBumper;
import physicsprototype.GizmoMap;
import physicsprototype.IGizmo;
import physicsprototype.IPhysicsEngine;
import physicsprototype.SquareBumper;
import physicsprototype.TriangleBumper;
import physicswrapper.MitPhysicsEngineWrapper;

public class MainWindow extends JFrame implements ActionListener, Observer
{
	private static final int WIDTH = 20, HEIGHT = 20;
	private static final int TICK = 20;
	private GizmoMap map;
	private IPhysicsEngine engine;
	private double xscale, yscale;
	private Timer timer;
	
	public MainWindow()
	{
		super("Physics demo");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		map = new GizmoMap(WIDTH, HEIGHT);
		xscale = (double)this.getWidth() / WIDTH;
		yscale = (double)this.getHeight() / HEIGHT;
		
		Ball ball = new Ball(4.5, 4.5, 0.25, 1);
		ball.addObserver(this);
		map.addBall(ball);
		
		ball = new Ball(6.5, 6.5, 0.25, 1);
		ball.addObserver(this);
		map.addBall(ball);
		
		IGizmo gizmo = new TriangleBumper(4, 11, 3);
		map.addGizmo(gizmo);
		
		gizmo = new SquareBumper(6, 11);
		map.addGizmo(gizmo);
		
		gizmo = new CircleBumper(8, 11);
		map.addGizmo(gizmo);
		
		ball = new Ball(8.3, 3, 0.25, 1);
		ball.addObserver(this);
		map.addBall(ball);
		
		engine = new MitPhysicsEngineWrapper();
		engine.initialise(map);
		
		timer = new Timer(TICK, this);
		timer.start();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		g.setColor(new Color(0));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color orange = new Color(255, 204, 0);
		Color pink = new Color(255, 0, 255);
		
		int x, y, w, h;
		
		g.setColor(blue);
		
		for (Ball ball: map.getBalls())
		{
			x = (int)((ball.getX() - ball.getRadius()) * xscale);
			y = (int)((ball.getY() - ball.getRadius()) * yscale); 
			w = (int)(ball.getRadius() * 2 * xscale);
			h = (int)(ball.getRadius() * 2 * yscale);
			
			g.fillOval(x, y, w, h);
		}
		
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
			}
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		engine.calculateState((double)TICK / 750);
	}

	@Override
	public void update(Observable source, Object arg)
	{
		this.repaint();
	}
	
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
	}
}
