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

	/**
	 * Constructor to be used when a game is loaded from a file. 
	 * 
	 * @param keyupTriggers - the loaded keyupTriggers.
	 * @param keydownTriggers - the loaded keydownTriggers.
	 */
	public TriggerHandler(Map<Integer, List<IBoardItem>> keyupTriggers,
			Map<Integer, List<IBoardItem>> keydownTriggers) {
		this.keyupTriggers = keyupTriggers;
		this.keydownTriggers = keydownTriggers;
	}

	/**
	 * Constructor for a new design mode game. 
	 */
	public TriggerHandler() {
		keyupTriggers = new HashMap<Integer, List<IBoardItem>>();
		keydownTriggers = new HashMap<Integer, List<IBoardItem>>();
	}
	
	/**
	 * Allows a set of keyupTriggers and keydownTriggers to be added 
	 * to the handler.
	 * 
	 * @param keyupTriggers - the map of keyupTriggers.
	 * @param keydownTriggers - the map of keydownTriggers.
	 */
	public void addLinks(Map<Integer, List<IBoardItem>> keyupTriggers,
			Map<Integer, List<IBoardItem>> keydownTriggers){
		this.keyupTriggers = keyupTriggers;
		this.keydownTriggers = keydownTriggers;
	}

	@Override
	/**
	 * When a key is pressed, the map of keydownTriggers
	 * is searched to see if it triggers a gizmo.
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (keydownTriggers.containsKey(key)) {
			for (IBoardItem item : keydownTriggers.get(key)) {
				item.doAction();
			}
		}
	}

	@Override
	/**
	 * When a key is released, the map of keyupTriggers
	 * is searched to see if it triggers a gizmo.
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (keyupTriggers.containsKey(key)) {
			for (IBoardItem item : keyupTriggers.get(key)) {
				item.doAction();
			}
		}
	}
	
	/**
	 * Kept for backwards compatibility, should remove soon.
	 * @param keyCode
	 * @param gizmo
	 */
	public void addLink(Integer keyCode, IBoardItem gizmo) {
		addLinkUp(keyCode, gizmo);
		addLinkDown(keyCode, gizmo);
	}

	/**
	 * Used to add a user made keyuplink to a gizmo. 
	 * 
	 * @param keyCode - the key the user pressed.
	 * @param gizmo - the gizmo the user selected.
	 */
	public void addLinkUp(Integer keyCode, IBoardItem gizmo) {
		if (!keyupTriggers.containsKey(keyCode)) {
			keyupTriggers.put(keyCode, new ArrayList<IBoardItem>());
		}
		keyupTriggers.get(keyCode).add(gizmo);
	}
	
	/**
	 * Used to add a user made keydownLink to a gizmo.
	 * 
	 * @param keyCode - the key the user pressed.
	 * @param gizmo - the gizmo the user selected. 
	 */
	public void addLinkDown(Integer keyCode, IBoardItem gizmo) {
		if (!keydownTriggers.containsKey(keyCode)) {
			keydownTriggers.put(keyCode, new ArrayList<IBoardItem>());
		}
		keydownTriggers.get(keyCode).add(gizmo);
	}
	
	/**
	 * Clears all triggers. 
	 */
	public void clear() {
		keyupTriggers.clear();
		keydownTriggers.clear();
	}

	/**
	 * Gets the list of keyupTriggers. 
	 * 
	 * @return - the map representing the keyupTriggers. 
	 */
	public Map<Integer, List<IBoardItem>> getLinksUp() {
		return keyupTriggers;
	}
	
	/**
	 * Gets the list of keydownTriggers.
	 * 
	 * @return - the map representing the keydownTriggers.
	 */
	public Map<Integer, List<IBoardItem>> getLinksDown() {
		return keydownTriggers;
	}

}