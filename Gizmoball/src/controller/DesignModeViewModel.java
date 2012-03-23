package controller;

import java.util.Observable;

public class DesignModeViewModel extends Observable
{
	private DesignCommand currentCommand;
	
	public enum UpdateReason
	{
		SelectedToolChanged
	}
	
	public enum DesignCommand
	{
		None,
		AddCircleBumper,
		AddSquareBumper,
		AddTriangleBumper,
		AddLeftFlipper,
		AddRightFlipper,
		AddAbsorber,
		AddBall,
		DeleteGizmo,
		RotateGizmo,
		MoveGizmo,
		ConnectKeyUp,
		ConnectKeyDown,
		ConnectGizmo
	}
	
	/**
	 * Constructor.
	 */
	public DesignModeViewModel()
	{
		currentCommand = DesignCommand.None;
	}
	
	/**
	 * Gets the currently selected command.
	 */
	public DesignCommand getCurrentCommand()
	{
		return currentCommand;
	}
	
	/**
	 * Sets the current command and notifies observers of the change.
	 */
	public void setCurrentCommand(DesignCommand value)
	{
		currentCommand = value;
		this.setChanged();
		this.notifyObservers(UpdateReason.SelectedToolChanged);
	}
}
