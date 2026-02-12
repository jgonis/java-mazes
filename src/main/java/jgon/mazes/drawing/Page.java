package jgon.mazes.drawing;

import java.awt.image.BufferedImage;

interface Page {

    BufferedImage getPageImage();

    int getPageHeightInPixels();

    int getPageWidthInPixels();
}
