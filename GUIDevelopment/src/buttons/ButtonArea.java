package buttons;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonArea extends JPanel {
	
	
	
	public ButtonArea(){
		super();
		
		this.setPreferredSize(new Dimension(500, 100));
		
		this.add(new JButton("Test"));
		
	}

}
