package jgon.mazes.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jgon.mazes.Cell;
import jgon.mazes.Grid;

public class SidewinderMazeAlgorithm {

    private static final Random RNG = new Random();

    public static void applyTo(Grid grid) {
        IO.println("inside sidewinder algo.");
        grid.rowIterator().forEach((List<Cell> row) -> {
            List<Cell> run = new ArrayList<>();
            for (Cell cell : row) {
                run.add(cell);
                boolean isAtEasternBoundary = cell.east().equals(Cell.OUTSIDE);
                boolean isAtNorthernBoundary = cell.north().equals(Cell.OUTSIDE);
                boolean shouldCloseOut = isAtEasternBoundary || (!isAtNorthernBoundary && (RNG.nextInt(2) == 0));
                if (shouldCloseOut) {
                    Cell member = run.get(RNG.nextInt(run.size()));
                    if (!member.north().equals(Cell.OUTSIDE)) {
                        member.addLink(member.north(), true);
                    }
                    run.clear();
                } else {
                    cell.addLink(cell.east(), true);
                }
            }
        });
        grid.cellAt(grid.getRows() - 1, 0)
                .setIsStart();
        grid.cellAt(0, grid.getColumns() - 1)
                .setIsEnd();
    }
}
