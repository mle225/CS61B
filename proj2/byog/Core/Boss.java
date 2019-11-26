package byog.Core;

import java.util.Random;
import byog.TileEngine.Tileset;

public class Boss {

    private static int health = 7;
    private int x;
    private int y;
    private boolean alive = true;
    private Random r = new Random();

    public Boss() {
        Point p = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
        while (distance() <= 15) {
            p = Game.rooms.get(r.nextInt(Game.rooms.size())).getCenter();
        }
        Game.world[p.x()][p.y()] = Tileset.BOSS;
        x = p.x();
        y = p.y();
    }

    public void move()  {
        if (alive) {
            Point p = Player.locate();
            int dy = (y > p.y()) ? -1 : 1;
            int dx = (x > p.x()) ? -1 : 1;
            if (p.y() != y) {
                if (!collides(x, y + dy)) {
                    moveVertical(dy);
                } else {
                    if (!collides(x + dx, y)) {
                        moveHorizontal(dx);
                    }
                }
            } else {
                if (!collides(x + dx, y)) {
                    moveHorizontal(dx);
                } else {
                    if (!collides(x, y + dy)) {
                        moveVertical(dy);
                    }
                }
            }
        }
    }

    private void moveHorizontal(int dx) {
        Game.world[x][y] = Tileset.DIM_POISON;
        Game.world[x + dx][y] = Tileset.BOSS;
        x += dx;
    }

    private void moveVertical(int dy) {
        Game.world[x][y] = Tileset.DIM_POISON;
        Game.world[x][y + dy] = Tileset.BOSS;
        y += dy;
    }

    private boolean collides(int x, int y) {
        if (Game.world[x][y] == Tileset.FLOOR || Game.world[x][y] == Tileset.DIM_FLOOR || Game.world[x][y] == Tileset.LIGHTED_FLOOR
                || Game.world[x][y] == Tileset.F4 || Game.world[x][y] == Tileset.POISON || Game.world[x][y] == Tileset.DIM_POISON) {
            return false;
        }
        return true;
    }

    private int distance() {
        return Math.abs(Player.locate().x() - x) + Math.abs(Player.locate().y() - y);
    }

    public void damaged() {
        if (health > 0) {
            --health;
        }
        if (health == 0) {
            alive = false;
            Game.world[x][y] = Tileset.POISON;
        }
    }

    public boolean alive() {
        return alive;
    }

}
