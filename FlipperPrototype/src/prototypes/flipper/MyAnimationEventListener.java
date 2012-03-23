package prototypes.flipper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * @author Andrew White - 200939787
 * 
 * MyAnimationEvenListener - Controller 
 * 
 * Handles all user input and calls the appropriate
 * methods within the model to adapt the data. 
 *
 */
public class MyAnimationEventListener extends KeyAdapter implements KeyListener, ActionListener {

	/* Flipper prototype. */
	private FlipperPrototype flipper;
	
	/**
	 * Constructor which instantiates the declared flipper with the
	 * model of the prototype program. 
	 * 
	 * @param flip - a reference to the prototype model. 
	 */
	public MyAnimationEventListener(FlipperPrototype flip) {
		super();
		flipper = flip;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	}

	@Override
	/**
	 * If the left key is pressed, the activate method is called. 
	 * It proceeds to be called until released. 
	 */
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			flipper.activate();
		}
	}

	@Override
	/**
	 * When the key is released, the flipper deactives. 
	 */
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			flipper.deActivate();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}


}
