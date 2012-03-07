package model;

import gizmos.Absorber;
import gizmos.Ball;

import gizmos.CircleGizmo;
import gizmos.IBoardItem;
import gizmos.LeftFlipper;
import gizmos.RightFlipper;
import gizmos.SquareGizmo;
import gizmos.TriangleGizmo;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import listeners.EditListener;


@SuppressWarnings("serial")
public class BoardView extends Canvas implements ActionListener, Observer {

	private static final int L = 20;

	private Board model;

	private int numRows;
	private int numCols;
	private int width;
	private int hieght;
	private EditListener listner;
	
	private JPanel panel;
	private Canvas can;
	
	private boolean mode;
	
	public BoardView(int numRows, int numCols, int width, int hieght, EditListener listener, Board model){
		super();
		this.numRows = numRows;
		this.numCols = numCols;
		this.width = width;
		this.hieght = hieght;
		this.setBackground(Color.black);
		this.setSize(width, hieght);
		this.setMaximumSize(new Dimension(width, hieght));
		this.listner = listener;
		this.addMouseListener(this.listner);
		mode = true;
		this.model = model;
		
		
	}

	public void paint(Graphics g) {
		if(mode){
			for(int i = 0; i < numRows; i++){
				g.drawLine(0, i*(width/20), this.getWidth() ,i*(width/20));
			}
			
			for(int i = 0; i < numCols; i++){
				g.drawLine(i*(hieght/20), 0,i*(hieght/20), this.getHeight());
			}
		}
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		Color red = new Color(255, 0, 0);
		Color green = new Color(0, 255, 0);
		Color blue = new Color(0, 0, 255);
		Color orange = new Color(255, 204, 0);
		Color pink = new Color(255, 0, 255);

		Class<? extends BoardItem> cls;

		int x, y, w, h, halfL, orientation, flipW;

		for (BoardItem boardItem : model.getItems()) {

			cls = boardItem.getClass();
			x = L * (int) boardItem.getX();
			y = L * (int) boardItem.getY();
			w = L * boardItem.getWidth();
			h = L * boardItem.getHeight();
			halfL = L / 2;

			if (cls.equals(SquareGizmo.class)) {
				g.setColor(red);
				g.fillRect(x, y, w, h);
			} else if (cls.equals(CircleGizmo.class)) {
				g.setColor(green);
				g.fillOval(x, y, w, h);
			} else if (cls.equals(TriangleGizmo.class)) {
				g.setColor(blue);
				int xTrianglePoints[][] = {
						{ x,   x+w, x,   x   },
						{ x,   x+w, x+w, x   },
						{ x+w, x+w, x,   x+w },
						{ x,   x+w, x,   x   }
				};
				int yTrianglePoints[][] = {
						{ y, y,   y+h, y },
						{ y, y,   y+h, y },
						{ y, y+h, y+h, y },
						{ y, y+h,  +h, y }
				};
				orientation = boardItem.getOrientation();
				g.fillPolygon(xTrianglePoints[orientation],
						yTrianglePoints[orientation], 4);
			} else if (cls.equals(RightFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;
				g.fillRoundRect(x + (flipW * 3), y, flipW, h, halfL, halfL);
			} else if (cls.equals(LeftFlipper.class)) {
				g.setColor(orange);
				flipW = w / 4;
				g.fillRoundRect(x, y, flipW, h, halfL, halfL);
			} else if (cls.equals(Absorber.class)) {
				g.setColor(pink);
				g.fillRect(x, y, w, h);
			} else if (cls.equals(Ball.class)) {
				g.setColor(blue);
				g.fillOval(x, y, halfL, halfL);
			}

		}
	}

	
	public void update(Observable o, Object arg) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}