package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * Listener for the GizmoBall Button Area, when a button is pressed it sets a
 * command placeholder. If the button pressed has an immediate action, it is
 * carried out here.
 */
class ButtonListener implements ActionListener {
	/**
	 * 
	 */
	private final Controller controller;

	/**
	 * @param controller
	 */
	ButtonListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.command = e.getActionCommand().toUpperCase().charAt(0);

		if (this.controller.command == 'M') {
			this.controller.appWin.flipMode();
			this.controller.boardModel.runMode();
			JButton temp = (JButton) e.getSource();
			if (temp.getText().equals("Play")) {
				temp.setText("Edit");
				this.controller.engine.initialise(this.controller.boardModel);
				this.controller.appWin
						.switchListeners(this.controller.magicListener);
			} else {
				temp.setText("Play");
			}
		}
	}
}