import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
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
	private Set<String> names;
	private API api;

	public Loader(String fileName, API api) throws FileNotFoundException {
		namePat = Pattern.compile("[^0-9A-Za-z_]");
		names = new TreeSet<String>();
		names.add("OuterWalls");
		fileInput = new BufferedReader(new FileReader(fileName));
		this.api = api;
	}

	public void parse() throws BadFileException, IOException {
		String line;
		StringTokenizer st;
		String opCode;

		while ((line = fileInput.readLine()) != null) {

			st = new StringTokenizer(line);

			if (!st.hasMoreTokens()) {
				continue;
			}

			opCode = st.nextToken();

			// Gizmo
			if (isGimzoOP(opCode)) {
				String name = parseName(st);
				ensureUnquieName(name);
				int x = parseInt(st);
				int y = parseInt(st);
				api.addGizmo(opCode, name, x, y);

				// Absorber
			} else if (opCode.equals("Absorber")) {
				String name = parseName(st);
				ensureUnquieName(name);
				int x1 = parseInt(st);
				int y1 = parseInt(st);
				int x2 = parseInt(st);
				int y2 = parseInt(st);
				api.addAbsorber(name, x1, y1, x2, y2);

				// Ball
			} else if (opCode.equals("Ball")) {
				String name = parseName(st);
				ensureUnquieName(name);
				float x = parseFloat(st);
				float y = parseFloat(st);
				float vx = parseFloat(st);
				float vy = parseFloat(st);
				api.addBall(name, x, y, vx, vy);

				// Rotate
			} else if (opCode.equals("Rotate")) {
				String name = parseName(st);
				ensureNameExists(name);
				ensureRotatable(name);
				api.rotate(name);

				// Delete
			} else if (opCode.equals("Delete")) {
				String name = parseName(st);
				ensureNameExists(name);
				api.delete(name);
				names.remove(name);

				// Move
			} else if (opCode.equals("Move")) {
				String name = parseName(st);
				ensureNameExists(name);
				// TODO can be float or int ????
				// ints move upper left corner, floats move center
				float f1 = parseFloat(st);
				float f2 = parseFloat(st);
				api.move(name, f1, f2);

				// Connect
			} else if (opCode.equals("Connect")) {
				String name1 = parseName(st);
				ensureNameExists(name1);
				String name2 = parseName(st);
				ensureNameExists(name2);
				api.connect(name1, name2);

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
				api.keyConnect(key, direction, name);

				// Gravity
			} else if (opCode.equals("Gravity")) {
				// gL/sec2 downward, defaults to 25 L/sec2 if not value in file
				float f = parseFloat(st);
				api.setGravity(f);

				// Friction
			} else if (opCode.equals("Friction")) {
				float f1 = parseFloat(st);
				float f2 = parseFloat(st);
				api.setFriction(f1, f2);

				// command not found
			} else {
				throw new BadFileException("invalid opcode");
			}
		}

	}

	private boolean isGimzoOP(String opCode) {
		if (opCode.equals("Square") || opCode.equals("Circle")
				|| opCode.equals("Triangle") || opCode.equals("RightFlipper")
				|| opCode.equals("LeftFlipper")) {
			return true;
		}
		return false;
	}

	private int parseInt(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no int found");
		}
		return Integer.valueOf(st.nextToken());
	}

	private float parseFloat(StringTokenizer st) throws BadFileException {
		if (!st.hasMoreTokens()) {
			throw new BadFileException("no float found");
		}
		return Float.valueOf(st.nextToken());
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
		if (!names.contains(name)) {
			throw new BadFileException(name + " name does not exist yet");
		}
	}

	private void ensureUnquieName(String name) throws BadFileException {
		if (names.contains(name)) {
			throw new BadFileException(name + " name is not unquie");
		}
		names.add(name);
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

	private void ensureRotatable(String name) {
		// TODO cannot rotate some items (eg absorber, outer walls, balls)
	}

}