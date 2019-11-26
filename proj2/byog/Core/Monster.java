package byog.Core;

import java.util.Random;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Monster {

    private Random r = new Random();
    private Point startPt;
    private int x;
    private int y;
    boolean alive = true;
    private TETile prevTile;

    public Monster() {
        Game.mCount++;
        startPt = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
        x = startPt.x();
        y = startPt.y();
        while (collides(x , y)) {
            startPt = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
            x = startPt.x();
            y = startPt.y();
        }
        prevTile = Game.world[x][y];
        Game.world[x][y] = (prevTile == Tileset.NOTHING) ? Tileset.DARK_MONSTER : Tileset.INVMONSTER;
    }

    private boolean collides(int x, int y) {
        return Game.world[x][y] != Tileset.FLOOR && Game.world[x][y] != Tileset.DIM_FLOOR
                && Game.world[x][y] != Tileset.LIGHTED_FLOOR && Game.world[x][y] != Tileset.F4;
    }

    private int distance() {
        return Math.abs(Player.locate().x() - x) + Math.abs(Player.locate().y() - y);
    }

    public void move()  {
        if (alive) {
            Point p = Player.locate();
            int dy = (y > p.y()) ? -1 : 1;
            int dx = (x > p.x()) ? -1 : 1;
            if (p.y() != y) {
                if (!collides(x, y + dy)) {
                    moveVertical(dy);
                }
                else {
                    if (!collides(x + dx, y)) {
                        moveHorizontal(dx);
                    }
                }
            } else {
                if (!collides(x + dx, y)) {
                    moveHorizontal(dx);
                }
                else {
                    if (!collides(x, y + dy)) {
                        moveVertical(dy);
                    }
                }
            }
        }
    }

    private void moveHorizontal(int dx) {
        Game.world[x][y] = prevTile;
        prevTile = Game.world[x + dx][y];
        if (distance() <= 6) {
            Game.world[x + dx][y] = Tileset.MONSTER;
        }
        else {
            Game.world[x + dx][y] = Tileset.INVMONSTER;
        }
        x += dx;
    }

    private void moveVertical(int dy) {
        Game.world[x][y] = prevTile;
        prevTile = Game.world[x][y + dy];
        if (distance() <= 6) {
            Game.world[x][y + dy] = Tileset.MONSTER;
        }
        else {
            Game.world[x][y + dy] = Tileset.INVMONSTER;
        }
        y += dy;
    }

    public void attack() {
        if (adj() && alive) {
            Player.hurt();
            die();
        }
    }

    private boolean adj () {
        if (x == Player.locate().x() + 1 || (x == Player.locate().x() - 1) ||
            y == Player.locate().y() + 1|| y == Player.locate().y() - 1) {
                return true;
        }
        return false;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void die() {
        System.out.println("dead");
        alive = false;
        Game.world[x][y] = Tileset.LIGHTED_FLOOR;
    }
}
