package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;

import model.Loader;
import exceptions.BadFileException;

/**
 * SavesListener controls the loading of previously created Gizmoball games and
 * saving of games created by the user while in edit mode.
 */
class SavesListener implements ActionListener {

	/**
	 * 
	 */
	private final Controller controller;

	/**
	 * @param controller
	 */
	SavesListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	/**
	 * Checks the actionCommand string, saving or
	 * loading depending on the result. 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Save")) { // Need to save
			System.out.println("Gotta Save!");
		} else { // Otherwise allow user to load a file.
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(chooser);
			File file = chooser.getSelectedFile();
			String fileName = file.getAbsolutePath();

			try {
				Loader loader = new Loader(fileName);
				loader.parseFile(this.controller.engine);
				loader.loadItems(this.controller.boardModel);

				this.controller.handler.addLinks(loader.getKeyUpTriggers(),
						loader.getKeyDownTriggers());

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (BadFileException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

		}
	}

}