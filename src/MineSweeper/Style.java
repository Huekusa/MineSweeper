package MineSweeper;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Style {
	private String text;
	private Border border;
	private Color backgroundColor;
	private Color foregroundColor;
	
	private Style(String text, Boolean close, Color color) {
		this.text = text;
		if (close) {
			this.border = new BevelBorder(BevelBorder.RAISED);
			this.backgroundColor = Color.GRAY;
		}else {
			this.border = new BevelBorder(BevelBorder.LOWERED);
			this.backgroundColor = Color.LIGHT_GRAY;
		}
		this.foregroundColor = color;
	}
	
	public String getText() {return this.text;}
	public Border getBorder() {return this.border;}
	public Color getBackgroundColor() {return this.backgroundColor;}
	public Color getForegroundColor() {return this.foregroundColor;}

	public static Style getStyleOf(Cell cell) {
		boolean close = cell.isClose();
		switch(cell.getStatu()) {
			case 0: return new Style(" ", close, Color.BLACK);
			case 1: return new Style("1", close, Color.BLUE);
			case 2: return new Style("2", close, Color.GREEN);
			case 3: return new Style("3", close, Color.RED);
			case 4: return new Style("4", close, Color.CYAN);
			case 5: return new Style("5", close, Color.MAGENTA);
			case 6: return new Style("6", close, Color.YELLOW);
			case 7: return new Style("7", close, Color.ORANGE);
			case 8: return new Style("8", close, Color.PINK);
			case 9: return new Style("B", close, Color.RED);
			default:throw new IllegalArgumentException();
		}
	}
}
