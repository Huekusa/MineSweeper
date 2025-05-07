package MineSweeper;

public class MineField {
	public static final int MINE = 9;
	
	private int mines;
	private int openCount;
	private int height;
	private int width;
	private Cell[][] cells;
	
	private MineField(int height, int width) {
		mines = 40;
		this.height = height;
		this.width = width;
		Cell.Create(this.height, this.width);
		genCells(this.height, this.width);
		genMine(mines);
		cheakMine();
	}
	
	public static MineField Create(int height, int width) {
		MineField m = new MineField(height,width);
		return m;
	}
	
	public Cell[][] getCells() {return this.cells;}

	private void genMine(int m) {
		int count = 0;
		while(count < m) {
			var target = this.cells
					[new java.util.Random().nextInt(this.height)]
					[new java.util.Random().nextInt(this.width)];
			if(!(target.getStatu() == MINE)) {
				target.setStatu(MINE);
				System.out.println(target.getHeight() +" " +target.getWidth());
				count++;
			}
		}
		System.out.println("cmp gen mine");
	}
	
	private void genCells(int height, int width) {
		this.cells = new Cell[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width; j++) {
				this.cells[i][j] = Cell.Create(i, j);
			}
		}
		System.out.println("cmp gen cells");
	}
	
	private void calcCells() {
//		cells[][] 
	}
	
	private void cheakMine() {
		for(int i = 0;i < this.height;i++) {
			System.out.println();
			for (int j = 0; j < this.width; j++) {
				System.out.print(cells[i][j].getStatu() + " ");
				
			}
		}
	}
}
