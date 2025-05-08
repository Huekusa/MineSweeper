package MineSweeper;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Start {
	private static final int CELL_SIZE = 30;
	private JFrame frame; 
	private JPanel field;
	private Cell[][] cells;
	
	public static void main(String[] args) {
		new Start();
	}
	
	private Start() {
		frame = new JFrame("MineSweeper");
		initializeFrame();
		
	}
	
	private void initializeFrame() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(500, 520);
		
		field = new JPanel();
		field.setVisible(true);
        field.setLayout(new GridLayout(16, 16));
        field.setSize(CELL_SIZE*16, CELL_SIZE*16);
		
        MineField m = MineField.Create(16, 16);
		
        this.cells = m.getCells();
		for (int i = 0;i < 16;i++) {
			for (int j = 0;j < 16;j++) {
				field.add(this.cells[i][j]);
				this.cells[i][j].addMouseListener(new cellListener(this.cells[i][j]));
				this.cells[i][j].setOpaque(true);
				this.cells[i][j].setVisible(true);
			}
		}
		
		frame.add(field);
		frame.setVisible(true);
		updateViewAll(m.getCells());
	}
	
	private void updateViewAll(Cell[][] c) {
		for(int i = 0;i < 16;i++) {
			for (int j = 0; j < 16; j++) {
				updateViewOf(c[i][j]);
//				Style s = c[i][j].getStyle();
//				cells[i][j].setText(s.getText());
//				cells[i][j].setBorder(s.getBorder());
//				cells[i][j].setForeground(s.getForegroundColor());
//				cells[i][j].setBackground(s.getBackgroundColor());
			}
		}
		System.out.println("viewAll updated");
	}
	
	private void updateViewOf(Cell c) {
		Style s = c.getStyle();
		cells[c.getCell_Y()][c.getCell_X()].setText(s.getText());
		cells[c.getCell_Y()][c.getCell_X()].setBorder(s.getBorder());
		cells[c.getCell_Y()][c.getCell_X()].setForeground(s.getForegroundColor());
		cells[c.getCell_Y()][c.getCell_X()].setBackground(s.getBackgroundColor());
	}

	public class cellListener implements MouseListener{
		Cell cell;
		public cellListener(Cell cell) {
			this.cell = cell;
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getButton() == 1) {
				if(this.cell.isClose() && !this.cell.isFlag()) {
					this.cell.setClose(false);
					updateViewOf(this.cell);
					System.out.println(this.cell.getCell_Y() +" "+this.cell.getCell_X());					
				}
				
			}else if(e.getButton() == 3) {
				if (this.cell.isClose()) {					
					this.cell.toggleFlag();
					updateViewOf(cell);
					System.out.println("ToggleFlag");
				}
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(this.cell.isClose() && !this.cell.isFlag())
			cell.setBackground(new Color(220,220,220));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(cell.isClose())
			cell.setBackground(Color.GRAY);
		}
		
		@Override
		public void mousePressed(MouseEvent e) {;}
		@Override
		public void mouseReleased(MouseEvent e) {;}
	}
}

