package model;

import java.util.ArrayList;

import java.util.List;


public class Board {

	private int width, height;
	private List<BoardItem> items;

	public Board(int width, int height) {
		this.width = width;
		this.height = height;
		this.items = new ArrayList<BoardItem>();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void addItem(BoardItem item) {
		items.add(item);
	}

	public void removeItem(BoardItem item) {
		items.remove(item);
	}

	public List<BoardItem> getItems() {
		return items;
	}

}