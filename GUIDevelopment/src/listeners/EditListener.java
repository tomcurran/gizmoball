package listeners;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import mainwindow.EditGrid;

public class EditListener implements MouseListener, ActionListener{
	
//	private EditModel model;
//	
//	public EditListener(EditModel model){
//		this.model = model;
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getX());
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

	@Override
	public void actionPerformed(ActionEvent e) {
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
