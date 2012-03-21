package controller;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import model.Ball;
import model.gizmos.CircleBumper;
import model.gizmos.IGizmo;
import model.gizmos.LeftFlipper;
import model.gizmos.RightFlipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;
import view.window.AnimationPanel;

/**
 * GridListener handles all events that happen to the EditGrid, e.g. a user
 * clicking on the grid when the 'CircleGizmo' button is selected will check the
 * validity of a square and place the gizmo if valid.
 */
class GridListener implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private final Controller controller;

	/**
	 * @param controller
	 */
	GridListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	/**
	 * When the mouse is clicked on the grid, this method checks
	 * to see if a command (i.e. edit button selected) has been 
	 * set, and carries out the appropriate action. 
	 */
	public void mouseClicked(MouseEvent e) {

		IGizmo gizmoLinkGiz = null;

		AnimationPanel ap = (AnimationPanel) e.getComponent();
		int x = (int) Math.round(e.getX() / ap.getScaleX());
		int y = (int) Math.round(e.getY() / ap.getScaleY());
		int w = 0;
		int h = 0;

		Ball ball = null;

		switch (this.controller.command) {

		case 'C':
			this.controller.gizmo = new CircleBumper(x, y);
			w = this.controller.gizmo.getWidth();
			h = this.controller.gizmo.getHeight();
			if (this.controller.validLocation(x, y, w, h)) {
				this.controller.boardModel.addGizmo(this.controller.gizmo);
			}
			break;

		case 'S':
			this.controller.gizmo = new SquareBumper(x, y);
			w = this.controller.gizmo.getWidth();
			h = this.controller.gizmo.getHeight();
			if (this.controller.validLocation(x, y, w, h)) {
				this.controller.boardModel.addGizmo(this.controller.gizmo);
			}
			break;

		case 'T':
			this.controller.gizmo = new TriangleBumper(x, y, 0);
			w = this.controller.gizmo.getWidth();
			h = this.controller.gizmo.getHeight();
			if (this.controller.validLocation(x, y, w, h)) {
				this.controller.boardModel.addGizmo(this.controller.gizmo);
			}
			break;

		case 'F':
			this.controller.gizmo = new RightFlipper(x, y);
			w = this.controller.gizmo.getWidth();
			h = this.controller.gizmo.getHeight();
			if (this.controller.validLocation(x, y, w, h)) {
				this.controller.boardModel.addGizmo(this.controller.gizmo);
			}
			break;

		case 'G':
			this.controller.gizmo = new LeftFlipper(x, y);
			w = this.controller.gizmo.getWidth();
			h = this.controller.gizmo.getHeight();
			if (this.controller.validLocation(x, y, w, h)) {
				this.controller.boardModel.addGizmo(this.controller.gizmo);
			}
			break;

		case 'B':
			ball = new Ball(x, y, 0.25, 1);
			if (this.controller.validLocation(x, y, 1, 1)) {
				this.controller.boardModel.addBall(ball);
			}

			break;

		case 'R':
			this.controller.gizmo = this.controller.boardModel.getGizmoAt(x, y);

			if (this.controller.gizmo != null) {
				try {
					this.controller.gizmo.rotate();
				} catch (UnsupportedOperationException e1) {
					// TODO Do all the rotation functions actually work?
				}
			}
			break;

		case 'D':
			this.controller.gizmo = this.controller.boardModel.getGizmoAt(x, y);
			ball = this.controller.boardModel.getBallAt(x, y);
			if (this.controller.gizmo != null) {
				this.controller.boardModel.removeGizmo(this.controller.gizmo);
			} else if (ball != null) {
				this.controller.boardModel.removeBall(ball);
			}

			break;
		case 'K':
			this.controller.keyLinkGiz = this.controller.boardModel.getGizmoAt(
					x, y);
			ap.requestFocus();
			if (this.controller.keyLinkKey != null) {
				this.controller.handler.addLink(this.controller.keyLinkKey,
						this.controller.gizmo);
				this.controller.keyLinkKey = null;
			}
			break;

		case 'J':
			if (this.controller.keyLinkGiz != null) {
				gizmoLinkGiz = this.controller.boardModel.getGizmoAt(x, y);
				if (gizmoLinkGiz != null) {
					this.controller.keyLinkGiz.connect(gizmoLinkGiz);
					gizmoLinkGiz = null;
					this.controller.keyLinkGiz = null;
				}
			} else {
				this.controller.keyLinkGiz = this.controller.boardModel
						.getGizmoAt(x, y);
			}
			break;
		default:
			break;
		}

		this.controller.gizmo = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		AnimationPanel ap = (AnimationPanel) e.getComponent();
		ap.removeLoactionIndicator();
	}

	@Override
	/**
	 * If the mouse has left the grid, deactivate the validity
	 * guidance square. 
	 */
	public void mouseExited(MouseEvent e) {
		AnimationPanel ap = (AnimationPanel) e.getComponent();
		ap.removeLoactionIndicator();
	}

	@Override
	/**
	 * The absorber has a user defined size, controlled by
	 * their dragging of the mouse whilst in the grid area.
	 */
	public void mousePressed(MouseEvent e) {
		AnimationPanel ap = (AnimationPanel) e.getComponent();
		switch (this.controller.command) {
		case 'A':
			this.controller.ax = (int) Math.round(e.getX() / ap.getScaleX());
			this.controller.ay = (int) Math.round(e.getY() / ap.getScaleY());
			break;

		case 'Z':
			this.controller.ax = (int) Math.round(e.getX() / ap.getScaleX());
			this.controller.ay = (int) Math.round(e.getY() / ap.getScaleY());
			this.controller.gizmo = this.controller.boardModel.getGizmoAt(
					this.controller.ax, this.controller.ay);
			break;
		}

		drawValidityBox(e);
	}

	@Override
	/**
	 * When the mouse is released, and the absorber command is
	 * selected, create an absorber. 
	 */
	public void mouseReleased(MouseEvent e) {
		AnimationPanel ap = (AnimationPanel) e.getComponent();
		switch (this.controller.command) {
		case 'A':
			this.controller.ax2 = (int) Math.round(e.getX() / ap.getScaleX());
			this.controller.ay2 = (int) Math.round(e.getY() / ap.getScaleY());
			this.controller.selectedGizmo = this.controller
					.getNormailisedAbsorber();
			if (this.controller.validLocation(
					this.controller.selectedGizmo.getX(),
					this.controller.selectedGizmo.getY(),
					this.controller.selectedGizmo.getWidth(),
					this.controller.selectedGizmo.getHeight())) {
				this.controller.boardModel
						.addGizmo(this.controller.selectedGizmo);
			}
			this.controller.ax = this.controller.ax2;
			this.controller.ay = this.controller.ay2;
			break;

		case 'Z':
			this.controller.ax2 = (int) Math.round(e.getX() / ap.getScaleX());
			this.controller.ay2 = (int) Math.round(e.getY() / ap.getScaleY());

			if (this.controller.boardModel.getGizmoAt(this.controller.ax2,
					this.controller.ay2) == null
					&& this.controller.gizmo != null
					&& this.controller.validLocation(this.controller.ax2,
							this.controller.ay2,
							this.controller.gizmo.getWidth(),
							this.controller.gizmo.getHeight())) {
				this.controller.gizmo.move(this.controller.ax2,
						this.controller.ay2);
			}
			this.controller.gizmo = null;
		}
		drawValidityBox(e);
	}

	@Override
	/**
	 * While the mouse is dragged, maintain a normalised value of the potential
	 * absorbers width and height. 
	 */
	public void mouseDragged(MouseEvent e) {
		switch (this.controller.command) {
		case 'A':
			AnimationPanel ap = (AnimationPanel) e.getComponent();
			this.controller.ax2 = (int) Math.round(e.getX() / ap.getScaleX());
			this.controller.ay2 = (int) Math.round(e.getY() / ap.getScaleY());
			break;
		}
		drawValidityBox(e);
	}

	@Override
	/**
	 * Maintaint the validity box while mouse is over the grid.
	 */
	public void mouseMoved(MouseEvent e) {
		AnimationPanel ap = (AnimationPanel) e.getComponent();
		this.controller.ax = (int) Math.round(e.getX() / ap.getScaleX());
		this.controller.ay = (int) Math.round(e.getY() / ap.getScaleY());
		this.controller.ax2 = this.controller.ax;
		this.controller.ay2 = this.controller.ay;
		drawValidityBox(e);
	}

	/**
	 * Draw the Validity Guidance Square on the screen.
	 * 
	 * @param e
	 *            - MouseEvent used got gaining the mouses position.
	 */
	private void drawValidityBox(MouseEvent e) {

		AnimationPanel ap = (AnimationPanel) e.getComponent();

		int w = 1;
		int h = 1;

		int x = (int) Math.round(e.getX() / ap.getScaleX());
		int y = (int) Math.round(e.getY() / ap.getScaleY());

		switch (this.controller.command) {
		case 'C':
			this.controller.selectedGizmo = new CircleBumper(x, y);
			break;

		case 'S':
			this.controller.selectedGizmo = new SquareBumper(x, y);
			break;

		case 'T':
			this.controller.selectedGizmo = new TriangleBumper(x, y, 0);
			break;

		case 'F':
			this.controller.selectedGizmo = new RightFlipper(x, y);
			break;

		case 'G':
			this.controller.selectedGizmo = new LeftFlipper(x, y);
			break;

		case 'A':
			this.controller.selectedGizmo = this.controller
					.getNormailisedAbsorber();
			x = this.controller.selectedGizmo.getX();
			y = this.controller.selectedGizmo.getY();
			break;

		case 'Z':
			this.controller.selectedGizmo = this.controller.gizmo;
			break;

		default:
			this.controller.selectedGizmo = null;
			break;
		}

		if (this.controller.selectedGizmo != null) {
			w = this.controller.selectedGizmo.getWidth();
			h = this.controller.selectedGizmo.getHeight();
			ap.setLoactionIndicator(x, y, w, h, (this.controller.validLocation(
					x, y, w, h) ? Color.GREEN : Color.RED));
		} else if (this.controller.command == 'B') {
			ap.setLoactionIndicator(x, y, 1, 1, (this.controller.validLocation(
					x, y, 1, 1) ? Color.GREEN : Color.RED));
		} else if (this.controller.command == 'Z'
				|| this.controller.command == 'R') {
			ap.setLoactionIndicator(x, y, 1, 1, (this.controller.validLocation(
					x, y, 1, 1) ? Color.RED : Color.GREEN));
		} else {
			ap.removeLoactionIndicator();
		}

	}
}