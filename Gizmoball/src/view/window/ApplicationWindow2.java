package view.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import view.gizmos.AnimationPanel2;
import controller.DesignModeViewModel;
import controller.GizmoballViewModel;
import controller.GizmoballViewModel.UpdateReason;
import controller.MagicKeyListener;
import exceptions.BadFileException;

/**
 * Main application window.
 */
@SuppressWarnings("serial")
public class ApplicationWindow2 extends JFrame implements Observer
{
	public static final int L = 20;
	
	private GizmoballViewModel viewmodel;
	private DesignModeViewModel designmodeViewmodel;

	private JMenuItem newMenuItem, openMenuItem, saveMenuItem;
	private AnimationPanel2 boardView;
	private ToolbarButtonArea toolbar;
	private JPanel contentPane;
	private JLabel statusBar;
	
	public ApplicationWindow2()
	{
		super("Gizmoball");
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		viewmodel = new GizmoballViewModel();
		viewmodel.addObserver(this);
		
		designmodeViewmodel = new DesignModeViewModel(viewmodel.getBoard(), viewmodel.getTriggerHandler());
		designmodeViewmodel.addObserver(this);
		
		initialiseComponents();
		initialiseActionListeners();
	}
	
	
	private void initialiseComponents()
	{
		toolbar = new ToolbarButtonArea(viewmodel, designmodeViewmodel);
		boardView = new AnimationPanel2(viewmodel, designmodeViewmodel);

		JMenuBar menubar = new JMenuBar();
		super.setJMenuBar(menubar);
		
		JMenu fileMenu = new JMenu("File");
		menubar.add(fileMenu);
		
		newMenuItem = new JMenuItem("New");
		fileMenu.add(newMenuItem);
		
		openMenuItem = new JMenuItem("Open");
		fileMenu.add(openMenuItem);
		
		saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		
		statusBar = new JLabel("Status.");
		statusBar.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(500, 410));
		contentPane.add(boardView, BorderLayout.CENTER);
		contentPane.add(toolbar, BorderLayout.EAST);
		contentPane.add(statusBar, BorderLayout.SOUTH);
		
		super.setContentPane(contentPane);
		super.setMinimumSize(new Dimension(500, 410));
		
		super.pack();
		super.requestFocus();
	}
	
	
	private void initialiseActionListeners()
	{
		final JFrame parent = this;
		
		newMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				viewmodel.newGame();
			}
		});
		
		openMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
				
				if (chooser.showOpenDialog(chooser) == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();
					
					try
					{
						viewmodel.loadGame(file.getAbsolutePath());
					}
					catch (FileNotFoundException ex)
					{
						JOptionPane.showMessageDialog(parent, "The file cannot be found.", "Load error", JOptionPane.ERROR);
					}
					catch (IOException ex)
					{
						JOptionPane.showMessageDialog(parent, "Error reading file: " + ex.getMessage(), "Load error", JOptionPane.ERROR);
					}
					catch (BadFileException ex)
					{
						JOptionPane.showMessageDialog(parent, "The file format is incorrect.", "Load error", JOptionPane.ERROR);
					}
				}
			}
		});
		
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));

				if (chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION)
				{
					File file = chooser.getSelectedFile();

					try
					{
						viewmodel.saveGame(file.getAbsolutePath());
					}
					catch (IOException ex)
					{
						JOptionPane.showMessageDialog(parent, "Error saving file: " + ex.getMessage(), "Save error", JOptionPane.ERROR);
					}
				}
			}
		});
	}
	
	
	@Override
	public void update(Observable source, Object arg)
	{
		UpdateReason reason = (UpdateReason)arg;
		
		switch (reason)
		{
			case RunStateChanged:
				toolbar.setRunMode(viewmodel.getIsRunning());
				
				if (viewmodel.getIsRunning())
					contentPane.remove(statusBar);
				else
					contentPane.add(statusBar, BorderLayout.SOUTH);
					
				break;
				
			case StatusChanged:
				statusBar.setText(designmodeViewmodel.getStatusMessage());
				break;
		}
	}
	


	private KeyListener linkListener;

	public void addLinkKeyListener(KeyListener linkListener) {
		boardView.addKeyListener(linkListener);
		this.linkListener = linkListener;
	}

	public void switchListeners(MagicKeyListener magicListener) {
		boardView.removeKeyListener(linkListener);
		boardView.addKeyListener(magicListener);
		boardView.requestFocus();
	}

	public void addMagicListener(MagicKeyListener magicListener) {
		boardView.addKeyListener(magicListener);
	}

}
