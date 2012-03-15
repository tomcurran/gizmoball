package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import model.Ball;
import model.Board;
import model.IPhysicsEngine;
import model.Loader;
import model.gizmos.AbsorberGizmo;
import model.gizmos.CircleBumper;
import model.gizmos.Flipper;
import model.gizmos.IGizmo;
import model.gizmos.LeftFlipper;
import model.gizmos.RightFlipper;
import model.gizmos.SquareBumper;
import model.gizmos.TriangleBumper;
import view.window.AnimationPanel;
import view.window.ApplicationWindow;
import exceptions.BadFileException;

public class Controller {

	private Board model;
	private IPhysicsEngine engine;
	private ApplicationWindow appWin;
	private TriggerHandler handler;
	private IGizmo selectedGizmo;

	private int ax;
	private int ay;
	private int ax2;
	private int ay2;

	private char command;
	private IGizmo keyLinkGiz;
	private Integer keyLinkKey;

	public Controller(IPhysicsEngine physics, Board model,
			ApplicationWindow applicationWindow) {
		this.model = model;
		engine = physics;
		appWin = applicationWindow;
		handler = new TriggerHandler();
		addListeners();
	}

	public void addListeners() {
		appWin.addButtonListeners(new ButtonListener());
		appWin.addGridListner(new GridListener());
		appWin.addMenuListner(new SavesListener());
		appWin.addEditKeyListener(new LinkListener());
	}

