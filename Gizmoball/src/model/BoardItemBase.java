package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public abstract class BoardItemBase extends Observable implements IBoardItem
{
	private List<IBoardItem> connectedItems;
	private boolean triggered;
	
	public BoardItemBase()
	{
		connectedItems = new ArrayList<IBoardItem>();
	}
	
	@Override
	public void connect(IBoardItem item)
	{
		connectedItems.add(item);
	}
	
	@Override
	public void trigger(IBoardItem triggeringItem)
	{
		for (IBoardItem item: connectedItems)
		{
			item.trigger(this);
		}
		
		triggered = !triggered;
	}
	
	@Override
	public boolean getTriggeredState()
	{
		return triggered;
	}
	
	
}
