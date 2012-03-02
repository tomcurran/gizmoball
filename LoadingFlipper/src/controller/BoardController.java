package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.TriggerSystem;

public class BoardController extends KeyAdapter implements KeyListener {

	private TriggerSystem trigsys;

	public BoardController(TriggerSystem trigsys) {
		this.trigsys = trigsys;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		trigsys.triggerKeyUp(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		trigsys.triggerKeyDown(e.getKeyCode());
	}

}