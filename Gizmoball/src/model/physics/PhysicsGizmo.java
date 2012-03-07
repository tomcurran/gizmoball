package model.physics;

import java.util.ArrayList;
import java.util.List;

public class PhysicsGizmo
{
	protected List<IPhysicsObject> objects;
	
	public PhysicsGizmo()
	{
		objects = new ArrayList<IPhysicsObject>();
	}
	
	public List<IPhysicsObject> getPhysicsObjects()
	{
		return objects;
	}
}
