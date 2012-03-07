package view.window;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.MagicKeyListener;
import controller.TriggerHandler;

import exceptions.BadFileException;

import model.Board;
import model.IPhysicsEngine;
import model.Loader;
import model.physics.MitPhysicsEngineWrapper;

public class MainWindow extends JFrame implements ActionListener, Observer
{
	private static final int WIDTH = 20, HEIGHT = 20;
	private static final int TICK = 20;
	private Board map;
	private IPhysicsEngine engine;
	private double xscale, yscale;
	private Timer timer;
	private JPanel panel;
	private TriggerHandler handler;
	
	public MainWindow()
	{
		super("Physics demo");
		//this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		map = new Board(WIDTH, HEIGHT);
		xscale = (double)this.getWidth() / WIDTH;
		yscale = (double)this.getHeight() / HEIGHT;
		
		engine = new MitPhysicsEngineWrapper();
		
		try
		{
			Loader loader = new Loader("saves/save1.txt");
			loader.parseFile(engine);
			loader.loadItems(map);
			
			handler = new TriggerHandler(loader.getKeyUpTriggers(), loader.getKeyDownTriggers());
			MagicKeyListener listener = new MagicKeyListener(handler);
			addKeyListener(listener);
			requestFocus();
			
			engine.initialise(map);
			
			
			//panel = new AnimationPanel(map);
			panel = new PhysicsPanel((MitPhysicsEngineWrapper)engine);
			panel.setPreferredSize(new Dimension(500,500));
			setContentPane(panel);
			this.pack();
			
			timer = new Timer(TICK, this);
			timer.start();
		}
		catch (IOException ex)
		{
			System.out.println("Couldn't load file.");
		}
		catch (BadFileException ex)
		{
			System.out.println("Couldn't parse file.");
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		engine.calculateState((double)TICK / 750);
		panel.repaint();
	}

	@Override
	public void update(Observable source, Object arg)
	{
		//this.repaint();
	}
	
	public static void main(String[] args)
	{
		MainWindow window = new MainWindow();
		window.setVisible(true);
	}
}
