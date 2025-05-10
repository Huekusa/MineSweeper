package MineSweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Start {
	public static final int MINE = 9;
	private static final int CELL_SIZE = 30;
	public static boolean gameover = false;
	private JFrame frame; 
	private JPanel field;
	private Cell[][] cells;
	private MineField m;
	private JLabel counter;
	
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
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 550);
		
		field = new JPanel();
		field.setVisible(true);
        field.setLayout(new GridLayout(16, 16));
        field.setSize(CELL_SIZE*16, CELL_SIZE*16);
        
		
        m = MineField.Create(16, 16);
		
        this.cells = m.getCells();
		for (int i = 0;i < 16;i++) {
			for (int j = 0;j < 16;j++) {
				field.add(this.cells[i][j]);
				this.cells[i][j].addMouseListener(new cellListener(this.cells[i][j]));
				this.cells[i][j].setOpaque(true);
				this.cells[i][j].setVisible(true);
			}
		}
		
		frame.add(field, BorderLayout.CENTER);
		frame.setVisible(true);
		
		counter = new JLabel();
		counter.setVisible(true);
		this.updateOpenCount();
		counter.setBackground(Color.BLACK);
		frame.add(counter, BorderLayout.SOUTH);
		
		updateViewAll(m.getCells());
	}
	
	public MineField getMinefield() {return this.m;}
	
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
	
	public void updateOpenCount() {
		m.calcOpenCount();
		counter.setText("残りマス数："+ (16 * 16 - m.getOpenCount() - 40));
		if (16 * 16 - m.getOpenCount() - 40 == 0) {
			this.gameover = true;
			System.out.println("-----CLEAR-----");
		}
	}

	public class cellListener implements MouseListener{
		Cell cell;
		public cellListener(Cell cell) {
			this.cell = cell;
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!gameover) {
				if(e.getButton() == 1) {
					if(this.cell.isClose() && !this.cell.isFlag()) {
						this.cell.setClose(false);
						System.out.println(this.cell.getCell_Y() +" "+this.cell.getCell_X());					
						this.openNullCells(cell);
						updateViewOf(this.cell);
						if(this.cell.getStatu() == MINE) {
							gameover = true;
							System.out.println("GAME OVER!!");
						}else {
							updateOpenCount();							
						}
					}
					
				}else if(e.getButton() == 3) {
					if (this.cell.isClose()) {					
						this.cell.toggleFlag();
						updateViewOf(cell);
						System.out.println("ToggleFlag");
					}
				}		
			}
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(this.cell.isClose() && !this.cell.isFlag() && !gameover)
			cell.setBackground(new Color(220,220,220));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(cell.isClose())
			cell.setBackground(Color.GRAY);
		}
		
		public void openNullCells(Cell cell) {
			if(cell.getStatu() == 0) {
				for(int i = -1;i < 2;i++) {
					for(int j = -1;j < 2;j++) {
							if(i == 0 && j == 0) {continue;}
							try {
								if(getMinefield().getCells()[cell.getCell_Y()+i][cell.getCell_X()+j].isClose() && !m.getCells()[cell.getCell_Y()+i][cell.getCell_X()+j].isFlag()) {
									getMinefield().getCells()[cell.getCell_Y()+i][cell.getCell_X()+j].setClose(false);
									System.out.println("open "+(cell.getCell_Y()+i)+" "+(cell.getCell_X()+j));
									if(getMinefield().getCells()[cell.getCell_Y()+i][cell.getCell_X()+j].getStatu() == 0) {
										openNullCells(getMinefield().getCells()[cell.getCell_Y()+i][cell.getCell_X()+j]);
									}
								}
							} catch (Exception e) {;}								
						}
				}
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {;}
		@Override
		public void mouseReleased(MouseEvent e) {;}
	}
}

