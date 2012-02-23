package prototypes.flipper;
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
