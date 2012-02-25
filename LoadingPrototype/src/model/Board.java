package model;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private int width, height;
	private List<IBoardItem> items;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.items = new ArrayList<IBoardItem>();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addItem(IBoardItem item) {
		items.add(item);
	}

	public void removeItem(IBoardItem item) {
		items.remove(item);
	}

	public List<IBoardItem> getItems() {
		return items;
	}

}