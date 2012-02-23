package physicsprototype;

public interface IPhysicsEngine
{
	void initialise(GizmoMap map);
	void calculateState(double timeDelta);
}
