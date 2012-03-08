package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.window.ApplicationWindow;
import model.Ball;
import model.Board;
import model.GizmoType;
import model.gizmos.AbsorberGizmo;
import model.gizmos.CircleBumper;
import model.gizmos.IGizmo;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;

public class Controller {

	private Board model;
	private ApplicationWindow appWin;
	private boolean buttonPressed;
	private GizmoType gt;
	private boolean addBall;
	private int ax;
	private int ay;
	
	public Controller(Board model, ApplicationWindow applicationWindow) {
		this.model = model;
		buttonPressed = false;
		appWin = applicationWindow;
		gt = null;
		addBall = false;
		
		addListeners();
	}

	public void addListeners() {
		appWin.addButtonListeners(new ButtonListener());
		appWin.addGridListner(new GridListener());
		
	}

	private class LoadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	private class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}

	}
	
	private class GridListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		
			
			if(addBall){
				model.addBall(new Ball(e.getX(), e.getY(), 3, 4));
			}else if(buttonPressed){
				if(gt == GizmoType.CircleBumper){
					model.addGizmo(new CircleBumper(Math.round(e.getX()/20)*20, Math.round(e.getY()/20)*20));
					buttonPressed = false;
				}
				
				if(gt == GizmoType.SquareBumper){
					model.addGizmo(new SquareBumper(Math.round(e.getX()/20)*20, Math.round(e.getY()/20)*20));
					buttonPressed = false;
				}
				
				if(gt == GizmoType.TriangleBumper){
					model.addGizmo(new TriangleBumper(Math.round(e.getX()/20)*20, Math.round(e.getY()/20)*20, 1));
					buttonPressed = false;
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
				ax = Math.round(e.getX()/20)*20;
				ay = Math.round(e.getY()/20)*20;
			}
			
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			if(gt == GizmoType.Absorber){
				System.out.println("ax: " + ax);
				System.out.println("ay: " + ay);
				System.out.println("new x" + Math.round(e.getX()/20)*20);
				model.addGizmo(new AbsorberGizmo(ax, ay, ax+Math.round(e.getX()/20)*20, 20));
				ax = 0;
				ay = 0;
				buttonPressed = false;
			}
		}
		
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("PRESSED");
			char key = e.getActionCommand().charAt(0);
			
			switch (key) {
			case 'C':
				addBall = false;
				buttonPressed = true;
				gt = GizmoType.CircleBumper;
				
				break;
				
			case 'S':
				System.out.println("Pressed");
				addBall = false;
				buttonPressed = true;
				gt = GizmoType.SquareBumper;
				break;
				
			case 'T':
				System.out.println("Pressed");
				addBall = false;
				buttonPressed = true;
				gt = GizmoType.TriangleBumper;
				break;
				
			case 'B':
				System.out.println("Pressed");
				buttonPressed = true;
				addBall = true;
				break;
				
			case 'A':
				System.out.println("Pressed");
				addBall = false;
				buttonPressed = true;
				gt = GizmoType.Absorber;
				break;
				
			case 'M':
				System.out.println("Pressed");
			//	model.changeMode();
				break;

			default:
				break;
			}
		}

	}


}
