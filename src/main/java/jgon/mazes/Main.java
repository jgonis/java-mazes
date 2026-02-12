package jgon.mazes;

import java.util.concurrent.Callable;

import jgon.mazes.drawing.MazeImageDrawer;
import jgon.mazes.generation.BinaryTreeMazeAlgorithm;
import jgon.mazes.generation.MazeAlgorithm;
import jgon.mazes.generation.SidewinderMazeAlgorithm;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "mazegen", mixinStandardHelpOptions = true, version = "mazegen 0.1",
        description = "Generates mazes in various formats.")
class Mazegen implements Callable<Integer> {

    @Option(names = {"-a", "--algorithm"}, description = "The maze generation algorithm to use. Valid values: ${COMPLETION-CANDIDATES}")
    private MazeAlgorithm algorithm = MazeAlgorithm.Binary;
    @Parameters(index = "0", description = "The width of the maze in cells.")
    private int width;
    @Parameters(index = "1", description = "The height of the maze in cells.")
    private int height;
    @Parameters(index = "2", description = "The name of the output file for the generated maze.")
    private String outputFileName;

    @Override
    public Integer call() throws Exception { // your business logic goes here...
        System.out.println("Hello world! width: " + width + " height: " + height);
        Grid testMaze = new Grid(width, height);
        switch (algorithm) {
            case MazeAlgorithm.Binary ->
                BinaryTreeMazeAlgorithm.applyTo(testMaze);
            case MazeAlgorithm.Sidewinder ->
                SidewinderMazeAlgorithm.applyTo(testMaze);
        }
        new MazeImageDrawer("test.png").drawMaze(testMaze);
        return 0;
    }

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
}

public class Main {

    public static void main(String... args) {
        int exitCode = new CommandLine(new Mazegen()).execute(args);
        System.exit(exitCode);
    }
}
