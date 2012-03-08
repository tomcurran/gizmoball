import mainwindow.ApplicationWindow;


public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationWindow frame = new ApplicationWindow();

		frame.setJMenuBar(frame.getGizmoMenu());
		frame.pack();
		frame.setVisible(true);
	}

}
