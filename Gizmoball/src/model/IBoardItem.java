package model;

import java.util.List;

/**
 * Represents balls and gizmos on the board.
 */
public interface IBoardItem
{
	/**
	 * Moves the item to integer coordinates.
	 * @param x The value to set the x coordinate to.
	 * @param y The value to set the y coordinate to.
	 */
	void move(int x, int y);
	
	/**
	 * Moves the item to float coordinates.
	 * @param x The value to set the x coordinate to.
	 * @param y The value to set the y coordinate to.
	 */
	void move(double dx, double dy);
	
	/**
	 * Rotates the item by 90 degrees in a clockwise direction.
	 */
	void rotate();
	
	/**
	 * Connects an item to this item such that when this item is triggered, the specified
	 * item will also be triggered.
	 * @param item The item to add to the list of connected items.
	 */
	void connect(IBoardItem item);

	List<IBoardItem> getConnections();
	
	/**
	 * Triggers this item.
	 */
	void trigger(IBoardItem item);
	
	/**
	 * Does the action.
	 */
	void doAction();
	
	/**
	 * Gets the triggered state of the item.
	 * @return
	 */
	boolean getTriggeredState();
	
	/**
	 * Gets the list of connected items.
	 * @return
	 */
	List<IBoardItem> getConnectedItems();
}
