package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class BoardItemBase extends Observable implements IBoardItem
{
	private List<IBoardItem> connectedItems;
	protected boolean triggered;
	
	public BoardItemBase()
	{
		connectedItems = new ArrayList<IBoardItem>();
		triggered = false;
	}
	
	@Override
	public void connect(IBoardItem item)
	{
		connectedItems.add(item);
	}

	@Override
	public List<IBoardItem> getConnections() {
		return connectedItems;
	}

	@Override
	public void trigger(IBoardItem triggeringItem)
	{
		for (IBoardItem item: connectedItems)
		{
			item.doAction();
		}
	}
	
	@Override
	public void doAction()
	{
		triggered = !triggered;
	}
	
	@Override
	public boolean getTriggeredState()
	{
		return triggered;
	}
}
