package model.physics;

import java.util.Observable;
import java.util.Observer;

import model.Ball;
import model.physics.mit.Circle;
import model.physics.mit.Geometry;
import model.physics.mit.LineSegment;
import model.physics.mit.Vect;
import model.physics.mit.Geometry.VectPair;

public class PhysicsBall implements IPhysicsObject, Observer {
	
	private Circle circle;
	private Vect velocity;
	private Ball ball;

	/**
	 * Creates a physics representation of a ball. 
	 * 
	 * @param ball - the ball to represent. 
	 */
	public PhysicsBall(Ball ball) {
		this.ball = ball;
		ball.addObserver(this);
		update(ball, null);
	}

	public PhysicsObjectType getType() {
		return PhysicsObjectType.Ball;
	}

	public double timeUntilCollision(IPhysicsObject object) {
		switch (object.getType()) {
		case Ball:
			PhysicsBall ball2 = (PhysicsBall) object;
			return Geometry.timeUntilBallBallCollision(circle, velocity,
					ball2.circle, ball2.velocity);

		case Circle:
			return Geometry.timeUntilCircleCollision((Circle) object, circle,
					velocity);

		case LineSegment:
			return Geometry.timeUntilWallCollision((LineSegment) object,
					circle, velocity);

		case RotatingCircle:
			RotatingCircle c = (RotatingCircle) object;
			return Geometry.timeUntilRotatingCircleCollision(c.getCircle(),
					c.getCentre(), c.getAngularVelocity(), circle, velocity);

		case RotatingWall:
			RotatingWall wall = (RotatingWall) object;
			return Geometry.timeUntilRotatingWallCollision(wall.getLine(),
					wall.getCentre(), wall.getAngularVelocity(), circle,
					velocity);

		default:
			throw new IllegalStateException(String.format(
					"Cannot collide ball with %s object.", object.getClass()
							.getName()));
		}
	}

	public void reflect(IPhysicsObject object) {
		switch (object.getType()) {
		case Ball:
			PhysicsBall ball = (PhysicsBall) object;
			VectPair velocities = Geometry.reflectBalls(circle.getCenter(),
					this.ball.getMass(), velocity, ball.circle.getCenter(),
					ball.ball.getMass(), ball.velocity);
			velocity = velocities.v1;
			ball.velocity = velocities.v2;
			break;

		case Circle:
			velocity = Geometry.reflectCircle(((Circle) object).getCenter(),
					circle.getCenter(), velocity);
			break;

		case LineSegment:
			velocity = Geometry.reflectWall((LineSegment) object, velocity);
			break;

		case RotatingCircle:
			RotatingCircle c = (RotatingCircle) object;
			velocity = Geometry.reflectRotatingCircle(c.getCircle(),
					c.getCentre(), c.getAngularVelocity(), circle, velocity,
					0.85);
			break;

		case RotatingWall:
			RotatingWall w = (RotatingWall) object;
			velocity = Geometry.reflectRotatingWall(w.getLine(), w.getCentre(),
					w.getAngularVelocity(), circle, velocity, 0.85);
			break;

		default:
			throw new IllegalStateException(String.format(
					"Cannot reflect ball with %s object.", object.getClass()
							.getName()));
		}

		this.ball.setVelocity(velocity.x(), velocity.y());
	}

	public void move(double timedelta) {
		double dx = timedelta * velocity.x();
		double dy = timedelta * velocity.y();

		ball.moveBy(dx, dy);
	}

	public void applyFrictionAndGravity(double timedelta, double gravity,
			double mu, double mu2) {
		if (ball.getIsCaptured() == false) {
			double friction = 1 - mu * timedelta - mu2
					* Math.abs(velocity.length()) * timedelta;
			ball.setVelocity(velocity.x() * friction, velocity.y() * friction
					+ gravity * timedelta);
		}
	}

	@Override
	public void update(Observable source, Object arg) {
		circle = new Circle(ball.getX(), ball.getY(), ball.getRadius());
		velocity = new Vect(ball.getXVelocity(), ball.getYVelocity());
	}

	public Ball getBall() {
		return ball;
	}

	public boolean getIsStationary() {
		return velocity.length() == 0;
	}
}
