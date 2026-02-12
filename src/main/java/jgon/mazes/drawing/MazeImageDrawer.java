package jgon.mazes.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import jgon.mazes.Cell;
import jgon.mazes.Grid;

public class MazeImageDrawer {

    private static final int DEFAULT_PAGE_INSET = 96;

    private File outputFile;

    public MazeImageDrawer(String fileName) {
        this.outputFile = new File(fileName);
    }

    public void drawMaze(Grid grid) throws IOException {
        PageOrientation pageOrientation = calculatePageOrientation(grid);
        Page page = new LetterPage(pageOrientation, 300);
        BufferedImage image = page.getPageImage();
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, page.getPageWidthInPixels(), page.getPageHeightInPixels());
        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.BLACK);

        int cellSize = calculateGridCellSize(grid, page);
        int initialXOffset = calculateXOffset(grid, cellSize, page);
        int yOffset = calculateYOffset(grid, cellSize, page);

        for (List<Cell> row : grid.rowIterator()) {
            int xOffset = initialXOffset;
            for (Cell cell : row) {
                drawCell(cell, g2d, cellSize, xOffset, yOffset);
                xOffset += cellSize;
            }
            yOffset += cellSize;
        }
        ImageIO.write(image, "png", this.outputFile);
    }

    private void drawCell(Cell cell, Graphics2D g2d, int cellSize, int xOffset, int yOffset) {
        if (cell.north().equals(Cell.OUTSIDE)
                || (!cell.north().equals(Cell.OUTSIDE) && !cell.isLinked(cell.north()))) {
            g2d.drawLine(xOffset, yOffset, xOffset + cellSize, yOffset);
        }
        if ((cell.west().equals(Cell.OUTSIDE) && !cell.isStart()) || (!cell.west().equals(Cell.OUTSIDE) && !cell.isLinked(cell.west()))) {
            g2d.drawLine(xOffset, yOffset, xOffset, yOffset + cellSize);
        }
        if ((cell.east().equals(Cell.OUTSIDE) && !cell.isEnd()) || (!cell.east().equals(Cell.OUTSIDE) && !cell.isLinked(cell.east()))) {
            g2d.drawLine(xOffset + cellSize, yOffset, xOffset + cellSize, yOffset + cellSize);
        }
        if (cell.south().equals(Cell.OUTSIDE) || (!cell.south().equals(Cell.OUTSIDE) && !cell.isLinked(cell.south()))) {
            g2d.drawLine(xOffset, yOffset + cellSize, xOffset + cellSize, yOffset + cellSize);
        }
    }

    private PageOrientation calculatePageOrientation(Grid grid) {
        if (grid.getRows() >= grid.getColumns()) {
            return PageOrientation.PORTRAIT;
        } else {
            return PageOrientation.LANDSCAPE;
        }
    }

    private int calculateGridCellSize(Grid grid, Page page) {
        int cellWidth = (page.getPageWidthInPixels() - MazeImageDrawer.DEFAULT_PAGE_INSET) / grid.getColumns();
        int cellHeight = (page.getPageHeightInPixels() - MazeImageDrawer.DEFAULT_PAGE_INSET) / grid.getRows();
        return Math.min(cellWidth, cellHeight);
    }

    private int calculateXOffset(Grid grid, int cellSize, Page page) {
        int pageWidth = page.getPageWidthInPixels();
        int mazeWidth = grid.getColumns() * cellSize;
        return (pageWidth - mazeWidth) / 2;
    }

    private int calculateYOffset(Grid grid, int cellSize, Page page) {
        int pageHeight = page.getPageHeightInPixels();
        int mazeHeight = grid.getRows() * cellSize;
        return (pageHeight - mazeHeight) / 2;
    }
}
