package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import model.IFlipper;

public class FlipperController extends KeyAdapter implements KeyListener {

	private static final int KEY = KeyEvent.VK_LEFT;

	private List<IFlipper> flippers;

	public FlipperController(List<IFlipper> flippers) {
		this.flippers = flippers;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KEY){
			for (IFlipper f : flippers) {
				f.activate();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KEY){
			for (IFlipper f : flippers) {
				f.deactivate();
			}
		}
	}

}