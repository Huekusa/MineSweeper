package MineSweeper;

import javax.swing.JLabel;

public class Cell extends JLabel {
	private int statu;
	private Style style;
	private boolean flag = false;
	private int cell_Y;
	private int cell_X;
	private boolean close;
	
	private Cell(int cell_Y, int cell_X) {
		this.setClose(true);
		this.setStatu(0);
		this.setCell_Y(cell_Y);
		this.setCell_X(cell_X);
		this.setHorizontalAlignment(CENTER);
	}
	
	public static Cell Create(int height, int width) {
		Cell c = new Cell(height, width);
		return c;
	}
	
	public int getStatu() {return this.statu;}
	public void setStatu(int statu) {this.statu = statu;}

	public Style getStyle() {return Style.getStyleOf(this);}

	public boolean isFlag() {return this.flag;}
	public void toggleFlag() {this.flag = !this.flag;}

	public int getCell_Y() {return this.cell_Y;}
	public void setCell_Y(int cell_Y) {this.cell_Y = cell_Y;}
	public int getCell_X() {return this.cell_X;}
	public void setCell_X(int cell_X) {this.cell_X = cell_X;}
	
	
	public boolean isClose() {return this.close;}
	public void setClose(boolean close) {
		this.close = close;
		this.setText(Style.getStyleOf(this).getText());
		this.setBorder(Style.getStyleOf(this).getBorder());
		this.setForeground(Style.getStyleOf(this).getForegroundColor());
		this.setBackground(Style.getStyleOf(this).getBackgroundColor());
		
		
	}
	
	
}
