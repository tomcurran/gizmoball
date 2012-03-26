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

import view.icons.AbsorberIcon;
import view.icons.BallIcon;
import view.icons.CircleBumperIcon;
import view.icons.DeleteIcon;
import view.icons.RightFlipperIcon;
import view.icons.LeftFlipperIcon;
import view.icons.LinkGizmoIcon;
import view.icons.LinkKeyDownIcon;
import view.icons.LinkKeyUpIcon;
import view.icons.MoveGizmoIcon;
import view.icons.RotateGizmoIcon;
import view.icons.SquareBumperIcon;
import view.icons.TriangleBumperIcon;
import controller.DesignModeViewModel;
import controller.DesignModeViewModel.DesignCommand;
import controller.GizmoballViewModel;
import controller.GizmoballViewModel.UpdateReason;

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
				viewmodel.setCurrentCommand(DesignCommand.None);
			}
		});
		
		super.add(modeButton);
		
		designModeGroup = new ButtonGroup();
		designModePanel = new JPanel();
		designModePanel.setPreferredSize(new Dimension(100, 400));
		super.add(designModePanel);
		
		makeToolbarButton(new CircleBumperIcon(Color.green), 30, 30, "Circle bumper", DesignCommand.AddCircleBumper);
		makeToolbarButton(new SquareBumperIcon(Color.red), 30, 30, "Square bumper", DesignCommand.AddSquareBumper);
		makeToolbarButton(new TriangleBumperIcon(Color.blue), 30, 30, "Triangle bumper", DesignCommand.AddTriangleBumper);
		makeToolbarButton(new CircleBumperIcon(Color.cyan), 30, 30, "Accelerator gizmo", DesignCommand.AddAcceleratorGizmo);
		makeToolbarButton(new CircleBumperIcon(new Color(139, 0, 244)), 30, 30, "Portal gizmo", DesignCommand.AddPortalGizmo);
		makeToolbarButton(new CircleBumperIcon(Color.white), 30, 30, "Multiball gizmo", DesignCommand.AddMultiballGizmo);
		
		makeToolbarButton(new BallIcon(Color.yellow), 30, 30, "Ball", DesignCommand.AddBall);
		
		makeToolbarButton(new LeftFlipperIcon(Color.gray), 30, 30, "Left flipper", DesignCommand.AddLeftFlipper);
		makeToolbarButton(new RightFlipperIcon(Color.gray), 30, 30, "Right flipper", DesignCommand.AddRightFlipper);
		makeToolbarButton(new AbsorberIcon(Color.magenta), 66, 30, "Absorber", DesignCommand.AddAbsorber);

		makeToolbarButton(new LinkKeyDownIcon(Color.gray), 68, 30, "Allows you to link a gizmo to a key down event.", DesignCommand.ConnectKeyDown);
		makeToolbarButton(new LinkKeyUpIcon(Color.gray), 68, 30, "Allows you to link a gizmo to a key up event.", DesignCommand.ConnectKeyUp);
		makeToolbarButton(new LinkGizmoIcon(Color.gray), 68, 30, "Allows you to link two gizmos together.", DesignCommand.ConnectGizmo);
		makeToolbarButton(new DeleteIcon(Color.gray), 30, 30, "Delete gizmo/ball", DesignCommand.DeleteGizmo);
		makeToolbarButton(new RotateGizmoIcon(Color.gray), 30, 30, "Rotate gizmo", DesignCommand.RotateGizmo);
		makeToolbarButton(new MoveGizmoIcon(Color.gray), 30, 30, "Move gizmo", DesignCommand.MoveGizmo);
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
