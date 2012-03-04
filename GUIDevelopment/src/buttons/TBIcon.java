package buttons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.Icon;

public class TBIcon implements Icon{
	
	private Color color;
	
	public TBIcon(Color c){
		color = c;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Point p1 = new Point(5, 5);
		Point p2 = new Point(5, 25);
		Point p3 = new Point(25, 5);

		int[] xs = { p1.x, p2.x, p3.x };
		int[] ys = { p1.y, p2.y, p3.y };
		Polygon triangle = new Polygon(xs, ys, 3);
		g.setColor(color);
		g.fillPolygon(triangle);
	}

}
