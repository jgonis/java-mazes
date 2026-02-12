package jgon.mazes.drawing;

import java.awt.image.BufferedImage;

class LetterPage implements Page {

    private static final double WIDTH_IN_INCHES = 8.5;
    private static final double HEIGHT_IN_INCHES = 11;
    private int pageWidthInPixels;
    private int pageHeightInPixels;

    public LetterPage(PageOrientation pageOrientation, int ppi) {
        switch (pageOrientation) {
            case PORTRAIT -> {
                this.pageHeightInPixels = (int) (LetterPage.HEIGHT_IN_INCHES * ppi);
                this.pageWidthInPixels = (int) (LetterPage.WIDTH_IN_INCHES * ppi);
            }
            case LANDSCAPE -> {
                this.pageHeightInPixels = (int) (LetterPage.WIDTH_IN_INCHES * ppi);
                this.pageWidthInPixels = (int) (LetterPage.HEIGHT_IN_INCHES * ppi);
            }
        }
    }

    @Override
    public BufferedImage getPageImage() {
        return new BufferedImage(this.pageWidthInPixels, this.pageHeightInPixels, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public int getPageWidthInPixels() {
        return this.pageWidthInPixels;
    }

    @Override
    public int getPageHeightInPixels() {
        return this.pageHeightInPixels;
    }
}
