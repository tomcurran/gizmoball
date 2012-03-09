package view.window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import view.icons.*;

import controller.Controller;

public class ButtonArea {

	private JPanel buttonArea;
	private Controller listener;

	private JToggleButton cb;
	private JToggleButton sb;
	private JToggleButton tb;
	private JToggleButton bb;
	private JToggleButton ab;
	private JButton mode;
	private JToggleButton lk;
	private JToggleButton lg;
	private JToggleButton dg;
	private JToggleButton rg;
	private JToggleButton frg;
	private JToggleButton flg;
	
	private boolean canEdit;

	public ButtonArea(Controller controller) {
		canEdit = true;
		this.listener = controller;
		buttonArea = new JPanel();
		buttonArea.setPreferredSize(new Dimension(100, 400));

		Icon cbbs = new CBIcon(Color.green);
		cb = new JToggleButton(cbbs);
		cb.setPreferredSize(new Dimension(30, 30));
		cb.setActionCommand("Circle");

		Icon sbbs = new SBIcon(Color.red);
		sb = new JToggleButton(sbbs);
		sb.setPreferredSize(new Dimension(30, 30));
		sb.setActionCommand("Square");

		Icon tbbs = new TBIcon(Color.blue);
		tb = new JToggleButton(tbbs);
		tb.setPreferredSize(new Dimension(30, 30));
		tb.setActionCommand("Triangle");

		Icon bbbs = new BIcon(Color.blue);
		bb = new JToggleButton(bbbs);
		bb.setPreferredSize(new Dimension(30, 30));
		bb.setActionCommand("Ball");

		Icon abbs = new AIcon(Color.magenta);
		ab = new JToggleButton(abbs);
		ab.setPreferredSize(new Dimension(66, 30));
		ab.setActionCommand("Absorber");

		mode = new JButton("Play");
		mode.setPreferredSize(new Dimension(68, 30));
		mode.setActionCommand("Mode");

		Icon lkbs = new LKIcon(Color.gray);
		lk = new JToggleButton(lkbs);
		lk.setPreferredSize(new Dimension(68, 30));
		lk.setActionCommand("KeyLink");

		Icon glbs = new LGIcon(Color.gray);
		lg = new JToggleButton(glbs);
		lg.setPreferredSize(new Dimension(68, 30));
		lg.setActionCommand("GizmoLink");

		Icon dbbs = new DGIcon(Color.gray);
		dg = new JToggleButton(dbbs);
		dg.setPreferredSize(new Dimension(30, 30));
		dg.setActionCommand("Delete");

		Icon rgbs = new RGIcon(Color.gray);
		rg = new JToggleButton(rgbs);
		rg.setPreferredSize(new Dimension(30, 30));
		rg.setActionCommand("Rotate");

		Icon frbs = new FRIcon(Color.gray);
		frg = new JToggleButton(frbs);
		frg.setPreferredSize(new Dimension(30, 30));
		frg.setActionCommand("RightFlipper");

		Icon flbs = new FLIcon(Color.gray);
		flg = new JToggleButton(flbs);

		flg.setPreferredSize(new Dimension(30, 30));
		flg.setActionCommand("LeftFlipper");
		

		buttonArea.add(cb);
		buttonArea.add(sb);
		buttonArea.add(tb);
		buttonArea.add(bb);
		buttonArea.add(frg);
		buttonArea.add(flg);
		buttonArea.add(ab);
		buttonArea.add(lk);
		buttonArea.add(lg);
		buttonArea.add(dg);
		buttonArea.add(rg);
		buttonArea.add(mode);

	}

	public JPanel getButtonArea() {
		return buttonArea;
	}

	public void addListeners(ActionListener listener) {
		System.out.println("added listner");
		cb.addActionListener(listener);
		sb.addActionListener(listener);
		tb.addActionListener(listener);
		bb.addActionListener(listener);
		ab.addActionListener(listener);
		mode.addActionListener(listener);
		lk.addActionListener(listener);
		lg.addActionListener(listener);
		dg.addActionListener(listener);
		rg.addActionListener(listener);
		frg.addActionListener(listener);
		flg.addActionListener(listener);
	}

	public void activateEditButtons() {
		canEdit = !canEdit;
		cb.setEnabled(canEdit);
		sb.setEnabled(canEdit);
		tb.setEnabled(canEdit);
		bb.setEnabled(canEdit);
		ab.setEnabled(canEdit);
		lk.setEnabled(canEdit);
		lg.setEnabled(canEdit);
		dg.setEnabled(canEdit);
		rg.setEnabled(canEdit);
		frg.setEnabled(canEdit);
		flg.setEnabled(canEdit);
	}

}
