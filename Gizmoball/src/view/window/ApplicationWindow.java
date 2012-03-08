package view.window;




import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import model.Board;
import model.IPhysicsEngine;
import controller.Controller;

import controller.EditListener;

/**
 * 
 * @author Andew White - 200939787
 * 
 * ApplicationWindow
 * 
 * @version 1.0 Started development of the GizmoBall GUI.
 *
 */
public class ApplicationWindow extends JFrame implements Observer{
	
	
	private ButtonArea buttonArea;
	
	private Controller controller;
	private EditListener listener;
	private AnimationPanel boardView;
	
	private IPhysicsEngine physics;
	private Board model;
	
	private JMenu file;
	private JMenu edit;
	
	private JMenuItem save;
	private JMenuItem load;
	
	private JMenuBar menu;
	
	public ApplicationWindow(Board model){
		super("Gizmoball");
		
		this.model = model;
		model.addObserver(this);
	
		
		listener = new EditListener();
		buttonArea = new ButtonArea(listener);
	
		boardView = new AnimationPanel(model);
		controller = new Controller(model, this);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		
		menu = new JMenuBar();
		
		createMenu();
		
		this.setJMenuBar(menu);
		JPanel panel = new JPanel();
	
		panel.setPreferredSize(new Dimension(400, 400));
		panel.add(boardView);
		JPanel contentPane = new JPanel();
		
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(500, 410));
		
		contentPane.add(panel, BorderLayout.LINE_START);
		contentPane.add(buttonArea.getButtonArea(), BorderLayout.AFTER_LINE_ENDS);
		
		
		setContentPane(contentPane);
		pack();
		requestFocus();
		
		//controller.addListeners();
	}
	
	private void createMenu(){
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		load.addActionListener(listener);
		
		
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

	
	public boolean isFocusable(){
		return true;
	}
	

	@Override
	public void update(Observable obs, Object obj) {
		System.out.println("paint");
		repaint();
		
	}

	public void addButtonListeners(ActionListener buttonListener) {
		System.out.println("adding buttons lis");
		buttonArea.addListeners(buttonListener);
	}

	public void addGridListner(MouseListener gridListener) {
		boardView.addMouseListener(gridListener);
	}
	
	
	

}
