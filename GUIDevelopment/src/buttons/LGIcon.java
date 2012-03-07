package buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class LGIcon implements Icon{
	private Color color;
	public LGIcon(Color c){
		color = c;
	}
	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 13;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		 g.setColor(color);
		 g.drawOval (5, 5, getIconWidth(), getIconHeight());
		    
		    
		 g.drawOval (5, 12, getIconWidth(), getIconHeight());
		   
		     
	     g.drawString(" Gizmo", 14, 17);
		
	}

}
