package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/* SAVE FILE GRAMMAR

 <file> ::= <commandline>*

 <commandline> ::= <command>"\n" | "\n"

 <command> ::= <gizmoOp> <name> <int-pair> |
 Absorber <name> <int-pair> <int-pair> |
 Ball <name> <float-pair> <float-pair> |
 Rotate <name> |
 Delete <name> |
 Move <name> <number-pair> |
 Connect <name> <name> |
 KeyConnect <keyid> <name> |
 Gravity FLOAT |
 Friction FLOAT FLOAT

 <name> ::= IDENTIFIER

 <gizmoOp> ::= Square | Circle | Triangle | RightFlipper | LeftFlipper

 <number-pair> ::= <int-pair> | <float-pair>

 <int-pair> ::= INTEGER INTEGER

 <float-pair> ::= FLOAT FLOAT

 <keyid> ::= "key" KEYNUM "down" |
 "key" KEYNUM "up"

 IDENTIFIER    represents any string composed only from the characters {'0'..'9','A'..'Z','a..z','_'}. The identifier "OuterWalls" is a special reserved word which refers to the outer walls; no other item may use this identifier.    

 INTEGER        represents any integer number    
 FLOAT          represents any floating point number    
 KEYNUM         represents any numeric key identifier (which are integers)

 */
public class Loader {

	private Pattern namePat;
	private BufferedReader fileInput;
	private Map<String, IBoardItem> boardItemMap;

	public Loader(String fileName) throws FileNotFoundException {
		namePat = Pattern.compile("[^0-9A-Za-z_]");
		fileInput = new BufferedReader(new FileReader(fileName));
		boardItemMap = new HashMap<String, IBoardItem>();
	}

	public void parseFile(Physics physics, TriggerSystem trigsys)
			throws BadFileException, IOException {
		String line;
		StringTokenizer st;
		String opCode;
		int gizCode;

		while ((line = fileInput.readLine()) != null) {

			st = new StringTokenizer(line);

			if (!st.hasMoreTokens()) {
				continue;
			}

			opCode = st.nextToken();

			// Gizmo
			if ((gizCode = getGizmoCode(opCode)) != -1) {
				String name = parseName(st);
				ensureUnquieName(name);
				int x = parseInt(st);
				int y = parseInt(st);
				switch (gizCode) {
				case 0:
					boardItemMap.put(name, new SquareGizmo(x, y));
					break;
				case 1:
					boardItemMap.put(name, new CircleGizmo(x, y));
					break;
				case 2:
					boardItemMap.put(name, new TriangleGizmo(x, y));
					break;
				case 3:
					boardItemMap.put(name, new RightFlipper(x, y));
					break;
				case 4:
					boardItemMap.put(name, new LeftFlipper(x, y));
					break;
				default:
					throw new BadFileException("invalid gizmo " + opCode);
				}

				// Absorber
			} else if (opCode.equals("Absorber")) {
				String name = parseName(st);
				ensureUnquieName(name);
				int x1 = parseInt(st);
				int y1 = parseInt(st);
				int x2 = parseInt(st);
				int y2 = parseInt(st);
				boardItemMap.put(name, new Absorber(x1, y1, x2, y2));

				// Ball
			} else if (opCode.equals("Ball")) {
				String name = parseName(st);
				ensureUnquieName(name);
				double x = parseDouble(st);
				double y = parseDouble(st);
				double vx = parseDouble(st);
				double vy = parseDouble(st);
				boardItemMap.put(name, new Ball(x, y, vx, vy));

				// Rotate
			} else if (opCode.equals("Rotate")) {
				String name = parseName(st);
				ensureNameExists(name);
				boardItemMap.get(name).rotate();

				// Delete
			} else if (opCode.equals("Delete")) {
				String name = parseName(st);
				ensureNameExists(name);
				boardItemMap.remove(name);
				System.out.printf("Delete: %s\n", name);

				// Move
			} else if (opCode.equals("Move")) {
				String name = parseName(st);
				ensureNameExists(name);
				// TODO can be float or int ????
				// ints move upper left corner, floats move center
				int i1 = parseInt(st);
				int i2 = parseInt(st);
				boardItemMap.get(name).move(i1, i2);
				System.out.printf("Move: %s x=%d,y=%d\n", name, i1, i2);

				// Connect
			} else if (opCode.equals("Connect")) {
				String name1 = parseName(st);
				ensureNameExists(name1);
				String name2 = parseName(st);
				ensureNameExists(name2);
				trigsys.connect(boardItemMap.get(name1),
						boardItemMap.get(name2));

				// KeyConnect
			} else if (opCode.equals("KeyConnect")) {
				if (!st.hasMoreTokens()) {
					throw new BadFileException("'key' not found");
				}
				st.nextToken();
				int key = parseInt(st);
				ensureKey(key);
				String direction = parseDirection(st);
				String name = parseName(st);
				ensureNameExists(name);
				trigsys.keyConnect(key, direction, boardItemMap.get(name));

				// Gravity
			} else if (opCode.equals("Gravity")) {
				// gL/sec2 downward, defaults to 25 L/sec2 if not value in file
				double f = parseDouble(st);
				physics.setGravity(f);

				// Friction
			} else if (opCode.equals("Friction")) {
				double f1 = parseDouble(st);
				double f2 = parseDouble(st);
				physics.setFriction(f1, f2);

				// command not found
			} else {
				throw new BadFileException("invalid opcode");
			}
		}

	}

	public void loadItems(Board board) {
		for (IBoardItem item : boardItemMap.values()) {
			board.addItem(item);
		}
	}

	private int getGizmoCode(String opCode) {
		if (opCode.equals("Square")) {
			return 0;
		} else if (opCode.equals("Circle")) {
			return 1;
		} else if (opCode.equals("Triangle")) {
			return 2;
		} else if (opCode.equals("RightFlipper")) {
			return 3;
		} else if (opCode.equals("LeftFlipper")) {
			return 4;
		} else {
			return -1;
		}
	}

	private int parseInt(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no int found");
		}
		String sint = st.nextToken();
		try {
			return Integer.valueOf(sint);
		} catch (NumberFormatException e) {
			throw new BadFileException("could not parse int from " + sint);
		}
	}

	private double parseDouble(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no float found");
		}
		String sdoub = st.nextToken();
		try {
			return Double.valueOf(sdoub);
		} catch (NumberFormatException e) {
			throw new BadFileException("could not parse double from " + sdoub);		}
	}

	private String parseName(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no name found");
		}
		String name = st.nextToken();
		if (namePat.matcher(name).find()) {
			throw new BadFileException(name
					+ " name in wrong format (premitted [0-9A-Za-z_])");
		}
		return name;
	}

	private void ensureNameExists(String name) throws BadFileException {
		// TODO put reference to OuterWalls in item map
		if (name.equals("OuterWalls") || boardItemMap.containsKey(name)) {
			return;
		}
		throw new BadFileException(name + " name does not exist yet");
	}

	private void ensureUnquieName(String name) throws BadFileException {
		if (boardItemMap.containsKey(name)) {
			throw new BadFileException(name + " name is not unquie");
		}
	}

	private String parseDirection(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no direction found");
		}
		String dir = st.nextToken();
		if (!dir.equals("up") && !dir.equals("down")) {
			throw new BadFileException("invalid direction " + dir);
		}
		return dir;
	}

	private void ensureKey(int key) {
		// TODO numeric key identifier
	}

}