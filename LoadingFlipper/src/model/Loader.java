package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
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

	private Pattern gizCommand;
	private Pattern absCommand;
	private Pattern ballCommand;
	private Pattern rotCommand;
	private Pattern delCommand;
	private Pattern movIntCommand;
	private Pattern movFloatCommand;
	private Pattern conCommand;
	private Pattern keyconCommand;
	private Pattern gravCommand;
	private Pattern fricCommand;
	private BufferedReader fileInput;
	private Map<String, IBoardItem> boardItemMap;

	public Loader(String fileName) throws FileNotFoundException {
		String nameRegex = "([0-9A-Za-z_]+)";
		String intpairRegex = "(\\d+) (\\d+)";
		String floatRegex = "(\\d*\\.\\d+)";
		String floatpairRegex = floatRegex + " " + floatRegex;
		gizCommand = Pattern.compile("(Square|Circle|Triangle|RightFlipper|LeftFlipper) " + nameRegex + " " + intpairRegex);
		absCommand = Pattern.compile("Absorber " + nameRegex + " " + intpairRegex + " " + intpairRegex);
		ballCommand = Pattern.compile("Ball " + nameRegex + " " + floatpairRegex + " " + floatpairRegex);
		rotCommand = Pattern.compile("Rotate " + nameRegex);
		delCommand = Pattern.compile("Delete " + nameRegex);
		movIntCommand = Pattern.compile("Move " + nameRegex + " " + intpairRegex);
		movFloatCommand = Pattern.compile("Move " + nameRegex + " " + floatpairRegex);
		conCommand = Pattern.compile("Connect " + nameRegex + " " + nameRegex);
		keyconCommand = Pattern.compile("KeyConnect key (\\d+) (up|down) " + nameRegex);
		gravCommand = Pattern.compile("Gravity " + floatRegex);
		fricCommand = Pattern.compile("Friction " + floatpairRegex);
		fileInput = new BufferedReader(new FileReader(fileName));
		boardItemMap = new HashMap<String, IBoardItem>();
	}

	public void parseFile(Physics physics, TriggerSystem trigsys) throws BadFileException, IOException {
		String line, gizop, name, name2, dir;
		int x, y, x1, x2, y1, y2, key;
		double xd, yd, vx, vy, g, f1, f2;
		Matcher matcher;
		
		while ((line = fileInput.readLine()) != null) {

			if (line.isEmpty()) {
				continue;
			}
			
			matcher = gizCommand.matcher(line);
			if (matcher.matches()) {
				gizop = matcher.group(1);
				name = matcher.group(2);
				ensureUnquieName(name);
				x = Integer.parseInt(matcher.group(3));
				y = Integer.parseInt(matcher.group(4));
				if (gizop.equals("Square")) {
					boardItemMap.put(name, new SquareGizmo(x, y));
				} else if (gizop.equals("Circle")) {
					boardItemMap.put(name, new CircleGizmo(x, y));
				} else if (gizop.equals("Triangle")) {
					boardItemMap.put(name, new TriangleGizmo(x, y));
				} else if (gizop.equals("RightFlipper")) {
					boardItemMap.put(name, new RightFlipper(x, y));
				} else if (gizop.equals("LeftFlipper")) {
					boardItemMap.put(name, new LeftFlipper(x, y));
				}
				continue;
			}
			
			matcher = absCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureUnquieName(name);
				x1 = Integer.valueOf(matcher.group(2));
				y1 = Integer.valueOf(matcher.group(3));
				x2 = Integer.valueOf(matcher.group(4));
				y2 = Integer.valueOf(matcher.group(5));
				boardItemMap.put(name, new Absorber(x1, y1, x2, y2));
				continue;
			}
			
			matcher = ballCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(0);
				ensureUnquieName(name);
				xd = Double.valueOf(matcher.group(2));
				yd = Double.valueOf(matcher.group(3));
				vx = Double.valueOf(matcher.group(4));
				vy = Double.valueOf(matcher.group(5));
				boardItemMap.put(name, new Ball(xd, yd, vx, vy));
				continue;
			}
			
			matcher = rotCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureNameExists(name);
				boardItemMap.get(name).rotate();
				continue;
			}
			
			matcher = delCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureNameExists(name);
				boardItemMap.remove(name);
				continue;
			}
			
			matcher = movIntCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureNameExists(name);
				x = Integer.parseInt(matcher.group(2));
				y = Integer.parseInt(matcher.group(3));
				boardItemMap.get(name).move(x, y);
				continue;
			}
			
			matcher = movFloatCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureNameExists(name);
				xd = Double.parseDouble(matcher.group(2));
				yd = Double.parseDouble(matcher.group(3));
				boardItemMap.get(name).move(xd, yd);
				continue;
			}
			
			matcher = conCommand.matcher(line);
			if (matcher.matches()) {
				name = matcher.group(1);
				ensureNameExists(name);
				name2 = matcher.group(2);
				ensureNameExists(name2);
				trigsys.connect((IGizmo)boardItemMap.get(name), (IGizmo)boardItemMap.get(name2));
				continue;
			}
			
			matcher = keyconCommand.matcher(line);
			if (matcher.matches()) {
				key = Integer.valueOf(matcher.group(1));
				dir = matcher.group(2);
				name = matcher.group(3);
				ensureNameExists(name);
				trigsys.keyConnect(key, dir, (IGizmo)boardItemMap.get(name));
				continue;
			}
			
			matcher = gravCommand.matcher(line);
			if (matcher.matches()) {
				g = Double.valueOf(matcher.group(1));
				physics.setGravity(g);
				continue;
			}
			
			matcher = fricCommand.matcher(line);
			if (matcher.matches()) {
				f1 = Double.parseDouble(matcher.group(1));
				f2 = Double.parseDouble(matcher.group(2));
				physics.setFriction(f1, f2);
				continue;
			}
			
			throw new BadFileException("invalid command " + line);
		}
	}

	public void loadItems(Board board) {
		for (IBoardItem item : boardItemMap.values()) {
			board.addItem(item);
		}
	}

	private void ensureUnquieName(String name) throws BadFileException {
		if (boardItemMap.containsKey(name)) {
			throw new BadFileException(name + " name is not unquie");
		}
	}

	private void ensureNameExists(String name) throws BadFileException {
		// TODO put reference to OuterWalls in item map
		if (name.equals("OuterWalls") || boardItemMap.containsKey(name)) {
			return;
		}
		throw new BadFileException(name + " name does not exist yet");
	}

}