package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TriggerSystem {

	private Map<Integer, Set<IGizmo>> upKeyMap;
	private Map<Integer, Set<IGizmo>> downKeyMap;
	private Map<IGizmo, Set<IGizmo>> gizmoMap;

	public TriggerSystem() {
		upKeyMap = new HashMap<Integer, Set<IGizmo>>();
		downKeyMap = new HashMap<Integer, Set<IGizmo>>();
		gizmoMap = new HashMap<IGizmo, Set<IGizmo>>();
	}

	public void connect(IGizmo gizmo1, IGizmo gizmo2) {
		Set<IGizmo> gizmos = gizmoMap.containsKey(gizmo1) ? gizmoMap.get(gizmo1) : new HashSet<IGizmo>();
		gizmos.add(gizmo2);
		gizmoMap.put(gizmo1, gizmos);
	}

	public void keyConnect(int key, String direction, IGizmo gizmo) {
		Set<IGizmo> gizmos;
		if (direction.equals("up")) {
			gizmos = upKeyMap.containsKey(key) ? upKeyMap.get(key) : new HashSet<IGizmo>();
			gizmos.add(gizmo);
			upKeyMap.put(key, gizmos);
		} else if (direction.equals("down")) {
			gizmos = downKeyMap.containsKey(key) ? downKeyMap.get(key) : new HashSet<IGizmo>();
			gizmos.add(gizmo);
			downKeyMap.put(key, gizmos);
		}
	}

	public void triggerKeyUp(int keyCode) {
		if (upKeyMap.containsKey(keyCode)) {
			for (IGizmo gizmo : upKeyMap.get(keyCode)) {
				gizmo.action();
			}
		}
	}

	public void triggerKeyDown(int keyCode) {
		if (downKeyMap.containsKey(keyCode)) {
			for (IGizmo gizmo : downKeyMap.get(keyCode)) {
				gizmo.action();
			}
		}
	}

	public void triggerGizmo(IGizmo gizmo) {
		if (gizmoMap.containsKey(gizmo)) {
			for (IGizmo g : gizmoMap.get(gizmo)) {
				g.action();
			}
		}
	}

}