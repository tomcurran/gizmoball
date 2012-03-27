package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.gizmos.IGizmo;
import model.gizmos.LeftFlipper;
import model.gizmos.RightFlipper;
import controller.TriggerHandler;

public class Saver
{
	private final static String GIZMO_FORMAT         = "%s %s %d %d\n";
	private final static String ABSORBER_FORMAT      = "Absorber %s %d %d %d %d\n";
	private final static String BALL_FORMAT          = "Ball %s %f %f %f %f\n";
	private final static String ROTATE_FORMAT        = "Rotate %s\n";
	private final static String CONNECT_FORMAT       = "Connect %s %s\n";
	private final static String KEY_CONNECT_FORMAT   = "KeyConnect key %d %s %s\n";
	private final static String GRAVITY_FORMAT       = "Gravity %f\n";
	private final static String FRICTION_FORMAT      = "Friction %f %f\n";

	private PrintWriter fileOutput;
	private Map<IBoardItem, String> names;
	private int count;

	public Saver(String fileName) throws IOException
	{
		fileOutput = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
		names = new HashMap<IBoardItem, String>();
		count = 0;
	}

	public void save(IPhysicsEngine engine, Board board, TriggerHandler triggerhandler)
	{
		savePhysics(engine);
		saveBoard(board);
		saveConnections(board, triggerhandler);
		fileOutput.close();
	}

	private void saveConnections(Board board, TriggerHandler triggerhandler)
	{
		Map<Integer, List<IBoardItem>> downLinks = triggerhandler.getLinksDown();
		Map<Integer, List<IBoardItem>> upLinks = triggerhandler.getLinksUp();
		saveKeyConnections("down", downLinks);
		saveKeyConnections("up", upLinks);
		saveBoardConnections(board);
	}

	private void saveKeyConnections(String direction, Map<Integer, List<IBoardItem>> downLinks)
	{
		int key;
		for (Map.Entry<Integer, List<IBoardItem>> entry : downLinks.entrySet())
		{
			key = entry.getKey();
			for (IBoardItem item : entry.getValue())
			{
				fileOutput.printf(KEY_CONNECT_FORMAT, key, direction, getName(item));
			}
		}
	}

	private void saveBoardConnections(Board board)
	{
		for (IGizmo gizmo : board.getGizmos())
		{
			for (IBoardItem item : gizmo.getConnectedItems())
			{
				fileOutput.format(CONNECT_FORMAT, getName(gizmo), getName(item));
			}
		}
	}

	private void saveBoard(Board board)
	{
		saveGizmos(board);
		saveBalls(board);
	}

	private void saveGizmos(Board board)
	{
		int x, y;
		for (IGizmo gizmo : board.getGizmos())
		{
			switch (gizmo.getType())
			{
			case CircleBumper:
				saveGizmo(gizmo, "Circle");
				break;

			case SquareBumper:
				saveGizmo(gizmo, "Square");
				break;

			case TriangleBumper:
				saveGizmo(gizmo, "Triangle");
				break;

			case Flipper:
				Class<? extends IGizmo> cls = gizmo.getClass();
				if (cls.equals(LeftFlipper.class))
				{
					saveGizmo(gizmo, "LeftFlipper");
				}
				else if (cls.equals(RightFlipper.class))
				{
					saveGizmo(gizmo, "RightFlipper");
				}
				break;

			case Absorber:
				x = gizmo.getX();
				y = gizmo.getY();
				fileOutput.format(ABSORBER_FORMAT, giveName(gizmo, "Absorber"), x, y, x + gizmo.getWidth(), y + gizmo.getHeight());
				break;

			default:
				
			}
		}
	}

	private void saveGizmo(IGizmo gizmo, String type)
	{
		String name = giveName(gizmo, type);
		fileOutput.format(GIZMO_FORMAT, type, name, gizmo.getX(), gizmo.getY());
		int orientation = gizmo.getOrientation();
		while (orientation-- > 0)
		{
			fileOutput.format(ROTATE_FORMAT, name);
		}
	}

	private void saveBalls(Board board)
	{
		List<Ball> balls = board.getBalls();
		for (Ball ball : balls)
		{
			fileOutput.format(BALL_FORMAT, giveName(ball), ball.getX(), ball.getY(), ball.getXVelocity(), ball.getYVelocity());
		}
	}

	private void savePhysics(IPhysicsEngine engine)
	{
		fileOutput.printf(GRAVITY_FORMAT, engine.getGravity());
		fileOutput.printf(FRICTION_FORMAT, engine.getFriction1(), engine.getFriction2());
	}

	private String getName(IBoardItem gizmo) {
		return names.get(gizmo);
	}

	private String giveName(IGizmo gizmo, String type)
	{
		String name = type + count++;
		names.put(gizmo, name);
		return name;
	}

	private String giveName(Ball ball)
	{
		String name = "ball" + count++;
		names.put(ball, name);
		return name;
	}

}