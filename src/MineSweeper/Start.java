package MineSweeper;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Start {
	private static final int CELL_SIZE = 30;
	private JFrame frame; 
	private JPanel field;
	private JLabel[][] cells;
	
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
		
		
		this.cells = new JLabel[16][16];
		for (int i = 0;i < 16;i++) {
			for (int j = 0;j < 16;j++) {
				field.add(this.cells[i][j] = new JLabel());
				this.cells[i][j].setOpaque(true);
				this.cells[i][j].setVisible(true);
//				this.cells[i][j].addMouseListener(null);
//				Button[i][j].addMouseListener();
				
			}
		}
		
		frame.add(field);
		frame.setVisible(true);
		MineField m = MineField.Create(16, 16);
		cellsUpdateView(m.getCells());
	}
	
	private void cellsUpdateView(Cell[][] c) {
		for(int i = 0;i < 16;i++) {
			for (int j = 0; j < 16; j++) {
				Style s = c[i][j].getStyle();
				cells[i][j].setText(s.getText());
				cells[i][j].setBorder(s.getBorder());
				cells[i][j].setForeground(s.getForegroundColor());
				cells[i][j].setBackground(s.getBackgroundColor());
				System.out.println(s.getText());
			}
		}
		System.out.println("view updated");
	}

}
