package view.board;

import java.awt.Point;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class ScalableComponent extends JComponent {

	private Point scale;

	public ScalableComponent() {
		scale = new Point();
	}

	public void setScale(Point scale) {
		this.scale = scale;
	}

	public Point getScale() {
		return scale;
	}

}