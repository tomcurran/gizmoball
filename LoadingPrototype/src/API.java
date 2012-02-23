// imaginary api currently just prints data
public class API {

	public void addGizmo(String type, String name, int x, int y) {
		System.out.printf("%s(%s): x=%d y=%d\n", type, name, x, y);
	}

	public void addAbsorber(String name, int x1, int y1, int x2, int y2) {
		System.out.printf("Absorber(%s): x1=%d x2=%d y1=%d y2=%d\n", name, x1, y1, x2, y2);
	}

	public void addBall(String name, float f1, float f2, float f3, float f4) {
		System.out.printf("Ball(%s): center: x=%.1f,y=%.1f, velocity: vx=%.1f vy=%.1f\n", name, f1, f2, f3, f4);
	}

	public void rotate(String name) {
		System.out.printf("Rotate: %s\n", name);
	}

	public void delete(String name) {
		System.out.printf("Delete: %s\n", name);
	}

	public void move(String name, float f1, float f2) {
		System.out.printf("Move: %s x=? y=?\n", name, f1, f2);
	}

	public void connect(String name1, String name2) {
		System.out.printf("Connect: %s & %s\n", name1, name2);
	}

	public void keyConnect(int key, String direction, String name) {
		System.out.printf("KetConnect %d %s fires %s\n", key, direction, name);
	}

	public void setGravity(float f) {
		System.out.printf("Gravity: g=%.2f\n", f);
	}

	public void setFriction(float f1, float f2) {
		System.out.printf("Friction: mu=%.1f mu2=%.1f\n", f1, f2);
	}

}