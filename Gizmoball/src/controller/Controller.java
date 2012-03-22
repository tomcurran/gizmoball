package controller;

import model.Ball;
import model.Board;
import model.IPhysicsEngine;
import model.gizmos.AbsorberGizmo;
import model.gizmos.IGizmo;

import view.window.ApplicationWindow;

/**
 * 
 * @author Group 4
 * 
 *         This is the Controller for the MVC model, it takes action events from
 *         the view and handles them appropriately by changing the model.
 * 
 */
public class Controller {

	public Board boardModel;
	public IPhysicsEngine engine;

	public ApplicationWindow appWin;
	TriggerHandler handler;
	public MagicKeyListener magicListener;
	IGizmo selectedGizmo, linkGizmoOne, linkGizmoTwo, gizmo = null;
	
	int ax, ay, ax2, ay2;

	public char command;

	Integer linkGizmoKey;

	public Controller(IPhysicsEngine physics, Board model,
			ApplicationWindow applicationWindow) {
		engine = physics;
		this.boardModel = model;
		appWin = applicationWindow;
		handler = new TriggerHandler();
		magicListener = new MagicKeyListener(handler);
		addListeners();
	}

	/**
	 * Add listeners to the application window.
	 */
	public void addListeners() {
		appWin.addButtonListeners(new ButtonListener(this));
		appWin.addGridListner(new GridListener(this));
		appWin.addMenuListner(new SavesListener(this));
		appWin.addLinkKeyListener(new LinkListener(this));
		appWin.addMagicListener(magicListener);
	}

	/**
	 * 
	 * @return - allows for the user to click and drag in any direction when
	 *         placing an absorber gizmo.
	 */
	IGizmo getNormalisedAbsorber() {
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;

		if (ax > ax2) {
			x1 = ax2;
			x2 = ax;
		} else {
			x1 = ax;
			x2 = ax2;
		}

		if (ay > ay2) {
			y1 = ay2;
			y2 = ay;
		} else {
			y1 = ay;
			y2 = ay2;
		}

		x2++;
		y2++;

		return new AbsorberGizmo(x1, y1, x2, y2);
	}

	/**
	 * Check the validity of a gizmo/ball placement.
	 * 
	 * @param x
	 *            - x position of gizmo being validated.
	 * @param y
	 *            - y position of the gizmo being validated.
	 * @param w
	 *            - width of the gizmo being validated.
	 * @param h
	 *            - height of the gizmo being validated.
	 * @return - True if valid, false otherwise.
	 */
	boolean validLocation(int x, int y, int w, int h) {
		if (x + w > boardModel.getWidth() || y + h > boardModel.getHeight()) {
			return false;
		}
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int xx = x + i;
				int yy = y + j;
				IGizmo bMG = boardModel.getGizmoAt(xx, yy);
				Ball bMB = boardModel.getBallAt(xx, yy);
				if (bMG != null && !bMG.equals(gizmo)) {
					return false;
				} else if (bMB != null) {
					return false;
				}
			}
		}
		return true;
	}

}
