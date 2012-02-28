import javax.swing.JFrame;
import javax.swing.JScrollPane;

import view.FlipperView;

public class Driver {

	public static void main(String[] args) {
			JFrame frame = new JFrame();
			frame.add(new JScrollPane(new FlipperView()));
			frame.pack();
			frame.setVisible(true);
	}

}