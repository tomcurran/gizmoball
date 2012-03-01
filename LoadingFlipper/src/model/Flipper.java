package model;


public abstract class Flipper extends BoardItem implements IBoardItem, IFlipper {

	private static final double ANGULAR_DELTA = 6 * Math.PI;

	private double angle, startAngle, endAngle;
	protected double angularMomentum;

	public Flipper(int x, int y, double angle) {
		super(x, y, 2, 2);
		this.angle = angle;
		this.startAngle = 0;
		this.endAngle = Math.PI / 2;
	}

	public Flipper(int x, int y) {
		this(x, y, 0);
	}

	@Override
	public void setAngle(double angle) {
		this.angle = angle;
		
		if (angle >= getEndAngle()) {
			this.angle = getEndAngle();
			angularMomentum = 0;
		} else if (angle <= getStartAngle()) {
			this.angle = getStartAngle();
			angularMomentum = 0;
		}
		
		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngle() {
		return angle;
	}

	@Override
	public double getStartAngle() {
		return startAngle;
	}

	@Override
	public double getEndAngle() {
		return endAngle;
	}

	@Override
	public void activate() {
		angularMomentum = ANGULAR_DELTA;
		setChanged();
		notifyObservers();
	}

	@Override
	public void deactivate() {
		angularMomentum = -ANGULAR_DELTA;
		setChanged();
		notifyObservers();
	}

	@Override
	public double getAngularMomentum() {
		return angularMomentum;
	}

}