package view.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class RGIcon implements Icon {
	
	Color color;
	public RGIcon(Color c){
		color = c;
		
		
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 18;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 17;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	    
	     g.setColor(Color.black);
	     g.drawArc(5, 5, getIconWidth(), getIconWidth(), 0,270);
	     g.drawLine(10, 18, 12, 22);
	     g.drawLine(10, 24, 12, 22);
	}

}
