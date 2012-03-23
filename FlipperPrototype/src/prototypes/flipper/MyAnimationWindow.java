package prototypes.flipper;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;


@SuppressWarnings("serial")
/**
 * 
 * @author Andrew White - 200939787
 * 
 * MyAnimationWindow - View
 * 
 * Observes the model, and animates the flipper appropriately.
 *
 */
public class MyAnimationWindow extends JPanel implements Observer{
	
	/* Variables for reference to the model and controller */
	private MyAnimationEventListener eventListener;
	private MagicKeyListener magic;
	private FlipperPrototype flip;
	
	/**
	 * Constructor to instantiate the model and controller variables. 
	 * 
	 * @param f - the flipper to observe. 
	 */
	public MyAnimationWindow(FlipperPrototype f) {
		super();
		flip = f;
		
		
		flip.addObserver(this);
		
		eventListener = new MyAnimationEventListener(flip);
		magic = new MagicKeyListener(eventListener);

		addKeyListener(magic);
		requestFocus();    
	
	}

	/**
	 * Ensures the window has focus. 
	 */
	public boolean isFocusable(){
		return true;
	}
	

	@Override
	/**
	 *repaints the window is the model changes.  
	 */
	public void update(Observable o, Object arg) {
		
		Rectangle paint = (Rectangle) arg;
	
		repaint(new Rectangle(0, 0, getWidth(), getHeight()));
		repaint(paint.x, paint.y, paint.width, paint.height);
		
	}
	

	@Override
	/**
	 * Paints the window. 
	 */
	public void paint (Graphics g){
		Rectangle rec = flip.getFlipper();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.BLACK);
		g.fillRect(rec.x, rec.y, rec.width, rec.height);
		
	}

}
