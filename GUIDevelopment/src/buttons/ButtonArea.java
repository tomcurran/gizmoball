package buttons;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import buttons.CBIcon;
import buttons.SBIcon;
import buttons.TBIcon;



import listeners.EditListener;

public class ButtonArea {
	
	private JPanel buttonArea;
	private EditListener listener;
	
	public ButtonArea(EditListener listener){
		this.listener = listener;
		buttonArea = new JPanel();
		buttonArea.setPreferredSize(new Dimension(100, 400));
		
		Icon cbbs = new CBIcon(Color.green);
		JButton cb = new JButton(cbbs);
		cb.setPreferredSize(new Dimension(30, 30));
		cb.addActionListener(this.listener);
		cb.setActionCommand("Circle");
		
		Icon sbbs = new SBIcon(Color.red);
		JButton sb = new JButton(sbbs);
		sb.setPreferredSize(new Dimension(30, 30));
		sb.addActionListener(this.listener);
		sb.setActionCommand("Square");
		
		Icon tbbs = new TBIcon(Color.blue);
		JButton tb = new JButton(tbbs);
		tb.setPreferredSize(new Dimension(30, 30));
		tb.addActionListener(this.listener);
		tb.setActionCommand("Triangle");
		
		
		Icon bbbs = new BIcon(Color.blue);
		JButton bb = new JButton(bbbs);
		bb.setPreferredSize(new Dimension(30, 30));
		bb.addActionListener(this.listener);
		bb.setActionCommand("Ball");
		
		Icon abbs = new AIcon(Color.magenta);
		JButton ab = new JButton(abbs);
		ab.setPreferredSize(new Dimension(66, 30));
		ab.addActionListener(this.listener);
		ab.setActionCommand("Absorber");
		
		
		JButton mode = new JButton("Play");
		mode.setPreferredSize(new Dimension(68, 30));
		mode.addActionListener(this.listener);
		mode.setActionCommand("Mode");
		
		Icon lkbs = new LKIcon(Color.gray);
		JButton lk = new JButton(lkbs);
		lk.setPreferredSize(new Dimension(68, 30));
		lk.addActionListener(this.listener);
		lk.setActionCommand("KeyLink");
		
		Icon glbs = new LGIcon(Color.gray);
		JButton lg = new JButton(glbs);
		lg.setPreferredSize(new Dimension(68, 30));
		lg.addActionListener(this.listener);
		lg.setActionCommand("GizmoLink");
		
		Icon dbbs = new DGIcon(Color.gray);
		JButton dg = new JButton(dbbs);
		dg.setPreferredSize(new Dimension(30, 30));
		dg.addActionListener(this.listener);
		dg.setActionCommand("Delete");
		
		Icon rgbs = new RGIcon(Color.gray);
		JButton rg = new JButton(rgbs);
		rg.setPreferredSize(new Dimension(30, 30));
		rg.addActionListener(this.listener);
		rg.setActionCommand("Rotate");

		Icon frbs = new FRIcon(Color.gray);
		JButton frg = new JButton(frbs);
		frg.setPreferredSize(new Dimension(30, 30));
		frg.addActionListener(this.listener);
		frg.setActionCommand("RightFlipper");
		
		Icon flbs = new FLIcon(Color.gray);
		JButton flg = new JButton(flbs);
		flg.setPreferredSize(new Dimension(30, 30));
		flg.addActionListener(this.listener);
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
	
	
	public JPanel getButtonArea(){
		return buttonArea;
	}
	
	
}
