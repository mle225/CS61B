package byog.Core;

import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Light {

    private Fov fov = new Fov(3);
    private Point startPt;
    private Random r = new Random();

    public Light() {
        int randInd = r.nextInt(Game.rooms.size());
        startPt = Game.rooms.get(randInd).getCenter();
        Game.world[startPt.x()][startPt.y()] = Tileset.DIM_LIGHT;
    }

//    public void turnOn () {
//        fov.makeLightField(Game.world, startPt);
//    }

    public boolean nextToPlayer() {
        return (Game.world[startPt.x()-1][startPt.y()] == Tileset.PLAYER ||
                Game.world[startPt.x()+1][startPt.y()] == Tileset.PLAYER ||
                Game.world[startPt.x()][startPt.y()-1] == Tileset.PLAYER ||
                Game.world[startPt.x()][startPt.y()+1] == Tileset.PLAYER);
    }

    private boolean oob(int x, int y, TETile[][] world) {
        return (x >= world.length || x < 0 || y >= world[0].length || y < 0);
    }
}
