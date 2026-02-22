package jgon.mazes.solving;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

import jgon.mazes.Cell;
import jgon.mazes.Grid;

class MazeDistanceTest {

    private Grid testMaze;

    @org.junit.jupiter.api.BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        testMaze = new Grid(5, 5);
        Cell currentCell = testMaze.cellAt(0, 0);
        currentCell.addLink(testMaze.cellAt(0, 1), true);
        currentCell = testMaze.cellAt(0, 1);
        currentCell.addLink(testMaze.cellAt(0, 2), true);
        currentCell.addLink(testMaze.cellAt(1, 1), true);
        currentCell = testMaze.cellAt(0, 2);
        currentCell.addLink(testMaze.cellAt(0, 3), true);
        currentCell = testMaze.cellAt(0, 3);
        currentCell.addLink(testMaze.cellAt(0, 4), true);
        currentCell = testMaze.cellAt(0, 4);
        currentCell.addLink(testMaze.cellAt(1, 4), true);
        currentCell = testMaze.cellAt(1, 0);
        currentCell.addLink(testMaze.cellAt(2, 0), true);
        currentCell.addLink(testMaze.cellAt(1, 1), true);
        currentCell = testMaze.cellAt(1, 1);
        currentCell.addLink(testMaze.cellAt(2, 1), true);
        currentCell.addLink(testMaze.cellAt(1, 2), true);
        currentCell = testMaze.cellAt(1, 3);
        currentCell.addLink(testMaze.cellAt(2, 3), true);
        currentCell = testMaze.cellAt(1, 4);
        currentCell.addLink(testMaze.cellAt(2, 4), true);
        currentCell = testMaze.cellAt(2, 1);
        currentCell.addLink(testMaze.cellAt(3, 1), true);
        currentCell = testMaze.cellAt(2, 2);
        currentCell.addLink(testMaze.cellAt(3, 2), true);
        currentCell = testMaze.cellAt(2, 3);
        currentCell.addLink(testMaze.cellAt(2, 4), true);
        currentCell = testMaze.cellAt(3, 0);
        currentCell.addLink(testMaze.cellAt(4, 0), true);
        currentCell.addLink(testMaze.cellAt(3, 1), true);
        currentCell = testMaze.cellAt(3, 1);
        currentCell.addLink(testMaze.cellAt(3, 2), true);
        currentCell = testMaze.cellAt(3, 2);
        currentCell.addLink(testMaze.cellAt(3, 3), true);
        currentCell = testMaze.cellAt(3, 3);
        currentCell.addLink(testMaze.cellAt(4, 3), true);
        currentCell.addLink(testMaze.cellAt(3, 4), true);
        currentCell = testMaze.cellAt(3, 4);
        currentCell.addLink(testMaze.cellAt(4, 4), true);
        currentCell = testMaze.cellAt(4, 0);
        currentCell.addLink(testMaze.cellAt(4, 1), true);
        currentCell = testMaze.cellAt(4, 2);
        currentCell.addLink(testMaze.cellAt(4, 3), true);
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() throws IOException {

    }

    @org.junit.jupiter.api.Test
    void testMazeDistances() throws IOException {
        Cell startCell = testMaze.cellAt(0, 0);
        Distances distances = startCell.getDistances();
        assertEquals(0, distances.cellDistance(startCell));
        assertEquals(8, distances.cellDistance(testMaze.cellAt(4, 4)));
    }
}
