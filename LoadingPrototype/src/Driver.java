import java.io.FileNotFoundException;
import java.io.IOException;

import model.BadFileException;
import model.Board;
import model.Loader;
import model.Physics;
import model.TriggerSystem;

public class Driver {

	public static void main(String[] args) {
		String fileName = "saves/save2.txt";
		Physics physics = new Physics();
		Board board = new Board(20, 20);
		TriggerSystem trgsys = new TriggerSystem();
		try {
			Loader loader = new Loader(fileName);
			loader.parseFile(physics, trgsys);
			loader.loadItems(board);
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + fileName);
		} catch (BadFileException e) {
			System.out.println("Invalid file format: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("I/O exception loading file: " + fileName);
		}
	}

}