package jgon.mazes.drawing;

import jgon.mazes.Cell;

import java.awt.*;

public class CellBorderRenderer implements ICellRenderer {
	@Override
	public void render(Cell cell,
	                   Graphics2D ctx,
	                   int cellWidth,
	                   int cellHeight,
	                   int xOffset,
	                   int yOffset) {
		if (cell.north()
		        .equals(Cell.OUTSIDE)
		    || (!cell.north()
		             .equals(Cell.OUTSIDE) && !cell.isLinked(cell.north()))) {
			ctx.drawLine(xOffset, yOffset, xOffset + cellWidth, yOffset);
		}
		if ((cell.west()
		         .equals(Cell.OUTSIDE) && !cell.isStart()) || (!cell.west()
		                                                            .equals(Cell.OUTSIDE) && !cell.isLinked(cell.west()))) {
			ctx.drawLine(xOffset, yOffset, xOffset, yOffset + cellWidth);
		}
		if ((cell.east()
		         .equals(Cell.OUTSIDE) && !cell.isEnd()) || (!cell.east()
		                                                          .equals(Cell.OUTSIDE) && !cell.isLinked(cell.east()))) {
			ctx.drawLine(xOffset + cellWidth, yOffset, xOffset + cellWidth, yOffset + cellWidth);
		}
		if (cell.south()
		        .equals(Cell.OUTSIDE) || (!cell.south()
		                                       .equals(Cell.OUTSIDE) && !cell.isLinked(cell.south()))) {
			ctx.drawLine(xOffset, yOffset + cellWidth, xOffset + cellWidth, yOffset + cellWidth);
		}
	}
}
