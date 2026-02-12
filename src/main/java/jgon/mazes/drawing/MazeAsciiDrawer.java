package jgon.mazes.drawing;

import java.util.List;

import jgon.mazes.Cell;
import jgon.mazes.Grid;

public class MazeAsciiDrawer {

    public void drawMaze(Grid grid) {
        for (List<Cell> row : grid.rowIterator()) {
            for (Cell cell : row) {
                drawCellNorthSide(cell);
            }
            System.out.println("+");
            Cell currentCell = null;
            for (Cell cell : row) {
                currentCell = cell;
                drawCellWestSide(cell);
            }
            drawMazeEastWall(currentCell);
        }
        drawMazeSouthWall(grid);
    }

    private static void drawMazeSouthWall(Grid grid) {
        for (int i = 0; i < grid.getColumns(); i++) {
            System.out.print("+---");
        }
        System.out.println("+");
    }

    private static void drawMazeEastWall(Cell currentCell) {
        if (!currentCell.isEnd()) {
            System.out.println("|");
        } else {
            System.out.println(" ");
        }
    }

    private static void drawCellWestSide(Cell cell) {
        if ((cell.west()
                .equals(Cell.OUTSIDE) && !cell.isStart())
                || (!cell.west()
                        .equals(Cell.OUTSIDE) && !cell.isLinked(cell.west()))) {
            System.out.print("|   ");
        } else {
            System.out.print("    ");
        }
    }

    private static void drawCellNorthSide(Cell cell) {
        if (cell.north()
                .equals(Cell.OUTSIDE) || !cell.isLinked(cell.north())) {
            System.out.print("+---");
        } else {
            System.out.print("+   ");
        }
    }
}
