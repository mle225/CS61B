package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Hallway {

    private Point start;
    private Point end;
    private int left, top, bot, right;
    private boolean leftHallway;
    private boolean visible = true;
    private Random r = new Random();

    public Hallway(Point startPt, Point endPt, boolean visible) {

        start = startPt;
        end = endPt;
        left = Math.min(start.x(), end.x());
        right = Math.max(start.x(), end.x());
        top = Math.max(start.y(), end.y());
        bot = Math.min(start.y(), end.y());
        leftHallway = false;
        this.visible = visible;
    }

    public void drawHallway(TETile[][] map) {
        for (int col = left; col < right; col++) {
            map[col][bot] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
        }
        drawVerticalHallway(map, start, end);
    }

    public void drawVerticalHallway(TETile[][] map, Point start, Point end) {
        if (start.y() > end.y()) {
            if (start.x() < end.x()) {
                leftHallway = true;
                for (int row = bot; row < top; row++) {
                    map[left][row] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
                }
            }
            else {
                for (int row = bot; row < top; row++) {
                    map[right][row] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
                }
            }
        }
        else {
            if (start.x() < end.x()) {
                for (int row = bot; row < top; row++) {
                    map[right][row] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
                }
            }
            else {
                leftHallway = true;
                for (int row = bot; row < top; row++) {
                    map[left][row] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
                }
            }
        }
    }

    public void drawWall(TETile[][] map) {
            //Draw Horizontal Walls
            for (int x = left - 1; x < right + 1; x++) {
                for (int y = bot - 1; y < bot + 2; y++) {
                    if (map[x][y] == Tileset.NOTHING) {
                        map[x][y] = (visible) ? Tileset.WALL : Tileset.DIM_WALL;
                    }
                }
            }
            //Draw Vertical Walls
            if (leftHallway) {
                for (int x = left - 1; x < left + 2; x++) {
                    for (int y = bot - 1; y < top + 1; y++) {
                        if (map[x][y] == Tileset.NOTHING) {
                            map[x][y] = (visible) ? Tileset.WALL : Tileset.DIM_WALL;
                        }
                    }
                }
            }
            else {
                for (int x = right - 1; x < right + 2; x++) {
                    for (int y = bot - 1; y < top + 1; y++) {
                        if (map[x][y] == Tileset.NOTHING) {
                            map[x][y] = (visible) ? Tileset.WALL : Tileset.DIM_WALL;
                        }
                    }
                }
            }
        }
}
