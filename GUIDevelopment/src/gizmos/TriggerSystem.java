package gizmos;

import model.BoardItem;


public class TriggerSystem {

	public void connect(BoardItem item1, BoardItem item2) {
		System.out.printf("Connect %s to %s\n", item1, item2);
	}

	public void keyConnect(int key, String direction, BoardItem item) {
		System.out.printf("Connect key %d %s to %s\n", key, direction, item);
	}

}