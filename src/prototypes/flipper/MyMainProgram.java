package prototype1;



public class MyMainProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FlipperPrototype flipper = new FlipperPrototype();

		MyApplicationWindow frame = new MyApplicationWindow(flipper);

		frame.pack();
		frame.setVisible(true);
	}

}
