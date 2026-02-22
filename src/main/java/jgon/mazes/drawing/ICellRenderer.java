package jgon.mazes.drawing;

import java.awt.Graphics2D;

import jgon.mazes.Cell;

public interface ICellRenderer {
	public void render(Cell cell,
	                   Graphics2D ctx,
	                   int cellWidth,
	                   int cellHeight,
	                   int xOffset,
	                   int yOffset);
}
