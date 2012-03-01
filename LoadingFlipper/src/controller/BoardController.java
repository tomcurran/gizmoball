package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Board;
import model.IFlipper;

public class BoardController extends KeyAdapter implements KeyListener {

	private static final int KEY = KeyEvent.VK_SPACE;

	private Board model;

	public BoardController(Board model) {
		this.model = model;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KEY){
			for (IFlipper f : model.getFlippers()) {
				f.activate();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KEY){
			for (IFlipper f : model.getFlippers()) {
				f.deactivate();
			}
		}
	}

}