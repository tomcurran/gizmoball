package model;

public class Physics {

	public void setGravity(double g) {
		System.out.printf("Gravity: %.2f\n", g);
	}

	public void setFriction(double mu, double mu2) {
		System.out.printf("Friction: mu=%.1f,mu2=%.1f\n", mu, mu2);
	}

}