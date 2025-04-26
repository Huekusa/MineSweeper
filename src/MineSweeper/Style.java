package MineSweeper;

import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Style {
	private String text;
	private Border border;
	private Color backgroundColor;
	private Color foregroundColor;
	
	private Style(String text, Boolean lock, Color color) {
		this.text = text;
		if (lock) {
			this.border = new BevelBorder(BevelBorder.RAISED);
			this.backgroundColor = Color.DARK_GRAY;
		}else {
			this.border = new BevelBorder(BevelBorder.LOWERED);
			this.backgroundColor = Color.GRAY;
			
		}
	}
	
	public Style getStyleOf(Cells cell) {
		boolean lock = cell.isLock();
		switch(cell.getStatu()) {
			case 0: return new Style(" ", lock, Color.BLACK);
			case 1: return new Style("1", lock, Color.BLUE);
			case 9: return new Style("B", lock, Color.RED);
		}
	}
}
