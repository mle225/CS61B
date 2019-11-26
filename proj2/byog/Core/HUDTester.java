package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class HUDTester {

    private static TERenderer ter = new TERenderer();
    private static TETile[][] world;
    private static int WIDTH = 62;
    private static int HEIGHT = 33;

    public static void topHUD(String message) {
        //This is box stuff
        Font smallFont = new Font ("sans serif", 0, 14);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(new Color(23, 50, 80));
        StdDraw.filledRectangle(WIDTH/ 2, HEIGHT - 0.5, 31 , 1);
        //This is word stuff
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(Color.white);
        StdDraw.textLeft(1, HEIGHT - 1, "Health: 7");
        StdDraw.text(WIDTH / 2, HEIGHT - 1, message);
        StdDraw.textRight(WIDTH - 1, HEIGHT - 1, "Level: 7");
        StdDraw.show();
        StdDraw.setFont(new Font("sans serif", 0, 14));
    }

    public static void botHUD() {
        //This is boxes and stuff
        Font smallFont = new Font ("sans serif", 0, 14);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(new Color(23, 50, 80));
        StdDraw.filledRectangle(WIDTH/ 2, 0.25, 31 , 1);
        //This is word stuff
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH / 7,0.5, "Menu (M)");
        StdDraw.text( 2* WIDTH / 7, 0.5, "Inventory (I)");
        StdDraw.text(3* WIDTH / 7, 0.5, "Stuff (S)");
        StdDraw.text(5 * WIDTH / 7, 0.5, "You can feel the cool grass on your feet");
        StdDraw.show();
        StdDraw.setFont(new Font("sans serif", 0, 14));
    }

    private static void fillWorld() {
        int height = 30;
        int width = 60;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void setUp () {
        world = new TETile[60][30];
        ter.initialize(WIDTH, HEIGHT, 1, 3);
        fillWorld();
    }

    public static void main (String[] args) {
        setUp();
        while (!StdDraw.hasNextKeyTyped()) {
            topHUD("Yeah");
            botHUD();
        }
    }
}
