package view.icons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class MoveGizmoIcon implements Icon {
	Color color;

	public MoveGizmoIcon(Color c) {
		color = c;
	}

	public int getIconWidth() {
		return 60;
	}

	public int getIconHeight() {
		return 20;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.black);

		g.drawLine(c.getWidth() / 2, y, c.getWidth() / 2, c.getHeight() - 5);
		g.drawLine(5, c.getHeight() / 2, c.getWidth() - 5, c.getHeight() / 2);
		g.drawLine(c.getWidth() / 2 - 2, y + 5, c.getWidth() / 2, y);
		g.drawLine(c.getWidth() / 2 + 2, y + 5, c.getWidth() / 2, y);

		g.drawLine(c.getWidth() / 2 - 2, c.getHeight() - 10, c.getWidth() / 2,
				c.getHeight() - 5);
		g.drawLine(c.getWidth() / 2 + 2, c.getHeight() - 10, c.getWidth() / 2,
				c.getHeight() - 5);

		g.drawLine(5, c.getHeight() / 2, 10, c.getHeight() / 2 - 2);
		g.drawLine(5, c.getHeight() / 2, 10, c.getHeight() / 2 + 2);

		g.drawLine(c.getWidth() - 5, c.getHeight() / 2, c.getWidth() - 10,
				c.getHeight() / 2 - 2);
		g.drawLine(c.getWidth() - 5, c.getHeight() / 2, c.getWidth() - 10,
				c.getHeight() / 2 + 2);
	}

}
