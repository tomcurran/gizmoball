package prototype1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
/**
 * 
 * @author Andrew White - 200939787
 *
 * MyApplicationWindow
 * 
 * Creates the window that can be seen by the user and interacted
 * with.
 * 
 */
public class MyApplicationWindow extends JFrame {
	
	private MyAnimationWindow animationWindow;
	
	public MyApplicationWindow(FlipperPrototype flip){
		
		super("Flipper Prototype");
		
		animationWindow = new MyAnimationWindow(flip);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(animationWindow);
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(50, 120));
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setContentPane(contentPane);
		
	}

}
