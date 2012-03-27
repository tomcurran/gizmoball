package model.physics;

import model.Board;
import model.physics.mit.LineSegment;

public class OuterWalls extends PhysicsGizmo {
	
	/**
	 * Adds outerwalls to the map. 
	 * 
	 * @param map - the map to add the walls to. 
	 */
	public OuterWalls(Board map) {
		objects.add(new LineSegment(0, 0, map.getWidth(), 0));
		objects.add(new LineSegment(map.getWidth(), 0, map.getWidth(), map.getHeight()));
		objects.add(new LineSegment(0, map.getHeight(), map.getWidth(), map.getHeight()));
		objects.add(new LineSegment(0, 0, 0, map.getHeight()));
	}
}
