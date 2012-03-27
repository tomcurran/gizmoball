package model.gizmos;

import model.Ball;
import model.Board;
import model.GizmoType;

public class MultiballGizmo extends CircleBumper
{
	private Board board;
	
	public MultiballGizmo(int x, int y, Board board)
	{
		super(x, y);
		this.board = board;
	}
	
	
	@Override
	public GizmoType getType()
	{
		return GizmoType.MultiballGizmo;
	}
	
	
	@Override
	public void doAction()
	{
		board.addBall(new Ball(x + 0.5, y + 1.5, 0.25, 1));
	}
}