	private class SavesListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Save")) {
				System.out.println("Gotta Save!");
			} else {
				JFileChooser chooser = new JFileChooser();
				chooser.showOpenDialog(chooser);
				File file = chooser.getSelectedFile();
				String fileName = file.getAbsolutePath();

				try {
					Loader loader = new Loader(fileName);
					loader.parseFile(engine);
					loader.loadItems(model);

					handler = new TriggerHandler(loader.getKeyUpTriggers(),
							loader.getKeyDownTriggers());
					MagicKeyListener listener = new MagicKeyListener(handler);
					appWin.addKeyListener(listener);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (BadFileException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}

			}
		}

	}

	private class GridListener implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			AnimationPanel ap = (AnimationPanel) e.getComponent();

			int x = Math.round(e.getX() / ApplicationWindow.L);
			int y = Math.round(e.getY() / ApplicationWindow.L);
			int w = 0;
			int h = 0;

			IGizmo g = null;

			switch (command) {

			case 'C':
				g = new CircleBumper(x, y);
				w = g.getWidth();
				h = g.getHeight();
				if (validLocation(x, y, w, h)) {
					model.addGizmo(g);
					ap.addMouseFollower(g.getX(), g.getY(), w, h, Color.red);
				}
				break;

			case 'S':
				g = new SquareBumper(x, y);
				w = g.getWidth();
				h = g.getHeight();
				if (validLocation(x, y, w, h)) {
					model.addGizmo(g);
					ap.addMouseFollower(g.getX(), g.getY(), w, h, Color.red);
				}
				break;

			case 'T':
				g = new TriangleBumper(x, y, 0);
				w = g.getWidth();
				h = g.getHeight();
				if (validLocation(x, y, w, h)) {
					model.addGizmo(g);
					ap.addMouseFollower(g.getX(), g.getY(), w, h, Color.red);
				}
				break;

			case 'F':
				g = new RightFlipper(x, y);
				w = g.getWidth();
				h = g.getHeight();
				if (validLocation(x, y, w, h)) {
					model.addGizmo(g);
					ap.addMouseFollower(g.getX(), g.getY(), w, h, Color.red);
				}
				break;

			case 'G':
				g = new LeftFlipper(x, y);
				w = g.getWidth();
				h = g.getHeight();
				if (validLocation(x, y, w, h)) {
					model.addGizmo(g);
					ap.addMouseFollower(g.getX(), g.getY(), w, h, Color.red);
				}
				break;

			case 'B':
				Ball b = new Ball(e.getX(), e.getY(), 3, 4);
				if (validLocation(x, y, 1, 1)) {
					model.addBall(b);
					ap.addMouseFollower((int) b.getX(), (int) b.getY(), 0, 0,
							Color.red); // TODO doubles not int/zero ?
				}

				break;

			case 'R':
				IGizmo rotateGiz = model.getGizmoAt(x, y);
				if (rotateGiz != null) {
					try {
						rotateGiz.rotate();
					} catch (UnsupportedOperationException e1) {
						// TODO Do all the rotation functions actually work?
					}
					appWin.repaint();
				}
				break;
			case 'D':
				IGizmo deleteGiz = model.getGizmoAt(x, y);
				if (deleteGiz != null) {
					model.removeGizmo(deleteGiz);
					appWin.repaint();
				} else {
					// TODO Try to select a ball at (e.getX(), e.getY()) and
					// delete if there is one.
				}
				break;
			case 'K':
				keyLinkGiz = model.getGizmoAt(x, y);

				if (keyLinkKey != null) {
					handler.addLink(keyLinkKey, keyLinkGiz);
					keyLinkKey = null;
				}

			default:
				break;
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			AnimationPanel ap = (AnimationPanel) e.getComponent();
			ap.mouseOverGrid();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			AnimationPanel ap = (AnimationPanel) e.getComponent();
			ap.mouseOverGrid();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			switch (command) {
			case 'A':
				ax = Math.round(e.getX() / ApplicationWindow.L);
				ay = Math.round(e.getY() / ApplicationWindow.L);
				break;
			}
			drawValidityBox(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			switch (command) {
			case 'A':
				ax2 = Math.round(e.getX() / ApplicationWindow.L) + 1;
				ay2 = Math.round(e.getY() / ApplicationWindow.L) + 1;
				selectedGizmo = new AbsorberGizmo(ax, ay, ax2, ay2);
				if (validLocation(selectedGizmo.getX(), selectedGizmo.getY(),
						selectedGizmo.getWidth(), selectedGizmo.getHeight())) {
					model.addGizmo(selectedGizmo);
				}
				ax = ax2 - 1;
				ay = ay2 - 1;
				break;
			}
			drawValidityBox(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			switch (command) {
			case 'A':
				ax2 = Math.round(e.getX() / ApplicationWindow.L) + 1;
				ay2 = Math.round(e.getY() / ApplicationWindow.L) + 1;
				break;
			}
			drawValidityBox(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			ax = e.getX() / ApplicationWindow.L;
			ay = e.getY() / ApplicationWindow.L;
			ax2 = ax + 1;
			ay2 = ay + 1;
			drawValidityBox(e);
		}

		private void drawValidityBox(MouseEvent e) {

			AnimationPanel ap = (AnimationPanel) e.getComponent();

			int w = 1;
			int h = 1;

			int x = e.getX() / ApplicationWindow.L;
			int y = e.getY() / ApplicationWindow.L;

			switch (command) {
			case 'C':
				selectedGizmo = new CircleBumper(x, y);
				break;

			case 'S':
				selectedGizmo = new SquareBumper(x, y);
				break;

			case 'T':
				selectedGizmo = new TriangleBumper(x, y, 0);
				break;

			case 'F':
				selectedGizmo = new RightFlipper(x, y);
				break;

			case 'G':
				selectedGizmo = new LeftFlipper(x, y);
				break;

			case 'A':
				selectedGizmo = new AbsorberGizmo(ax, ay, ax2, ay2);
				x = selectedGizmo.getX();
				y = selectedGizmo.getY();
				break;

			default:
				selectedGizmo = null;
				break;
			}

			if (selectedGizmo != null) {
				w = selectedGizmo.getWidth();
				h = selectedGizmo.getHeight();
			}

			ap.addMouseFollower(x, y, w, h,
					(validLocation(x, y, w, h) ? Color.GREEN : Color.RED));

		}
	}

	private boolean validLocation(int x, int y, int w, int h) {
		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				if (model.getGizmoAt(x + i, y + j) != null) {
					return false;
				}
			}
		}
		return true;
	}

	private class LinkListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (keyLinkGiz != null) {
				handler.addLink(arg0.getKeyCode(), keyLinkGiz);
				keyLinkGiz = null;
			} else {
				keyLinkKey = arg0.getKeyCode();
			}
		}

	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println(e.getActionCommand());

			command = e.getActionCommand().toUpperCase().charAt(0);

			if (command == 'M') {
				appWin.flipMode();
				model.runMode();
				JButton temp = (JButton) e.getSource();
				if (temp.getText().equals("Play")) {
					temp.setText("Edit");
				} else {
					temp.setText("Play");
				}
			}

			if (command == 'K') {

			}
		}
	}

	@SuppressWarnings("unused")
	private boolean checkPlacementRange() {
		// TODO Currently gizmos that go over more than one grid square
		// (flippers/absorbers/potentially balls) can be placed over current
		// gizmos. This method will be used to check the area the new flipper or
		// absorber will take up. Returns true if safe to place otherwise flase.
		return false;
	}

}
