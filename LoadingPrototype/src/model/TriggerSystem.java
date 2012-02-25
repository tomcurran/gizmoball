package model;

public class TriggerSystem {

	public void connect(IBoardItem item1, IBoardItem item2) {
		System.out.printf("Connect %s to %s\n", item1, item2);
	}

	public void keyConnect(int key, String direction, IBoardItem item) {
		System.out.printf("Connect key %d %s to %s\n", key, direction, item);
	}

}