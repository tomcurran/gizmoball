package view.board;

import model.gizmos.IGizmo;

@SuppressWarnings("serial")
public abstract class GizmoView extends ScalableComponent {

	private IGizmo model;

	public GizmoView(IGizmo model) {
		this.model = model;
	}

	public IGizmo getGizmo() {
		return model;
	}

}