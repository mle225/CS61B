package byog.lab5;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

//import java.util.Random;

public class HexagonWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    //to add: Seed ?

    //randomly adds a hexagon to a position in the world
    //positions 0-indexed
    public static void addHexagon(TETile[][] tiles, int r, int c, int size) {

        //Draws a hexagon at given position on the TETile[][]
        int lineCount = 1;
        int height = r + ( size * 2);
        int width = c + (lineCount * 2);
        for (int row = r; row < height; row++) {
            for (int col = c; col < size * width; col++) {
                tiles[row][col] = Tileset.WALL;
            }
            lineCount++;
            c--;
            width = c + (lineCount * 2);
        }
    }

    public static void main (String args[]) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hex = new TETile[WIDTH][HEIGHT];
        addHexagon(hex, 4,4,2 );

        ter.renderFrame(hex);

    }


}
