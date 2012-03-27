import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import view.window.ApplicationWindow;
import controller.DesignModeViewModel;
import controller.GizmoballViewModel;


public class Driver
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

		final GizmoballViewModel viewmodel = new GizmoballViewModel();
		final DesignModeViewModel designmodeViewmodel = new DesignModeViewModel(viewmodel.getBoard(), viewmodel.getTriggerHandler());

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ApplicationWindow window = new ApplicationWindow(viewmodel, designmodeViewmodel);
				window.setVisible(true);
//				Providing same controller/model to another view instance shows mvc
//				ApplicationWindow window2 = new ApplicationWindow(viewmodel, designmodeViewmodel);
//				window2.setVisible(true);
			}
		});
	}
}