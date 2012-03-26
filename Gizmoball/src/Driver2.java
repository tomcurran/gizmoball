import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.window.ApplicationWindow2;


public class Driver2
{
	public static void main(String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error setting look and feel.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		ApplicationWindow2 window = new ApplicationWindow2();
		window.setVisible(true);
	}
}
