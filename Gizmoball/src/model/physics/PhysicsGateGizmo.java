package model.physics;

import java.util.Observable;
import java.util.Observer;

import model.gizmos.GateGizmo;
import model.physics.mit.Circle;

public class PhysicsGateGizmo extends PhysicsGizmo implements Observer
{
	private GateGizmo gate;
	
	public PhysicsGateGizmo(GateGizmo gate)
	{
		this.gate = gate;
		update(null, null);
	}

	@Override
	public void update(Observable source, Object arg)
	{
		if (gate.getTriggeredState())
			objects.add(new Circle(0.5 + gate.getX(), 0.5 + gate.getY(), 0.5));
	}
}