package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.IBoardItem;

public class TriggerHandler extends KeyAdapter implements KeyListener {
	private Map<Integer, List<IBoardItem>> keyupTriggers;
	private Map<Integer, List<IBoardItem>> keydownTriggers;

	public TriggerHandler(Map<Integer, List<IBoardItem>> keyupTriggers,
			Map<Integer, List<IBoardItem>> keydownTriggers) {
		this.keyupTriggers = keyupTriggers;
		this.keydownTriggers = keydownTriggers;
	}

	public TriggerHandler() {
		keyupTriggers = new HashMap<Integer, List<IBoardItem>>();
		keydownTriggers = new HashMap<Integer, List<IBoardItem>>();
	}
	
	public void addLinks(Map<Integer, List<IBoardItem>> keyupTriggers,
			Map<Integer, List<IBoardItem>> keydownTriggers){
		this.keyupTriggers = keyupTriggers;
		this.keydownTriggers = keydownTriggers;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (keydownTriggers.containsKey(key)) {
			for (IBoardItem item : keydownTriggers.get(key)) {
				item.doAction();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (keyupTriggers.containsKey(key)) {
			for (IBoardItem item : keyupTriggers.get(key)) {
				item.doAction();
			}
		}
	}

	public void addLink(Integer keyCode, IBoardItem gizmo) {
		if (!keyupTriggers.containsKey(keyCode)) {
			keyupTriggers.put(keyCode, new ArrayList<IBoardItem>());
		}
		keyupTriggers.get(keyCode).add(gizmo);
		if (!keydownTriggers.containsKey(keyCode)) {
			keydownTriggers.put(keyCode, new ArrayList<IBoardItem>());
		}
		keydownTriggers.get(keyCode).add(gizmo);

		System.out.println("Key pressed");
	}
	
	public void clear()
	{
		keyupTriggers.clear();
		keydownTriggers.clear();
	}
}
