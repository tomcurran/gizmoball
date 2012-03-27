package model.gizmos;

public class LeftFlipper extends Flipper {
	
	/**
	 * Constructs a left flipper. 
	 * 
	 * @param x - topleft x point.
	 * @param y - topleft y point.
	 * @param angle - the starting angle. 
	 */
	public LeftFlipper(int x, int y, double angle) {

		super(x, y, 2, 2, angle, 0, -Math.PI / 2);
	}

	public LeftFlipper(int x, int y) {
		this(x, y, 0);
	}

	@Override
	public void doAction() {
		if (getTriggeredState()) {
			this.setAngularMomentum(Flipper.ANGULAR_MOMENTUM);
		} else {
			this.setAngularMomentum(-Flipper.ANGULAR_MOMENTUM);
		}

		super.doAction();
	}
}