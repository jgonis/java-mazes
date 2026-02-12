package jgon.mazes;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Grid implements Iterable<Cell> {

    private final int rows;

    public int getColumns() {
        return columns;
    }

    public int getRows() {
        return rows;
    }

    private final int columns;
    private Cell[][] grid;
    private static final Random RNG = new Random();

    public Grid(int width,
            int height) {
        this.rows = height;
        this.columns = width;
        this.grid = new Cell[height][width];

        this.prepareGrid();
        this.configureCells();
    }

    protected void configureCells() {
        for (Cell cell : this) {
            int row = cell.row();
            int column = cell.column();
            cell.setNorth(this.cellAt(row - 1, column));
            cell.setSouth(this.cellAt(row + 1, column));
            cell.setWest(this.cellAt(row, column - 1));
            cell.setEast(this.cellAt(row, column + 1));
        }
    }

    protected void prepareGrid() {
        for (int row = 0; row < this.rows; row++) {
            for (int column = 0; column < this.columns; column++) {
                this.grid[row][column] = Cell.newCellWithRowAndColumn(row, column);
            }
        }
    }

    Cell randomCell() {
        int row = RNG.nextInt(this.rows);
        int column = RNG.nextInt(this.columns);
        return this.cellAt(row, column);
    }

    public Cell cellAt(int row,
            int column) {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            return Cell.OUTSIDE;
        }
        return this.grid[row][column];
    }

    public int size() {
        return this.rows * this.columns;
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CustomCellIterator();
    }

    public Iterable<List<Cell>> rowIterator() {
        return new CustomRowIterator();
    }

    private class CustomRowIterator implements Iterator<List<Cell>>, Iterable<List<Cell>> {

        private int currentRow = 0;

        @Override
        public boolean hasNext() {
            return this.currentRow < Grid.this.rows;
        }

        @Override
        public List<Cell> next() {
            List<Cell> rowList = List.of(Grid.this.grid[this.currentRow]);
            this.currentRow += 1;
            return rowList;
        }

        @Override
        public Iterator<List<Cell>> iterator() {
            return this;
        }
    }

    private class CustomCellIterator implements Iterator<Cell> {

        private int currentRow = 0;
        private int currentColumn = 0;

        @Override
        public boolean hasNext() {
            return this.currentRow < Grid.this.rows && this.currentColumn < Grid.this.columns;
        }

        @Override
        public Cell next() {
            Cell cell = Grid.this.grid[this.currentRow][this.currentColumn];
            this.currentColumn++;
            if (this.currentColumn >= Grid.this.columns) {
                this.currentColumn = 0;
                this.currentRow++;
            }
            return cell;
        }
    }
}
