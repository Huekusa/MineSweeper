package MineSweeper;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Start {
	private static final int CELL_SIZE = 30;
	private JFrame frame; 
	private int[][] cells;
	
	public static void main(String[] args) {
		new Start();
		MineField.Create(16, 16);
	}
	
	private Start() {
		frame = new JFrame("MineSweeper");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800, 500);
		frame.setLayout(new GridLayout(16,16));
		for (int i = 0;i < 256;i++) {
			frame.add(new JButton(""+i));
		}
		
		
		frame.setVisible(true);
	}
}
