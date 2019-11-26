package byog.Core;

import java.util.Random;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Player {

    private static int health = 4;
    private Random r = new Random();
    private Point startPt;
    private static int x;
    private static int y;
    public static boolean hasKey = false;
    private static boolean alive = true;

    public Player() {
        int randI = r.nextInt(Game.rooms.size());
        startPt = Game.rooms.get(randI).getCenter();
        x = startPt.x();
        y = startPt.y();
        Game.world[x][y] = Tileset.PLAYER;
    }

    public void move(char c) {
        if (c == 'w') {
            if (!collides(x, y + 1)) {
                Game.world[x][y++] = Tileset.F4;
                Game.world[x][y] = Tileset.PLAYER;
            }
        }
        else if (c == 's') {
            if (!collides(x, y - 1)) {
                Game.world[x][y--] = Tileset.F4;
                Game.world[x][y] = Tileset.PLAYER;
            }
        }
        else if (c == 'a') {
            if (!collides(x - 1, y)) {
                Game.world[x--][y] = Tileset.F4;
                Game.world[x][y] = Tileset.PLAYER;
            }
        }
        else if (c == 'd') {
            if (!collides(x + 1, y)) {
                Game.world[x++][y] = Tileset.F4;
                Game.world[x][y] = Tileset.PLAYER;
            }
        }
    }

    public boolean collides (int x, int y) {
        for (TETile t : Tileset.TERRAIN) {
            if (Game.world[x][y] == t) {
                return true;
            }
        }
        return false;
    }

    public static Point locate() {
        return new Point (x,y);
    }

    public void action(char key) {
        switch(key) {
            case 'd':
                handleD();
                break;
            case 'w':
                handleW();
                break;
            case 's':
                handleS();
                break;
            case 'a':
                handleA();
                break;
        }
    }

    private void handleD() {
        TETile tile = Game.world[x + 1][y];
        move('d');
        if (tile == Tileset.MONSTER) {
            for (Monster m : Game.monsters) {
                if (m.x() == x + 1 && m.y() == y) {
                    m.die();
                }
            }
        }
        else if (tile == Tileset.BOSS) {
            Game.boss.damaged();
        }
        else if (tile == Tileset.KEY) {
            Game.world[x + 1][y] = Tileset.F4;
            hasKey = true;
        }
    }

    private void handleW() {
        TETile tile = Game.world[x][y + 1];
        move('w');
        if (tile == Tileset.MONSTER) {
            for (int i = 0; i < Game.monsters.size(); i++) {
                Monster m = Game.monsters.get(i);
                if (m.x() == x && m.y() == y + 1) {
                    m.die();
                }
            }
        }
        else if (tile == Tileset.BOSS) {
            Game.boss.damaged();
        }
        else if (tile == Tileset.KEY) {
            Game.world[x][y + 1] = Tileset.F4;
            hasKey = true;
        }
    }

    private void handleA() {
        TETile tile = Game.world[x - 1][y];
        move('a');
        if (tile == Tileset.MONSTER) {
            for (Monster m : Game.monsters) {
                if (m.x() == x - 1 && m.y() == y) {
                    m.die();
                }
            }
        }
        else if (tile == Tileset.BOSS) {
            Game.boss.damaged();
        }
        else if (tile == Tileset.KEY) {
            Game.world[x - 1][y] = Tileset.F4;
            hasKey = true;
        }
    }

    private void handleS() {
        TETile tile = Game.world[x][y - 1];
        move ('s');
        if (tile == Tileset.MONSTER) {
            for (Monster m : Game.monsters) {
                if (m.x() == x && m.y() == y - 1) {
                    m.die();
                }
            }
        }
        else if (tile == Tileset.BOSS) {
            Game.boss.damaged();
        }
        else if (tile == Tileset.KEY) {
            Game.world[x][y - 1] = Tileset.F4;
            hasKey = true;
        }
    }

    public int getHealth() {
        return health;
    }

    public boolean alive() {
        return alive;
    }

    public static void hurt() {
        if (health > 1) {
            --health;
        }
        else if (health == 1) {
            alive = false;
        }
    }
}