package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class BoardItemBase extends Observable implements IBoardItem {
	
	protected List<IBoardItem> connectedItems;
	protected boolean triggered;

	/**
	 * Constructs a new BoardItemBase for representing the links between 
	 * gizmos. 
	 */
	public BoardItemBase() {
		connectedItems = new ArrayList<IBoardItem>();
		triggered = false;
	}

	@Override
	public void connect(IBoardItem item) {
		connectedItems.add(item);
	}

	@Override
	public void trigger(IBoardItem triggeringItem) {
		for (IBoardItem item : connectedItems) {
			item.doAction();
		}
	}

	@Override
	public void doAction() {
		triggered = !triggered;
	}

	@Override
	public boolean getTriggeredState() {
		return triggered;
	}

	@Override
	public List<IBoardItem> getConnectedItems() {
		return connectedItems;
	}

}
