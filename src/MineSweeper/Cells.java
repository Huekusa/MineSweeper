package MineSweeper;

public class Cells {
	private int statu;
	private boolean frag;
	private int height;
	private int width;
	private boolean lock;
	
	private Cells(int height, int width) {
		this.setHeight(height);
		this.setWidth(width);
	}
	
	public static Cells Create(int height, int width) {
		Cells c = new Cells(height, width);
		return c;
	}
	
	public int getStatu() {return this.statu;}
	public void setStatu(int statu) {this.statu = statu;}

	public boolean isFrag() {return this.frag;}
	public void setFrag(boolean frag) {this.frag = frag;}

	public int getHeight() {return this.height;}
	public void setHeight(int height) {this.height = height;}
	public int getWidth() {return this.width;}
	public void setWidth(int width) {this.width = width;}
	public boolean isLock() {return this.lock;}
	public void setLock(boolean lock) {this.lock = lock;}
	
	
}
