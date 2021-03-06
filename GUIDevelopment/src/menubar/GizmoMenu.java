package menubar;

import gizmos.TriggerSystem;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import listeners.MenuListener;
import mainwindow.EditGrid;
import model.Board;
import model.BoardView;
import model.Physics;

public class GizmoMenu extends JMenuBar {
	
	
	private JMenu file;
	private JMenu edit;
	
	private JMenuItem save;
	private JMenuItem load;
	
	private MenuListener listener;
	
	
	public GizmoMenu(BoardView boardView, Physics physics,TriggerSystem trgsys, Board board ){
		super();
		listener = new MenuListener(physics, trgsys, board, boardView);
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		load.addActionListener(listener);
		
		
		file.add(save);
		file.add(load);
		
		this.add(file);
		this.add(edit);
	}

}
