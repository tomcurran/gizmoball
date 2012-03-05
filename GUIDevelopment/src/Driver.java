import view.ApplicationWindow;




public class Driver {
	
	public static void main(String[] args) {
	

		ApplicationWindow frame = new ApplicationWindow();

		frame.setJMenuBar(frame.getGizmoMenu());
		frame.pack();
		frame.setVisible(true);
	}

}
