package byog.Core;

import byog.TileEngine.Tileset;

public class Fov {

    private int fieldLength;
    private double cosines[] = new double[361];
    private double sines[] = new double[361];

    public Fov(int n){
        fieldLength = n;
        calcCos();
        calcSines();
    }

    private void calcCos() {
        for (int i = 0; i < 361; i++) {
            cosines[i] = Math.cos(i);
        }
    }

    private void calcSines() {
        for (int i = 0; i < 361; i++) {
            sines[i] = Math.sin(i);
        }
    }

    public void makeFov() {
        for (int i = 0; i < 361; i++) {
            drawLos(cosines[i], sines[i]);
        }
    }

    private void drawLos(double x, double y) {
        double ox = Player.locate().x() + 0.5;
        double oy = Player.locate().y() + 0.5;
        for (int i = 0; i < fieldLength + 1 ; i++) {
            if (!oob((int) ox, (int) oy)) {
                if (Game.world[(int) ox][(int) oy] == Tileset.DIM_FLOOR || Game.world[(int) ox][(int) oy] == Tileset.FLOOR) {
                    Game.world[(int) ox][(int) oy] = Tileset.F4;
                }
                else if (Game.world[(int) ox][(int) oy] == Tileset.INVMONSTER) {
                    Game.world[(int) ox][(int) oy] = Tileset.MONSTER;
                }
                else if (Game.world[(int) ox][(int) oy] == Tileset.HIDDEN_KEY) {
                    Game.world[(int) ox][(int) oy] = Tileset.KEY;
                }
                else if (Game.world[(int) ox][(int) oy] == Tileset.DIM_POISON) {
                    Game.world[(int) ox][(int) oy] = Tileset.POISON;
                }
                else if (Game.world[(int) ox][(int) oy] == Tileset.LIGHTED_WALL) {
                    break;
                }
                else if (Game.world[(int) ox][(int) oy] == Tileset.WALL || Game.world[(int) ox][(int) oy] == Tileset.DIM_WALL) {
                    Game.world[(int) ox][(int) oy] = Tileset.LIGHTED_WALL;
                    break;
                }
            }
            ox += x;
            oy += y;
        }
    }

    public void clearField() {
        Point pPos = Player.locate();
        for (int x = pPos.x() - 10; x < pPos.x() + 11; x++) {
            for (int y = pPos.y() - 10; y < pPos.y() + 11; y++) {
                if (!oob(x,y)) {
                    if (Game.world[x][y] == Tileset.LIGHTED_FLOOR) {
                        Game.world[x][y] = Tileset.FLOOR;
                    }
                    else if (Game.world[x][y] == Tileset.LIGHT) {
                        Game.world[x][y] = Tileset.DIM_LIGHT;
                    }
                    else if (Game.world[x][y] == Tileset.F4) {
                        Game.world[x][y] = Tileset.FLOOR;
                    }
                    else if (Game.world[x][y] == Tileset.LIGHTED_WALL) {
                        Game.world[x][y] = Tileset.WALL;
                    }
                    else if (Game.world[x][y] == Tileset.POISON) {
                        Game.world[x][y] = Tileset.DIM_POISON;
                    }
                }
            }
        }
    }

    private boolean oob(int x, int y) {
        return (x >= Game.world.length || x < 0 || y >= Game.world[0].length || y < 0);
    }
}
