package byog.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {
    public static final TETile PLAYER = new TETile('@', Color.white, new Color(10, 2, 2), "player");
    public static final TETile WALL = new TETile('#',new Color(8 ,4 ,4), new Color(19, 23, 81),
            "wall");
    public static final TETile FLOOR = new TETile('"', new Color(90, 90, 90), new Color (13, 14,46),
            "floor");
    public static final TETile DIM_FLOOR = new TETile('"', new Color(0,0,0), new Color(0,0,0), "dim floor");
    public static final TETile DIM_WALL = new TETile('#', Color.black ,Color.black,
            "dim wall");
    public static final TETile NOTHING = new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.orange, Color.black,
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.orange, Color.black,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");
    public static final TETile MONSTER = new TETile('&', Color.red, Color.black, "monster");
    public static final TETile INVMONSTER = new TETile('"', new Color(90, 90, 90),
            new Color (1, 4,55), "nothing");
    public static final TETile DARK_MONSTER = new TETile (' ',Color.black, Color.black, "nothing");
    public static final TETile LIGHT = new TETile('*', new Color(252, 251, 206), Color.black, "light");
    public static final TETile DIM_LIGHT =   new TETile(' ', Color.black, Color.black, "nothing");
    public static final TETile LIGHTED_WALL = new TETile('#', new Color(6, 2, 2), new Color(230, 196, 190),
            "wall");
    public static final TETile LIGHTED_FLOOR = new TETile('.', new Color(255, 195, 0),new Color(130, 119, 23),
            "floor");
    public static final TETile F4 = new TETile('"', new Color(234, 234, 248), new Color(5, 2, 2),
            "floor");
    public static final TETile BOSS = new TETile('Ψ', new Color(208, 33, 232), Color.black, "Boss");
    public static final TETile KEY = new TETile('γ', new Color(246, 200, 20), Color.black, "Key");
    public static final TETile HIDDEN_KEY = new TETile('·', Color.black, Color.black,
            "floor");
    public static final TETile POISON = new TETile('x', new Color(223, 204, 239),
            new Color (89, 20, 111), "Poison");
    public static final TETile DIM_POISON = new TETile('x', new Color(151, 130, 168),
            new Color (38, 1, 70), "Poison");

    public static final TETile[] TERRAIN = {WALL, LIGHTED_WALL, DIM_WALL, NOTHING, BOSS, MONSTER};
    public static final TETile[] PATH = {FLOOR, DIM_FLOOR, LIGHTED_FLOOR, POISON, DIM_POISON, WATER, GRASS, SAND, F4};
    public static final TETile[] ENEMIES = {MONSTER, DARK_MONSTER, INVMONSTER, BOSS};

    public static final TETile[] INV = {DIM_WALL, DIM_FLOOR, DIM_POISON, HIDDEN_KEY};
    public static final TETile[] LIGHTED = {LIGHTED_WALL, F4, POISON, KEY};
    public static final TETile[] DIMMED = {WALL, FLOOR, DIM_POISON, KEY};
}


