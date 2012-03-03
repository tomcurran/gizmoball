package menubar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GizmoMenu extends JMenuBar {
	
	
	private JMenu file;
	private JMenu edit;
	
	private JMenuItem save;
	private JMenuItem load;
	
	
	public GizmoMenu(){
		super();
		
		file = new JMenu("File");
		edit = new JMenu("Edit");
		
		save = new JMenuItem("Save");
		load = new JMenuItem("Load");
		
		file.add(save);
		file.add(load);
		
		this.add(file);
		this.add(edit);
	}

}
