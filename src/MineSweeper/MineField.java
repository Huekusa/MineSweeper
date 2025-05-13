//フィールドの設定、セル同士の計算

package MineSweeper;

public class MineField {
	public static final int MINE = 9;
	
	private int mines;
	private int openCount;
	private int height;
	private int width;
	private Cell[][] cells;
	
	private MineField(int height, int width) {
		//フィールドの初期値設定、MINEの生成とセルの計算
		mines = 40;
		this.height = height;
		this.width = width;
		Cell.Create(this.height, this.width);
		genCells(this.height, this.width);
		genMine(mines);
		calcCells();
		cheakMine();
	}
	
	public static MineField Create(int height, int width) {
		MineField m = new MineField(height,width);
		return m;
	}
	
	public int getOpenCount() {return this.openCount;}
	
	public Cell[][] getCells() {return this.cells;}

	private void genMine(int m) {
		//MINEの生成
		//乱数でセルを指定、MINEがない場合生成し、規定数になるまでループ
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
		//セルの生成
		this.cells = new Cell[height][width];
		for (int i = 0;i < height;i++) {
			for (int j = 0;j < width; j++) {
				this.cells[i][j] = Cell.Create(i, j);
			}
		}
		System.out.println("cmp gen cells");
	}
	
	private void calcCells() {
		//セルスタッツの計算
		//総当たりでMINEを検索し、周囲のセルを+1
		for(int i = 0;i < height;i++) {
			for (int j = 0;j < width;j++) {
				if(this.cells[i][j].getStatu() == MINE) {
					calcAroundCells(this.cells[i][j]);
				}
			}
		}
	}
	
	private void cheakMine() {
		//[テスト用]セルの配置をコンソール出力
		for(int i = 0;i < this.height;i++) {
			System.out.println();
			for (int j = 0; j < this.width; j++) {
				System.out.print(cells[i][j].getStatu() + " ");
				
			}
		}
	}
	
	private void calcAroundCells(Cell cell) {
		//周囲のセル+1
		for(int i = -1;i < 2;i++) {
			for(int j = -1;j < 2;j++) {
				try {
					if(this.cells[cell.getCell_Y()+i][cell.getCell_X()+j].getStatu() < 8) {						
						this.cells[cell.getCell_Y()+i][cell.getCell_X()+j].setStatu(this.cells[cell.getCell_Y()+i][cell.getCell_X()+j].getStatu() + 1);				
					}
				} catch (Exception e) {;}
			}
		}
	}
	
	public void calcOpenCount() {
		//オープンしたセルの数の計算
		//総当たりでセルを検索し、オープンの数を数える
		int count = 0;
		for(int i = 0;i < this.height;i++) {
			for(int j = 0;j < this.width;j++) {
				if(!cells[i][j].isClose()) {
					count++;
				}
			}
		}
		this.openCount = count;
		System.out.println("count "+this.openCount);
		
	}
}
