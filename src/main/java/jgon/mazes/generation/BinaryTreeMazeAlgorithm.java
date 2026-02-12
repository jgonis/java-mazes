package jgon.mazes.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jgon.mazes.Cell;
import jgon.mazes.Grid;

public class BinaryTreeMazeAlgorithm {

    private static final Random RNG = new Random();

    public static void applyTo(Grid grid) {
        for (Cell cell : grid) {
            List<Cell> neighbors = new ArrayList<>();
            Cell northNeighbor = cell.north();
            if (northNeighbor != Cell.OUTSIDE) {
                neighbors.add(northNeighbor);
            }
            Cell eastNeighbor = cell.east();
            if (eastNeighbor != Cell.OUTSIDE) {
                neighbors.add(eastNeighbor);
            }
            // The top northeast corner would have no neighbors
            // by this definition, so we need to account for that.
            if (!neighbors.isEmpty()) {
                int indexToChoose = RNG.nextInt(neighbors.size());
                Cell neighborToLink = neighbors.get(indexToChoose);
                cell.addLink(neighborToLink, true);
            }
        }
        grid.cellAt(grid.getRows() - 1, 0)
                .setIsStart();
        grid.cellAt(0, grid.getColumns() - 1)
                .setIsEnd();
    }
}
