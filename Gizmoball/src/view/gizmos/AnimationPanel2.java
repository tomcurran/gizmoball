package view.gizmos;

import java.awt.AWTEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Ball;
import model.Board;
import model.GizmoType;
import model.gizmos.IGizmo;
import controller.DesignModeViewModel;
import controller.GizmoballViewModel;
import controller.GizmoballViewModel.UpdateReason;

public class AnimationPanel2 extends JPanel implements Observer
{
	private GizmoballViewModel viewmodel;
	private DesignModeViewModel designmodeViewmodel;
	private Map<GizmoType, IGizmoPainter> painters;
	private BallPainter ballpainter;
	private boolean mousecontained;
	
	public AnimationPanel2(GizmoballViewModel viewmodel, DesignModeViewModel designmodeViewmodel)
	{
		this.viewmodel = viewmodel;
		viewmodel.addObserver(this);
		
		this.designmodeViewmodel = designmodeViewmodel;
		designmodeViewmodel.addObserver(this);
		
		this.setBackground(Color.black);
		this.setMinimumSize(new Dimension(500, 500));
		this.enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
		
		painters = new HashMap<GizmoType, IGizmoPainter>();
		painters.put(GizmoType.SquareBumper, new SquareBumperPainter());
		painters.put(GizmoType.CircleBumper, new CircleBumperPainter());
		painters.put(GizmoType.TriangleBumper, new TriangleBumperPainter());
		painters.put(GizmoType.Flipper, new FlipperPainter());
		painters.put(GizmoType.Absorber, new AbsorberPainter());
		
		ballpainter = new BallPainter();
	}
	
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Board board = viewmodel.getBoard();
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		AffineTransform transform = AffineTransform.getScaleInstance(getXScale(), getYScale());
		g2d.transform(transform);
		
		//set the stroke width equivalent to 1 pixel at normal scaling
		g2d.setStroke(new BasicStroke(0.05f));
		
		//draw the grid if in design mode
		if (viewmodel.getIsRunning() == false)
		{
			g2d.setColor(Color.GRAY);
			
			for (int i = 1; i < board.getHeight(); i++)
			{
				g.drawLine(0, i, board.getWidth(), i);
			}
			
			for (int i = 1; i < board.getWidth(); i++)
			{
				g.drawLine(i, 0, i, board.getHeight());
			}
		}
		
		//draw all the gizmos
		for (IGizmo gizmo: board.getGizmos())
		{
			IGizmoPainter painter = painters.get(gizmo.getType());
			
			if (painter != null)
				painter.paint(g2d, gizmo);
		}
		
		//draw all the balls
		for (Ball ball: board.getBalls())
		{
			ballpainter.paint(g2d, ball);
		}
		
		//draw the validation rectangle if active
		Rectangle validationRectangle = designmodeViewmodel.getPositionBox();
		
		if (mousecontained && validationRectangle != null)
		{
			if (designmodeViewmodel.getPositionValid())
				g2d.setColor(Color.GREEN);
			else
				g2d.setColor(Color.RED);
			
			g2d.draw(validationRectangle);
		}
	}

	@Override
	public void update(Observable source, Object arg)
	{
		UpdateReason reason = (UpdateReason)arg;
		
		switch (reason)
		{
			case RunStateChanged:
			case BoardChanged:
				this.repaint();
				break;
		}
	}

	@Override
	protected void processMouseEvent(MouseEvent e)
	{
		handleMouseEvent(e);
		super.processMouseEvent(e);
	}
	
	@Override
	protected void processMouseMotionEvent(MouseEvent e)
	{
		handleMouseEvent(e);
		super.processMouseMotionEvent(e);
	}
	
	
	private void handleMouseEvent(MouseEvent e)
	{
		int x = (int)(e.getX() / getXScale());
		int y = (int)(e.getY() / getYScale());
		
		switch (e.getID())
		{
			case MouseEvent.MOUSE_MOVED:
			case MouseEvent.MOUSE_DRAGGED:
				designmodeViewmodel.moveTo(x, y);
				break;
				
			case MouseEvent.MOUSE_PRESSED:
				designmodeViewmodel.beginSelectAt(x, y);
				break;
				
			case MouseEvent.MOUSE_RELEASED:
				designmodeViewmodel.endSelectAt(x, y);
				break;
				
			case MouseEvent.MOUSE_EXITED:
				mousecontained = false;
				repaint();
				break;
				
			case MouseEvent.MOUSE_ENTERED:
				mousecontained = true;
				break;
		}
	}
	
	
	private double getXScale()
	{
		return (double)this.getWidth() / viewmodel.getBoard().getWidth();
	}
	
	private double getYScale()
	{
		return (double)this.getHeight() / viewmodel.getBoard().getHeight();
	}
}
