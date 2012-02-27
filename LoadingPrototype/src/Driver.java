import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import model.BadFileException;
import model.Board;
import model.Loader;
import model.Physics;
import model.TriggerSystem;
import view.BoardView;

public class Driver {

	public static void main(String[] args) {
		String fileName = "saves/save1.txt";
		Physics physics = new Physics();
		Board board = new Board(20, 20);
		TriggerSystem trgsys = new TriggerSystem();
		try {
			Loader loader = new Loader(fileName);
			loader.parseFile(physics, trgsys);
			loader.loadItems(board);
			JFrame frame = new JFrame();
			frame.add(new JScrollPane(new BoardView(board)));
			frame.pack();
			frame.setVisible(true);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (BadFileException e) {
			System.out.println("Invalid file format: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception loading file: " + fileName);
		}
	}

}