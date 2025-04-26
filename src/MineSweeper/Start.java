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
		MineField.Create(16, 16);
	}
	
	private Start() {
		frame = new JFrame("MineSweeper");
		initializeFrame();
		
		
	}
	
	private void initializeFrame() {
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setSize(500, 500);
		
		field = new JPanel();
		field.setVisible(true);
        field.setLayout(new GridLayout(16, 16));
        field.setSize(CELL_SIZE*16, CELL_SIZE*16);
		
		
		cells = new JLabel[16][16];
		for (int i = 0;i < 16;i++) {
			for (int j = 0;j < 16;j++) {
				var target = cells[i][j];
				field.add(target = new JLabel());
				target.setOpaque(true);
				target.setVisible(true);
//				target.addMouseListener(this);
//				Button[i][j].addMouseListener();
				
			}
		}
		
		frame.add(field);
		frame.setVisible(true);
	}
	

}
