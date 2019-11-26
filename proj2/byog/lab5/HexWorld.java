package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;

    private static final long SEED = 23478;
    private static final Random RANDOM = new Random(SEED);

    //randomly adds a hexagon to a position in the world
    //positions 0-indexed
    //x = c, y = r
    public static void addHexagon(TETile[][] tiles, int c, int r, int size) {
        //Draws a hexagon at given position on the TETile[][]
        int height = r + (size * 2);
        int temp = c;
        int lineCount = 0;
        int width;
        boolean full = false;
        TETile tile = randomTile();
        for (int row = r; row < height; row++) {
            width = widthCalc(lineCount, size);
            for (int col = temp; col < temp + width; col++) {
                tiles[col][row] = tile;
            }
            if (full == false) {
                if (lineCount == size - 1) {
                    full = true;
                }
                else {
                    lineCount++;
                    temp--;
                }
            }
            else {
                lineCount--;
                temp++;
            }
        }
    }

    //Helper method to calculate Width
    public static int widthCalc (int lineCount, int size) {
        return size + (2 * lineCount);
    }

    //Helper method to add a row of hexagons
    public static void hexRow (TETile[][] tiles, int wWidth, int wHeight, int hexSize, int row) {
        int x = 0;
        int y = 0;
        if (row == 0 || row == 8) {
            x = (wWidth / 2) - (hexSize / 2);
            if (row == 0) {
                y = 0;
            }
            else {
                y = wHeight - (hexSize * 2);
            }
            addHexagon(tiles, x, y, hexSize);
        }
        else {
            if (row % 2 == 1) {
                y = hexSize * row;
                x = (wWidth / 2) - hexSize * 2;
                for (int i = 0; i < 2; i++) {
                    addHexagon(tiles, x, y, hexSize);
                    x = x + hexSize + (hexSize * 2) + 1;
                }
            }
            else {
                y = hexSize * row;
                x = hexSize + 1;
                for (int i = 0; i < 3; i++) {
                    addHexagon(tiles, x, y, hexSize);
                    x = x + hexSize + (hexSize * 2) + 1;
                }
            }
        }
    }

    //Helper method to generate Random tile
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.SAND;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.TREE;
            case 6: return Tileset.WATER;
            default: return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] hex = new TETile[WIDTH][HEIGHT];

        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                hex[x][y] = Tileset.NOTHING;
            }
        }

        for (int i = 0; i < 9; i++) {
            hexRow(hex, WIDTH, HEIGHT, 3, i);
        }

        ter.renderFrame(hex);
    }

}
