package buttons;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonArea {
	
	private JPanel buttonArea;
	
	public ButtonArea(){
		
		buttonArea = new JPanel();
		buttonArea.setPreferredSize(new Dimension(100, 100));
		
		Icon cbbs = new CBIcon(Color.green);
		JButton cb = new JButton(cbbs);
		cb.setPreferredSize(new Dimension(30, 30));
		
		Icon sbbs = new SBIcon(Color.red);
		JButton sb = new JButton(sbbs);
		sb.setPreferredSize(new Dimension(30, 30));
		
		Icon tbbs = new TBIcon(Color.blue);
		JButton tb = new JButton(tbbs);
		tb.setPreferredSize(new Dimension(30, 30));
		
		Icon bbbs = new BIcon(Color.blue);
		JButton bb = new JButton(bbbs);
		bb.setPreferredSize(new Dimension(30, 30));
		
		Icon abbs = new AIcon(Color.magenta);
		JButton ab = new JButton(abbs);
		ab.setPreferredSize(new Dimension(70, 30));
		
		buttonArea.add(cb);
		buttonArea.add(sb);
		buttonArea.add(tb);
		buttonArea.add(bb);
		buttonArea.add(ab);
		
	}
	
	
	public JPanel getButtonArea(){
		return buttonArea;
	}
	
	
}
