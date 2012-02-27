

import java.util.Observable;
import java.awt.*;

/**
 * 
 * @author Andrew White - 200939787
 * 
 * FlipperPrototype - MODEL
 * 
 * Prototype class for demonstrating a flipper onscreen moving in
 * reaction to a key being pressed. In this demo, there is a single
 * flipper which reacts to the left arrow key being activated.
 *
 */
public class FlipperPrototype extends Observable{
	
	/* Dimensions for flipper */
	private final int flipperX = 30;
	private final int flipperY = 30;
	private final int flipperWidth = 20;
	private final int flipperHeight = 40;

	/* Graphic that will be used on screen. */
	private Rectangle rect;
	
	/**
	 * Constructor that instantiates the rectangle.
	 */
	public FlipperPrototype(){
		super();
		rect = new Rectangle(flipperX, flipperY, flipperWidth, flipperHeight);
	}

	/**
	 * Creates a new rectangle, which will represent an animated flipper. When called the 
	 * rectangle used on screen is set to be the 'changed' rectangle and the model is set 
	 * to changed and the observers are notified. 
	 */
	public void activate() {
		Rectangle changed = new Rectangle(flipperX - (flipperHeight - flipperWidth), flipperY, flipperHeight, flipperWidth);
		rect = changed;

		
		this.setChanged();
		this.notifyObservers(changed);
	}
	
	/**
	 * Method that retrieves the rectangle.
	 * 
	 * @return - rectangle to draw. 
	 */
	public Rectangle getFlipper() {
		return rect;
	}

	
	/**
	 * Sets the rectangle back to it's original starting values and notifies the observers. 
	 */
	public void deActivate() {
		Rectangle changed = new Rectangle(flipperX, flipperY, flipperWidth, flipperHeight);
		rect = changed;

		this.setChanged();
		this.notifyObservers(changed);
	}

}
