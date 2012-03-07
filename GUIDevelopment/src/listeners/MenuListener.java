package listeners;


import gizmos.BadFileException;
import gizmos.TriggerSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import menubar.Loader;
import model.Board;
import model.BoardView;
import model.Physics;



public class MenuListener implements ActionListener {
	
	private Physics physics;
	private TriggerSystem trgsys;
	private Board board;
	private BoardView boardView;
	
	public MenuListener(Physics physics, TriggerSystem trgsys, Board board, BoardView boardView){
		this.physics = physics;
		this.trgsys = trgsys;
		this.board = board;
		this.boardView = boardView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		readFromFile();
	}
	
	
	public void readFromFile(){
		JFileChooser chooser = new JFileChooser();
	 
		int returnVal = chooser.showOpenDialog(chooser);

       
        File file = chooser.getSelectedFile();
        
        String fileName = file.getAbsolutePath();
       
        try {
			Loader loader = new Loader(fileName);
			loader.parseFile(physics, trgsys);
			loader.loadItems(board);
		
			
			boardView.repaint();
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (IOException e) {
			System.out.println("I/O exception loading file: " + fileName);
		} catch (BadFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
