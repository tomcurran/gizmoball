package controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import exceptions.BadFileException;

import model.Ball;
import model.Board;
import model.GizmoType;
import model.IPhysicsEngine;
import model.Loader;
import model.gizmos.AbsorberGizmo;
import model.gizmos.CircleBumper;
import model.gizmos.LeftFlipper;
import model.gizmos.RightFlipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;
import model.physics.MitPhysicsEngineWrapper;
import view.window.ApplicationWindow;
import view.window.PhysicsPanel;

public class Controller {

	private Board model;
	private IPhysicsEngine engine;
	private ApplicationWindow appWin;
	private boolean buttonPressed;
	private GizmoType gt;
	private boolean addBall;
	private boolean leftFlipper;
	private int ax;
	private int ay;
	private JToggleButton previousButton;
	
	public Controller(IPhysicsEngine physics, Board model, ApplicationWindow applicationWindow) {
		this.model = model;
		engine = physics;
		buttonPressed = false;
		appWin = applicationWindow;
		gt = null;
		addBall = false;
		leftFlipper = false;
		
		addListeners();
	}

	public void addListeners() {
		appWin.addButtonListeners(new ButtonListener());
		appWin.addGridListner(new GridListener());
		appWin.addMenuListner(new SavesListener());
	}

	private class SavesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Save")){
				System.out.println("Gotta Save!");
			}else{
				JFileChooser chooser = new JFileChooser();
				int returnVal = chooser.showOpenDialog(chooser);
		        File file = chooser.getSelectedFile();
		        String fileName = file.getAbsolutePath();
				
				try {
					Loader loader = new Loader(fileName);
					loader.parseFile(engine);
					loader.loadItems(model);
					
					TriggerHandler handler = new TriggerHandler(loader.getKeyUpTriggers(), loader.getKeyDownTriggers());
					MagicKeyListener listener = new MagicKeyListener(handler);
					appWin.addKeyListener(listener);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			
			}
		}

	}
	
	

	
	private class GridListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		
			
			if(addBall && buttonPressed){
				model.addBall(new Ball(e.getX(), e.getY(), 3, 4));
			}else if(buttonPressed){
				if(gt == GizmoType.CircleBumper){
					model.addGizmo(new CircleBumper(Math.round(e.getX()/appWin.L), Math.round(e.getY()/appWin.L)));
					
				}
				
				if(gt == GizmoType.SquareBumper){
					model.addGizmo(new SquareBumper(Math.round(e.getX()/appWin.L), Math.round(e.getY()/appWin.L)));
					
				}
				
				if(gt == GizmoType.TriangleBumper){
					model.addGizmo(new TriangleBumper(Math.round(e.getX()/appWin.L), Math.round(e.getY()/appWin.L), 0));
				
				}
				
				if(gt == GizmoType.Flipper){
					if(leftFlipper){
						model.addGizmo(new LeftFlipper(Math.round(e.getX()/appWin.L), Math.round(e.getY()/appWin.L)));
						
					}else{
						model.addGizmo(new RightFlipper(Math.round(e.getX()/appWin.L), Math.round(e.getY()/appWin.L)));
						
					}
				}
				
				
				
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if(gt == GizmoType.Absorber){
				ax = Math.round(e.getX()/appWin.L);
				ay = Math.round(e.getY()/appWin.L);
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(gt == GizmoType.Absorber && buttonPressed && !addBall){
				model.addGizmo(new AbsorberGizmo(ax, ay, (Math.round(e.getX()/appWin.L)+1), (Math.round(e.getY()/appWin.L)+1)));
				ax = 0;
				ay = 0;
				buttonPressed = false;
			}
		}
		
	}

	private class ButtonListener implements ActionListener {

	
		@Override
		public void actionPerformed(ActionEvent e) {
			addBall = false;
			char key = e.getActionCommand().toUpperCase().charAt(0);
			
			if(previousButton != null){
				previousButton.setSelected(false);
			}else if(e.getSource().equals(JToggleButton.class)){
				previousButton = (JToggleButton) e.getSource();
			}else{
				previousButton = null;
				gt = null;
			}
			
			
			switch (key) {
			
			case 'C':
				buttonPressed = true;
				gt = GizmoType.CircleBumper;
				break;
				
			case 'S':
				buttonPressed = true;
				gt = GizmoType.SquareBumper;
				break;
				
			case 'T':
				buttonPressed = true;
				gt = GizmoType.TriangleBumper;
				break;
				
			case 'B':
				buttonPressed = true;
				addBall = true;
				gt = null;
				break;
				
			case 'A':
				
				buttonPressed = true;
				gt = GizmoType.Absorber;
				break;
				
			case 'M':
				System.out.println("Pressed");
				appWin.flipMode();
				model.runMode();
				model.notifyObservers();
				
				break;
				
			case 'L':
				
				buttonPressed = true;
				leftFlipper = true;
				gt = GizmoType.Flipper;
				
				break;

			default:
				break;
			}
			
			if(e.getActionCommand().equals("RightFlipper")){
				System.out.println("Pressed");
				addBall = false;
				buttonPressed = true;
				leftFlipper = false;
				gt = GizmoType.Flipper;
			}
			
			if(e.getActionCommand().equals("Rotate")){
				System.out.println("Pressed");
				addBall = false;
				buttonPressed = true;
			}
		}

	}


}
