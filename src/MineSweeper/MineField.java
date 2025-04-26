package MineSweeper;

public class MineField {
	public static final int MINE = 9;
	
	private int mines;
	private int openCount;
	private int height;
	private int width;
	private Cells[][] cell;
	
	private MineField(int height, int width) {
		
		Cells.Create(height, width);
		genCells(height, width);
		genMine(mines);
	}
	
	public static MineField Create(int height, int width) {
		MineField m = new MineField(height,width);
		return m;
	}
	
	public Cells[][] getCell() {return this.cell;}

	private void genMine(int m) {
		int count = 0;
		while(count < m) {
			var target = cell
					[new java.util.Random().nextInt(cell.length) - 1]
					[new java.util.Random().nextInt(cell[0].length) - 1];
			if(!(target.getStatu() == MINE)) {
				target.setStatu(MINE);
				System.out.println(target);
				count++;
			}
		}
		System.out.println("cmp gen mine");
	}
	
	private void genCells(int height, int width) {
		cell = new Cells[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width; j++) {
				cell[i][j] = Cells.Create(i, j);
			}
		}
		System.out.println("cmp gen cells");
	}
}
