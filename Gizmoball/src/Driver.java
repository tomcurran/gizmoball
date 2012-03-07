import model.Board;
import view.window.ApplicationWindow;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board(400, 400);
		ApplicationWindow frame = new ApplicationWindow(board);

		
		frame.pack();
		frame.setVisible(true);
	}
}
