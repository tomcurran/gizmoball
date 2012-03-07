package mainwindow;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import listeners.EditListener;



public class EditGrid extends Canvas{ 
	
	
	private int numRows;
	private int numCols;
	private int width;
	private int hieght;
	private EditListener listner;
	
	private JPanel panel;
	private Canvas can;
	
	private boolean mode;
	
	public EditGrid(int numRows, int numCols, int width, int hieght, EditListener listener){
		super();
		this.numRows = numRows;
		this.numCols = numCols;
		this.width = width;
		this.hieght = hieght;
		this.setBackground(Color.black);
		this.setSize(width, hieght);
		this.setMaximumSize(new Dimension(width, hieght));
		this.listner = listener;
		this.addMouseListener(this.listner);
		mode = true;
		
		
	}
	
	public void paint(Graphics g){
		if(mode){
			for(int i = 0; i < numRows; i++){
				g.drawLine(0, i*(width/20), this.getWidth() ,i*(width/20));
			}
			
			for(int i = 0; i < numCols; i++){
				g.drawLine(i*(hieght/20), 0,i*(hieght/20), this.getHeight());
			}
		}
	}
	
	public void changeMode(){
		mode = !mode;
		repaint();
	}

	

}
