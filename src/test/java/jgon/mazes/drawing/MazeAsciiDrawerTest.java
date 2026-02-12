package jgon.mazes.drawing;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;

import jgon.mazes.BinaryTreeMazeAlgorithm;
import jgon.mazes.Grid;

class MazeAsciiDrawerTest {

    private Grid testMaze;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream myOut = new ByteArrayOutputStream();

    @org.junit.jupiter.api.BeforeEach
    @SuppressWarnings("unused")
    void setUp() {
        System.setOut(new PrintStream(myOut));
        testMaze = new Grid(4, 4);
    }

    @AfterEach
    @SuppressWarnings("unused")
    void tearDown() throws IOException {
        myOut.flush();
        String output = myOut.toString();
        System.setOut(originalOut);
        System.out.println(output);
    }

    @org.junit.jupiter.api.Test
    void drawDefaultMaze() throws IOException {
        new MazeAsciiDrawer().drawMaze(testMaze);
    }

    @org.junit.jupiter.api.Test
    void drawBinaryTreeMaze() throws IOException {
        BinaryTreeMazeAlgorithm.applyTo(testMaze);
        new MazeAsciiDrawer().drawMaze(testMaze);
    }
}
