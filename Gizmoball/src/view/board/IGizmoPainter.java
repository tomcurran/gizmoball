package view.board;

import java.awt.Graphics2D;

import model.gizmos.IGizmo;

public interface IGizmoPainter {
	
	/**
	 * Paints a desired item to the screen.
	 * 
	 * @param g - the graphic to paint to.
	 * @param gizmo - the gizmo type to paint.
	 */
	void paint(Graphics2D g, IGizmo gizmo);
}
