package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.window.ApplicationWindow;
import model.Board;

public class Controller {

	private Board model;
	private ApplicationWindow appWin;
	
	public Controller(Board model, ApplicationWindow applicationWindow) {
		this.model = model;
		appWin = applicationWindow;
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
			System.out.println("clicked on grid");
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("PRESSED");
			char key = e.getActionCommand().charAt(0);
			
			switch (key) {
			case 'C':
				System.out.println("Pressed");
				break;
				
			case 'S':
				System.out.println("Pressed");
				break;
				
			case 'T':
				System.out.println("Pressed");
				break;
				
			case 'B':
				System.out.println("Pressed");
				break;
				
			case 'A':
				System.out.println("Pressed");
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
