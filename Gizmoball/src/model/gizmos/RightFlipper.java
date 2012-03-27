package model.gizmos;

public class RightFlipper extends Flipper {
	
	/**
	 * Constructs a new RightFlipper.
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point.
	 * @param angle - the starting angle.
	 */
	public RightFlipper(int x, int y, double angle) {
		super(x, y, 2, 2, angle, 0, -Math.PI / 2);
		super.orientation = 1;
	}

	public RightFlipper(int x, int y) {
		this(x, y, -Math.PI / 2.0);
	}

	@Override
	public void doAction() {
		if (getTriggeredState()) {
			this.setAngularMomentum(-Flipper.ANGULAR_MOMENTUM);
		} else {
			this.setAngularMomentum(Flipper.ANGULAR_MOMENTUM);
		}

		super.doAction();
	}
}