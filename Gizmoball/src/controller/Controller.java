package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private IGizmo selectedGizmo;

	private boolean flipperLeft;
	private int ax;
	private int ay;
	private int ax2;
	private int ay2;

	private char command;

	public Controller(IPhysicsEngine physics, Board model,
			ApplicationWindow applicationWindow) {
		this.model = model;
		engine = physics;

		appWin = applicationWindow;

		flipperLeft = true;
		

		addListeners();
	}

	public void addListeners() {
		appWin.addButtonListeners(new ButtonListener());
		appWin.addGridListner(new GridListener());
		appWin.addMenuListner(new SavesListener());
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

					TriggerHandler handler = new TriggerHandler(
							loader.getKeyUpTriggers(),
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
			switch (command) {

			case 'C':
				if (model.getGizmoAt(e.getX() / ApplicationWindow.L, e.getY()
						/ ApplicationWindow.L) == null) {
					CircleBumper cb = new CircleBumper(Math.round(e.getX()
							/ ApplicationWindow.L), Math.round(e.getY()
							/ ApplicationWindow.L));
					model.addGizmo(cb);
					ap.addMouseFollower(cb.getX(), cb.getY(), cb.getWidth(), cb.getHeight(),
							Color.red);
				}
				break;

			case 'S':
				if (model.getGizmoAt(e.getX() / ApplicationWindow.L, e.getY()
						/ ApplicationWindow.L) == null) {
					SquareBumper sb = new SquareBumper(Math.round(e.getX()
							/ ApplicationWindow.L), Math.round(e.getY()
							/ ApplicationWindow.L));
					model.addGizmo(sb);
					ap.addMouseFollower(sb.getX(), sb.getY(), sb.getWidth(), sb.getHeight(),
							Color.red);
				}
				break;

			case 'T':
				if (model.getGizmoAt(e.getX() / ApplicationWindow.L, e.getY()
						/ ApplicationWindow.L) == null) {
					TriangleBumper tb = new TriangleBumper(Math.round(e.getX()
							/ ApplicationWindow.L), Math.round(e.getY()
							/ ApplicationWindow.L), 0);
					model.addGizmo(tb);
					ap.addMouseFollower(tb.getX(), tb.getY(), tb.getWidth(), tb.getHeight(),
							Color.red);
				}
				break;

			case 'B':
				if (model.getGizmoAt(e.getX() / ApplicationWindow.L, e.getY()
						/ ApplicationWindow.L) == null) {
					Ball b = new Ball(e.getX(), e.getY(), 3, 4);
					model.addBall(b);
					ap.addMouseFollower((int)b.getX(), (int)b.getY(), 0, 0, // TODO doubles not int/zero ?
							Color.red);
				}
				break;

			case 'F':
				if (model.getGizmoAt(e.getX() / ApplicationWindow.L, e.getY()
						/ ApplicationWindow.L) == null) {
					Flipper f;
					if (flipperLeft) {
						f = new LeftFlipper(Math.round(e.getX()
								/ ApplicationWindow.L), Math.round(e.getY()
								/ ApplicationWindow.L));
						model.addGizmo(f);
					} else {
						f = new RightFlipper(Math.round(e.getX()
								/ ApplicationWindow.L), Math.round(e.getY()
								/ ApplicationWindow.L));
						model.addGizmo(f);
					}
					ap.addMouseFollower(f.getX(), f.getY(), f.getWidth(), f.getHeight(),
							Color.red);
				}
				break;

			case 'R':
				IGizmo rotateGiz = model.getGizmoAt(e.getX()
						/ ApplicationWindow.L, e.getY() / ApplicationWindow.L);
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
				IGizmo deleteGiz = model.getGizmoAt(e.getX()
						/ ApplicationWindow.L, e.getY() / ApplicationWindow.L);
				if (deleteGiz != null) {
					model.removeGizmo(deleteGiz);
					appWin.repaint();
				} else {
					// TODO Try to select a ball at (e.getX(), e.getY()) and
					// delete if there is one.
				}
				break;
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
				selectedGizmo = new AbsorberGizmo(ax, ay, 1, 1);
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
//				ax = 0;
//				ay = 0;
				selectedGizmo = new AbsorberGizmo(ax, ay, ax2, ay2);
				model.addGizmo(selectedGizmo);
				selectedGizmo = null;
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
				selectedGizmo = new AbsorberGizmo(ax, ay, ax2, ay2);
				break;
			}
			drawValidityBox(e);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			drawValidityBox(e);
		}
	
		private void drawValidityBox(MouseEvent e) {

			AnimationPanel ap = (AnimationPanel) e.getComponent();
			
			int w = 1;
			int h = 1;
			
			int ex = e.getX() / ApplicationWindow.L;
			int ey = e.getY() / ApplicationWindow.L;
			
			
			switch (command) {
			case 'C':
				selectedGizmo = new CircleBumper(ex, ey);
				break;

			case 'S':
				selectedGizmo = new SquareBumper(ex, ey);
				break;

			case 'T':
				selectedGizmo = new TriangleBumper(ex, ey, 0);
				break;

			case 'F':
				selectedGizmo = new LeftFlipper(ex, ey);
				break;
				
			case 'A':
				 break;
	
			default:
				selectedGizmo = null;
				break;
			}

			if (selectedGizmo != null) {
				w = selectedGizmo.getWidth();
				h = selectedGizmo.getHeight();
				if (selectedGizmo.getClass().equals(AbsorberGizmo.class)) {
					ex = selectedGizmo.getX();
					ey = selectedGizmo.getY();
				}
			}

			for (int i = 0; i < w; i++) {
				for (int j = 0; j < h; j++) {
					if (model.getGizmoAt(ex + i, ey + j) != null) {
						ap.addMouseFollower(ex, ey, w, h, Color.red);
						return;
					}
				}
			}

			ap.addMouseFollower(ex, ey, w, h, Color.green);
		}
	}

	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			System.out.println(e.getActionCommand());
			
			command = e.getActionCommand().toUpperCase().charAt(0);

			if (command == 'F') {
				if (e.getActionCommand().equals("FlipperLeft")) {
					flipperLeft = true;
				} else {
					flipperLeft = false;
				}
			}

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
		}
	}

	@SuppressWarnings("unused")
	private boolean checkPlacementRange() {
		// TODO Currently gizmos that go over more than one grid square
		// (flippers/absorbers/potentially balls)
		// can be placed over current gizmos. This method will be used to check
		// the area the
		// new flipper or absorber will take up. Returns true if safe to place
		// otherwise
		// flase.
		return false;
	}

}
