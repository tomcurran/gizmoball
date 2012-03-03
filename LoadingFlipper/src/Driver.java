import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		if (args.length < 1) {
			System.out.println("provide file name to load");
			return;
		}
		String fileName = args[0];
		Physics physics = new Physics();
		Board board = new Board(20, 20);
		TriggerSystem trgsys = new TriggerSystem();
		try {
			Loader loader = new Loader(fileName);
			loader.parseFile(physics, trgsys);
			loader.loadItems(board);
			JFrame frame = new JFrame();
			frame.add(new JScrollPane(new BoardView(board, trgsys)));
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}
			});
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