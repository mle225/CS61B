package byog.Core;

import java.util.Random;
import byog.TileEngine.Tileset;

public class Key {

    private static int x;
    private static int y;
    private Random r = new Random();

    public Key(boolean visible) {
        Point startP = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
        while (collides(startP.x(), startP.y()) || distance(startP.x(), startP.y()) <= 15) {
            startP = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
        }
        x = startP.x();
        y = startP.y();
        Game.world[x][y] = (visible) ? Tileset.KEY : Tileset.HIDDEN_KEY;
    }

    private boolean collides(int x, int y) {
        if (Game.world[x][y] == Tileset.FLOOR || Game.world[x][y] == Tileset.DIM_FLOOR
        || Game.world[x][y] == Tileset.LIGHTED_FLOOR) {
            return false;
        }
        return true;
    }

    private int distance(int x, int y) {
        Point p = Player.locate();
        return Math.abs(x - p.x()) + Math.abs(y - p.y());
    }

    public static Point locate() {
        return new Point(x,y);
    }
}
