package view.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import view.icons.AIcon;
import view.icons.BIcon;
import view.icons.CBIcon;
import view.icons.DGIcon;
import view.icons.FLIcon;
import view.icons.FRIcon;
import view.icons.LGIcon;
import view.icons.LKIcon;
import view.icons.MGIcon;
import view.icons.RGIcon;
import view.icons.SBIcon;
import view.icons.TBIcon;
import controller.DesignModeViewModel;
import controller.DesignModeViewModel.DesignCommand;
import controller.DesignModeViewModel.UpdateReason;
import controller.GizmoballViewModel;

public class ToolbarButtonArea extends JPanel implements Observer, ActionListener
{
	private GizmoballViewModel mainviewmodel;
	private DesignModeViewModel viewmodel;
	
	private JButton modeButton;
	private JPanel designModePanel;
	private ButtonGroup designModeGroup;	
	
	public ToolbarButtonArea(GizmoballViewModel mainviewmodel, DesignModeViewModel viewmodel)
	{
		this.mainviewmodel = mainviewmodel;
		this.viewmodel = viewmodel;
		
		viewmodel.addObserver(this);
		initialiseComponents();
	}
	
	private void initialiseComponents()
	{
		super.setPreferredSize(new Dimension(100, 400));
		
		modeButton = new JButton("Play");
		modeButton.setPreferredSize(new Dimension(68, 30));
		modeButton.setToolTipText("Switches between Edit and Play mode.");
		modeButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				mainviewmodel.toggleRunState();
			}
		});
		
		super.add(modeButton);
		
		designModeGroup = new ButtonGroup();
		designModePanel = new JPanel();
		designModePanel.setPreferredSize(new Dimension(100, 350));
		super.add(designModePanel);
		
		makeToolbarButton(new CBIcon(Color.green), 30, 30, "While selected you can place CircleGizmos to the grid by clicking it.", DesignCommand.AddCircleBumper);
		makeToolbarButton(new SBIcon(Color.red), 30, 30, "While selected you can place SquareGizmos to the grid by clicking it.", DesignCommand.AddSquareBumper);
		makeToolbarButton(new TBIcon(Color.blue), 30, 30, "While selected you can place TriangleGizmos to the grid by clicking it. ", DesignCommand.AddTriangleBumper);
		
		makeToolbarButton(new BIcon(Color.yellow), 30, 30, "While selected you can place balls to the grid by clicking it. ", DesignCommand.AddBall);
		
		makeToolbarButton(new FLIcon(Color.gray), 30, 30, "While selected you can place right flippers to the grid. ", DesignCommand.AddLeftFlipper);
		makeToolbarButton(new FRIcon(Color.gray), 30, 30, "While selected you can place left flippers to the grid. ", DesignCommand.AddRightFlipper);
		makeToolbarButton(new AIcon(Color.magenta), 66, 30, "While selected you can place absorbers to the grid by clicking it (Click, hold and drag to add large absorbers).", DesignCommand.AddAbsorber);

		makeToolbarButton(new LKIcon(Color.gray), 68, 30, "Allows you to link a gizmo to a key. ", DesignCommand.ConnectKeyUp);
		makeToolbarButton(new LGIcon(Color.gray), 68, 30, "Allows you to link two gizmos together", DesignCommand.ConnectGizmo);
		makeToolbarButton(new DGIcon(Color.gray), 30, 30, "While selected you can delete grid elements by clicking them. ", DesignCommand.DeleteGizmo);
		makeToolbarButton(new RGIcon(Color.gray), 30, 30, "While selected you can rotate rotatable grid elements by clicking them. ", DesignCommand.RotateGizmo);
		makeToolbarButton(new MGIcon(Color.gray), 30, 30, "Wile selected you can move gizmos to valid squares", DesignCommand.MoveGizmo);
	}
	
	
	private void makeToolbarButton(Icon icon, int width, int height, String tooltip, DesignCommand command)
	{
		CommandButton button = new CommandButton(icon, command);
		
		button.setPreferredSize(new Dimension(width, height));
		button.setToolTipText(tooltip);
		button.addActionListener(this);
		
		designModeGroup.add(button);
		designModePanel.add(button);
	}
	
	
	public void setRunMode(boolean value)
	{
		if (value == true)
		{
			designModePanel.setVisible(false);
			modeButton.setText("Edit");
		}
		else
		{
			designModePanel.setVisible(true);
			modeButton.setText("Run");
		}
	}

	
	@Override
	public void update(Observable source, Object arg)
	{
		UpdateReason reason = (UpdateReason)arg;
		
		switch (reason)
		{
			case SelectedToolChanged:
				if (viewmodel.getCurrentCommand() == DesignCommand.None)
					designModeGroup.clearSelection();
				
				break;
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		CommandButton source = (CommandButton)e.getSource();
		viewmodel.setCurrentCommand(source.getCommand());
	}
	
	
	private class CommandButton extends JToggleButton
	{
		private DesignCommand command;
		
		public CommandButton(Icon icon, DesignCommand command)
		{
			super(icon);
			this.command = command;
		}
		
		public DesignCommand getCommand()
		{
			return command;
		}
	}
}
