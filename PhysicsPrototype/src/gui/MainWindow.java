package gui;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.Timer;

import physicsprototype.Ball;
import physicsprototype.GizmoMap;
import physicsprototype.IGizmo;
import physicsprototype.IPhysicsEngine;
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
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		map = new GizmoMap(WIDTH, HEIGHT);
		xscale = (double)this.getWidth() / WIDTH;
		yscale = (double)this.getHeight() / HEIGHT;
		
		Ball ball = new Ball(4.5, 4.5, 0.25, 1);
		ball.addObserver(this);
		map.addBall(ball);
		
		ball = new Ball(4.5, 6.5, 0.25, 1);
		ball.addObserver(this);
		map.addBall(ball);
		
		//IGizmo gizmo = new TriangleBumper(4, 19, 0);
		//map.addGizmo(gizmo);
		
		ball = new Ball(4.3, 19, 0.25, 1);
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
		
		for (Ball ball: map.getBalls())
		{
			int x = (int)((ball.getX() - ball.getRadius()) * xscale);
			int y = (int)((ball.getY() - ball.getRadius()) * yscale); 
			int width = (int)(ball.getRadius() * 2 * xscale);
			int height = (int)(ball.getRadius() * 2 * yscale);
			
			g.fillOval(x, y, width, height);
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
