package jgon.mazes.solving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jgon.mazes.Cell;

public class Distances {

    private final Cell root;
    private final Map<Cell, Integer> cellDistances = new HashMap<>();

    public Distances(Cell root) {
        this.root = root;
        this.cellDistances.put(this.root, 0);
    }

    public boolean contains(Cell cell) {
        return this.cellDistances.containsKey(cell);
    }

    public int cellDistance(Cell cell) {
        return this.cellDistances.get(cell);
    }

    public void setCellDistance(Cell cell, int distance) {
        this.cellDistances.put(cell, distance);
    }

    public List<Cell> getCells() {
        return new ArrayList<>(this.cellDistances.keySet());
    }
}
