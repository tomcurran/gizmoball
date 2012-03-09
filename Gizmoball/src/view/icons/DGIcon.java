package view.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class DGIcon implements Icon {
	
	Color color;
	public DGIcon(Color c){
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
	    int pad = 5;
	    g.setColor(Color.red);
	    g.drawLine(pad, pad, c.getWidth()-pad, c.getHeight()-pad);
	    g.drawLine(c.getWidth()-pad, pad, pad, c.getHeight()-pad);
	}

}
