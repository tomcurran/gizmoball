package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Map;

import model.IBoardItem;

public class TriggerHandler extends KeyAdapter implements KeyListener
{
	private Map<Integer, List<IBoardItem>> keyupTriggers;
	private Map<Integer, List<IBoardItem>> keydownTriggers;
	
	public TriggerHandler(Map<Integer, List<IBoardItem>> keyupTriggers, Map<Integer, List<IBoardItem>> keydownTriggers)
	{
		this.keyupTriggers = keyupTriggers;
		this.keydownTriggers = keydownTriggers;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (keydownTriggers.containsKey(key))
		{
			for (IBoardItem item: keydownTriggers.get(key))
			{
				item.trigger(null);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		if (keyupTriggers.containsKey(key))
		{
			for (IBoardItem item: keyupTriggers.get(key))
			{
				item.trigger(null);
			}
		}
	}
}
