package jgon.mazes;

import java.util.*;

public class Cell {
	public static final Cell OUTSIDE = new Cell(-1, -1);

	private final int rowCount;
	private final int columnCount;
	private final Set<Cell> links = new HashSet<>();
	private final Map<Direction, Cell> neighbors = new HashMap<>();
	private boolean isStart = false;
	private boolean isEnd = false;


	private Cell(int rowCount,
	             int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
	}

	public static Cell newCellWithRowAndColumn(int row,
	                                           int column) {
		return new Cell(row, column);
	}

	public int row() {
		return this.rowCount;
	}

	public int column() {
		return this.columnCount;
	}

	public boolean isStart() {
		return isStart;
	}

	public boolean isEnd() {
		return isEnd;
	}

	public void setIsEnd() {
		if (isStart) {
			throw new IllegalStateException("A cell cannot be both an end and a start.");
		}
		isEnd = true;
	}

	public void setIsStart() {
		if (isStart) {
			throw new IllegalStateException("A cell cannot be both a start and an end.");
		}
		isStart = true;
	}

	public void addLink(Cell otherCell,
	                    boolean bidirectional) {
		this.links.add(otherCell);
		if (bidirectional) {
			otherCell.addLink(this, false);
		}
	}

	public void removeLink(Cell otherCell,
	                       boolean bidirectional) {
		this.links.remove(otherCell);
		if (bidirectional) {
			otherCell.removeLink(this, false);
		}
	}

	public List<Cell> allLinks() {
		return this.links.stream()
		                 .toList();
	}

	public boolean isLinked(Cell otherCell) {
		return this.links.contains(otherCell);
	}

	public Cell north() {
		return this.neighbors.get(Direction.NORTH);
	}

	public void setNorth(Cell northernNeighbor) {
		this.neighbors.put(Direction.NORTH, northernNeighbor);
	}

	public Cell south() {
		return this.neighbors.get(Direction.SOUTH);
	}

	public void setSouth(Cell southernNeighbor) {
		this.neighbors.put(Direction.SOUTH, southernNeighbor);
	}

	public Cell east() {
		return this.neighbors.get(Direction.EAST);
	}

	public void setEast(Cell easternNeighbor) {
		this.neighbors.put(Direction.EAST, easternNeighbor);
	}

	public Cell west() {
		return this.neighbors.get(Direction.WEST);
	}

	public void setWest(Cell westernNeighbor) {
		this.neighbors.put(Direction.WEST, westernNeighbor);
	}

	public List<Cell> neighbors() {
		return this.neighbors.values()
		                     .stream()
		                     .filter(Objects::nonNull)
		                     .filter(cell -> !cell.equals(OUTSIDE))
		                     .toList();
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Cell cell)) {
			return false;
		}
		return rowCount == cell.rowCount && columnCount == cell.columnCount;
	}

	@Override
	public int hashCode() {
		return Objects.hash(rowCount, columnCount);
	}
}
