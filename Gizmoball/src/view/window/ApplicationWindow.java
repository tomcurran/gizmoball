package view.window;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;
import model.IPhysicsEngine;
import model.physics.MitPhysicsEngineWrapper;
import controller.Controller;

/**
 * 
 * @author Andew White - 200939787
 * 
 *         ApplicationWindow
 * 
 * @version 1.0 Started development of the GizmoBall GUI.
 * 
 */
public class ApplicationWindow extends JFrame implements Observer,
		ActionListener {

	public static final int L = 20;

	private ButtonArea buttonArea;
	private Controller controller;
	private AnimationPanel boardView;

	private IPhysicsEngine physics;
	private Board model;

	private JMenu file;
	private JMenu edit;

	private JMenuItem save;
	private JMenuItem load;

	private JMenuBar menu;

	private static final int TICK = 20;
	private Timer timer;

	public ApplicationWindow(Board model) {
		super("Gizmoball");

		this.model = model;
		model.addObserver(this);

		buttonArea = new ButtonArea(controller);

		boardView = new AnimationPanel(model);

		physics = new MitPhysicsEngineWrapper();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		menu = new JMenuBar();

		createMenu();
		controller = new Controller(physics, model, this);
		this.setJMenuBar(menu);
		JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(400, 400));
		panel.add(boardView);
		JPanel contentPane = new JPanel();

		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(500, 410));

		contentPane.add(panel, BorderLayout.WEST);
		contentPane.add(buttonArea.getButtonArea(), BorderLayout.EAST);

		this.setResizable(false);

		setContentPane(contentPane);
		pack();
		requestFocus();

		//timer = new Timer(TICK, this);
		// timer.start();

	}

	private void createMenu() {
		file = new JMenu("File");
		edit = new JMenu("Edit");

		save = new JMenuItem("Save");
		load = new JMenuItem("Load");

		file.add(save);
		file.add(load);

		menu.add(file);
	}

	public void paint(Graphics g) {
		super.paint(g);

		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		boardView.repaint();
	}

	public boolean isFocusable() {
		return true;
	}

	@Override
	public void update(Observable obs, Object obj) {
		repaint();
	}

	public void addButtonListeners(ActionListener buttonListener) {
		buttonArea.addListeners(buttonListener);
	}

	public void addGridListner(MouseListener gridListener) {
		boardView.addMouseListener(gridListener);
		boardView.addMouseMotionListener((MouseMotionListener) gridListener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		physics.calculateState((double) TICK / 750);
		boardView.repaint();
	}

	public void addMenuListner(ActionListener savesListener) {
		save.addActionListener(savesListener);
		load.addActionListener(savesListener);
	}

	public void flipMode() {
		buttonArea.activateEditButtons();

		boardView.setMode();
	}
	

	public void addEditKeyListener(KeyListener linkListener) {
		boardView.addKeyListener(linkListener);

	}

}
