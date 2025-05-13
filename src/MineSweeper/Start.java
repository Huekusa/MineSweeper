//スタート時処理、Swing

package MineSweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

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
		//Swingの基本設定・フィールドの生成・セルのボタン設定等
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(new BorderLayout());
		frame.setSize(500, 550);
		
		field = new JPanel();
		field.setVisible(true);
        field.setLayout(new GridLayout(16, 16));
        field.setSize(CELL_SIZE*16, CELL_SIZE*16);
        
		//フィールドの生成
        m = MineField.Create(16, 16);
		
        //フィールドで生成したセルの取得とボタン化
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
		//全てのセルの見た目をアップデートする
		for(int i = 0;i < 16;i++) {
			for (int j = 0; j < 16; j++) {
				updateViewOf(c[i][j]);
			}
		}
		System.out.println("viewAll updated");
	}
	
	private void updateViewOf(Cell c) {
		//セルのスタッツを参照してスタイルを取得しスタイル設定
		Style s = c.getStyle();
		cells[c.getCell_Y()][c.getCell_X()].setText(s.getText());
		cells[c.getCell_Y()][c.getCell_X()].setBorder(s.getBorder());
		cells[c.getCell_Y()][c.getCell_X()].setForeground(s.getForegroundColor());
		cells[c.getCell_Y()][c.getCell_X()].setBackground(s.getBackgroundColor());
	}
	
	public void updateOpenCount() {
		//オープンしたマスの表示設定・クリアの判定
		m.calcOpenCount();
		counter.setText("残りマス数："+ (16 * 16 - m.getOpenCount() - 40));
		if (16 * 16 - m.getOpenCount() - 40 == 0) {
			this.gameover = true;
			showGameoverDialog("CLEAR!!");
		}
	}
	
	public void showGameoverDialog(String text) {
		//ゲーム終了時のダイアログ設定
		JDialog dialog = new JDialog(frame, "");
		JLabel label_dialog = new JLabel(text);
		JButton button_dialog = new JButton("終了");

		dialog.setResizable(false);
		dialog.setSize(300, 200);
		dialog.setLocationRelativeTo(field);
		dialog.setUndecorated(true);
		JPanel p = new JPanel();
		p.setBorder(new LineBorder(Color.GRAY,2,true));
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		dialog.add(p);
		
		label_dialog.setAlignmentX(Component.CENTER_ALIGNMENT);
		label_dialog.setFont(new Font("Arial", Font.PLAIN, 30));
		button_dialog.setAlignmentX(Component.CENTER_ALIGNMENT);
		button_dialog.setBackground(Color.LIGHT_GRAY);
		p.add(Box.createRigidArea(new Dimension(0,70)));
		p.add(label_dialog);
		p.add(button_dialog);
		button_dialog.addActionListener(action -> frame.dispose());
		dialog.setVisible(true);
	}

	public class cellListener implements MouseListener{
		//セルのクリック処理用クラス
		
		Cell cell;
		public cellListener(Cell cell) {
			this.cell = cell;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(!gameover) {
				if(e.getButton() == 1) {
					//左クリック(セルオープン)処理・ゲームオーバーの判定
					//
					if(this.cell.isClose() && !this.cell.isFlag()) {
						this.cell.setClose(false);
						System.out.println(this.cell.getCell_Y() +" "+this.cell.getCell_X());					
						this.openNullCells(cell);
						updateViewOf(this.cell);
						if(this.cell.getStatu() == MINE) {
							gameover = true;
							showGameoverDialog("GAME OVER!!");
						}else {
							updateOpenCount();							
						}
					}
					
				}else if(e.getButton() == 3) {
					//右クリック(フラッグ)処理
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
			//カーソルがセルに乗ったら背景を白くする
			if(this.cell.isClose() && !this.cell.isFlag() && !gameover)
			cell.setBackground(new Color(220,220,220));
		}

		@Override
		public void mouseExited(MouseEvent e) {
			//カーソルがセルから離れたら背景色を戻す
			if(cell.isClose())
			cell.setBackground(Color.GRAY);
		}
		
		public void openNullCells(Cell cell) {
			//スタッツ0セルオープン時に周囲のセルをオープンする
			//クローズ&フラッグなしならオープンし、
			//そのマスがスタッツ0なら自身をコールして周囲をオープン
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

