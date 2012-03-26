package view.icons;


import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class BallIcon implements Icon{
	Color color;
    public BallIcon (Color c) {
      color = c;
    }
   
    public int getIconWidth() {
      return 7;
    }
    public int getIconHeight() { 
      return 7;
    }
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
	     g.setColor(color);
	     g.fillOval (
	        5, 5, getIconWidth(), getIconHeight());
	     g.setColor(Color.orange);
	     g.drawOval(5, 5, getIconWidth(), getIconHeight());
		
	}

}
