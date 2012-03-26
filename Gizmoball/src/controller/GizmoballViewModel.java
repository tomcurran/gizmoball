package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;

import javax.swing.Timer;

import model.Board;
import model.IPhysicsEngine;
import model.Loader;
import model.physics.MitPhysicsEngineWrapper;
import exceptions.BadFileException;


/**
 * Represents the data and logic required by a view.  This is a viewmodel class in the MVVM pattern.
 */
public class GizmoballViewModel extends Observable implements ActionListener
{
	private static final int FRAMES_PER_SEC = 30;
	private static final int DEFAULT_BOARD_WIDTH = 20, DEFAULT_BOARD_HEIGHT = 20;
	private Board board;
	private Timer timer;
	private IPhysicsEngine engine;
	private TriggerHandler triggerhandler;
	
	public enum UpdateReason
	{
		RunStateChanged,
		BoardChanged,
		SelectedToolChanged
	}
	
	public GizmoballViewModel()
	{
		timer = new Timer(1000 / FRAMES_PER_SEC, this);
		board = new Board(DEFAULT_BOARD_WIDTH, DEFAULT_BOARD_HEIGHT);
		engine = new MitPhysicsEngineWrapper();
		triggerhandler = new TriggerHandler();
	}
	
	
	/**
	 * Clears the board.
	 */
	public void newGame()
	{
		board.getBalls().clear();
		board.getGizmos().clear();
		triggerhandler.clear();
		this.setChanged();
		this.notifyObservers(UpdateReason.BoardChanged);
	}
	
	
	/**
	 * Loads the board from a file.
	 * @param path The file path to load.
	 * @throws FileNotFoundException Thrown if the file is not found.
	 * @throws IOException Thrown if there is a problem reading the file.
	 * @throws BadFileException Thrown if the file format is invalid.
	 */
	public void loadGame(String path) throws FileNotFoundException, IOException, BadFileException
	{
		newGame();
		
		Loader loader = new Loader(path);
		loader.load(engine, board);
		triggerhandler.addLinks(loader.getKeyUpTriggers(), loader.getKeyDownTriggers());
		this.setChanged();
		this.notifyObservers(UpdateReason.BoardChanged);
	}
	
	
	/**
	 * Saves the board to a file.
	 * @param path The path to save to.
	 * @throws IOException Thrown if there is an error writing to the file.
	 */
	public void saveGame(String path) throws IOException
	{
		//TODO: saving
	}
	
	
	/**
	 * Toggles the run state of the engine.
	 */
	public void toggleRunState()
	{
		if (this.getIsRunning())
			stopRunning();
		else
			startRunning();
	}
	
	/**
	 * Starts the engine running.
	 */
	public void startRunning()
	{
		if (!this.getIsRunning())
		{
			engine.initialise(board);
			timer.start();
			this.setChanged();
			this.notifyObservers(UpdateReason.RunStateChanged);
		}
	}
	
	/**
	 * Stops the engine from running.
	 */
	public void stopRunning()
	{
		if (this.getIsRunning())
		{
			timer.stop();
			this.setChanged();
			this.notifyObservers(UpdateReason.RunStateChanged);
		}
	}
	
	/**
	 * Gets the run state of the engine.
	 * @return True if the engine is running; otherwise, false.
	 */
	public boolean getIsRunning()
	{
		return timer.isRunning();
	}
	
	/**
	 * Callback method for engine timer.
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		engine.calculateState((double)1 / FRAMES_PER_SEC);
		this.setChanged();
		this.notifyObservers(UpdateReason.BoardChanged);
	}
	
	
	/**
	 * Gets the board instance.
	 * @return
	 */
	public Board getBoard()
	{
		return board;
	}
}
