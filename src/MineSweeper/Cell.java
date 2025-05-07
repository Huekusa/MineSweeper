package MineSweeper;

public class Cell {
	private int statu;
	private Style style;
	private boolean frag;
	private int height;
	private int width;
	private boolean close;
	
	private Cell(int height, int width) {
		this.setClose(true);
		this.setFrag(false);
		this.setStatu(0);
		this.setHeight(height);
		this.setWidth(width);
	}
	
	public static Cell Create(int height, int width) {
		Cell c = new Cell(height, width);
		return c;
	}
	
	public int getStatu() {return this.statu;}
	public void setStatu(int statu) {this.statu = statu;}

	public Style getStyle() {return Style.getStyleOf(this);}

	public boolean isFrag() {return this.frag;}
	public void setFrag(boolean frag) {this.frag = frag;}

	public int getHeight() {return this.height;}
	public void setHeight(int height) {this.height = height;}
	public int getWidth() {return this.width;}
	public void setWidth(int width) {this.width = width;}
	public boolean isClose() {return this.close;}
	public void setClose(boolean lock) {this.close = lock;}
	
	
}
