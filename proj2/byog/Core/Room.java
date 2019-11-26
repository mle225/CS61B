package byog.Core;

import byog.TileEngine.Tileset;

import java.util.Random;

public class Room {

    private Random r;

    private int width;
    private int height;
    private Point lowerLeft;
    private Point upperRight;
    private Point center;
    private boolean visible;

    public Room(int newWidth, int newHeight, int mapW, int mapH, boolean visible) {

        r = new Random();
        width = newWidth;
        height = newHeight;
        lowerLeft = new Point(r.nextInt(mapW - 9) + 1, r.nextInt(mapH - 9) + 1);
        upperRight = new Point(lowerLeft.x() + width, lowerLeft.y() + height);
        center = new Point(lowerLeft.x() + (width / 2), lowerLeft.y() + (height / 2));
        this.visible = visible;
    }

    public Point getCenter() {
        return center;
    }

    public void drawRoom () {

        for (int y = lowerLeft.y(); y < upperRight.y(); y++) {
            for (int x = lowerLeft.x(); x < upperRight.x(); x++) {
                Game.world[x][y] = (visible) ? Tileset.FLOOR : Tileset.DIM_FLOOR;
            }
        }
    }

    public void drawWall () {
        for (int y = lowerLeft.y() - 1; y < upperRight.y() + 1; y++) {
            for (int x = lowerLeft.x() - 1; x < upperRight.x() + 1; x++) {
                if (Game.world[x][y] == Tileset.NOTHING) {
                    Game.world[x][y] = (visible) ? Tileset.WALL : Tileset.DIM_WALL;
                }
            }
        }
    }

    //Todo: Apply ColorVariant to Floor and Walls

    private boolean isCorner(Point p) {
        if (p == lowerLeft || p == upperRight || (p.x() == lowerLeft.x() && p.y() == upperRight.y())
        || (p.y() == lowerLeft.y() && p.x() == upperRight.x())) {
            return true;
        }
        return false;
    }

    public Point spawnDoor() {
        int rand = r.nextInt(2);
        Point res;
        if (rand % 2 == 0) {
            int x = lowerLeft.x() - 1 + r.nextInt(width / 2);
            Game.world[x][lowerLeft.y() - 1] = Tileset.LOCKED_DOOR;
            res = new Point(x, lowerLeft.y() - 1);
        }
        else {
            int x = lowerLeft.x() - 1 + r.nextInt(width / 2);
            Game.world[x][upperRight.y() + 1] = Tileset.LOCKED_DOOR;
            res = new Point(x, upperRight.y() + 1);
        }
        return res;
    }


}
