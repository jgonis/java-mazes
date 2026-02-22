package jgon.mazes.drawing;

import java.awt.Color;
import java.awt.Graphics2D;

import jgon.mazes.Cell;
import jgon.mazes.solving.Distances;

public class CellDistanceRenderer implements ICellRenderer {

    private final float startingBrightness = 0.351f;
    private final float hue = 118;
    private final float saturation = 0.69f;

    private final Distances distances;

    public CellDistanceRenderer(Distances distances) {
        this.distances = distances;
    }

    @Override
    public void render(Cell cell,
            Graphics2D ctx,
            int cellWidth,
            int cellHeight,
            int xOffset,
            int yOffset) {
        int cellDistance = distances.cellDistance(cell);
        float newBrightness = Math.min(1.0f, startingBrightness + (cellDistance * 0.05f));
        Color cellColor = Color.getHSBColor(hue, saturation, newBrightness);
        ctx.setColor(cellColor);
        ctx.fillRect(xOffset, yOffset, cellWidth, cellHeight);
    }
}
