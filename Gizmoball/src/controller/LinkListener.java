package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Key listener for handling the linking of gizmos.
 */
class LinkListener implements KeyListener {

	private final Controller controller;

	/**
	 * @param controller
	 */
	LinkListener(Controller controller) {
		this.controller = controller;
	}

	private int keyCode;

	@Override
	public void keyPressed(KeyEvent e) {
		keyCode = e.getKeyCode();
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if (this.controller.linkGizmoKey != null) {
			this.controller.handler
					.addLink(keyCode, this.controller.linkGizmoOne);
			this.controller.linkGizmoTwo = null;
		} else {
			this.controller.linkGizmoKey = keyCode;
		}
	}

}