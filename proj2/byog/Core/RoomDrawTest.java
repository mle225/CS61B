package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;

public class RoomDrawTest {

    public static void fillWorld(TETile[][] tiles) {
        int height = tiles[0].length;
        int width = tiles.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                tiles[x][y] = Tileset.NOTHING;
            }
        }
    }

    public static void main(String[] args) {
        final int WIDTH = 62 ;
        final int HEIGHT = 33;
        int SEED = 41414;
        Random r = new Random(SEED);

        TETile[][] tiles = new TETile[60][30];
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT, 1, 3);
        fillWorld(tiles);
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Hallway> hallways = new ArrayList<>();

        for (int i = 0; i < 24 ; i++) {
            Room room = new Room(r.nextInt(7) + 1, r.nextInt(7) + 1, 60, 30, true);
            rooms.add(room);
            room.drawRoom();
            System.out.println(room.getCenter().x() + " " + room.getCenter().y());
        }

        for (int i = 1; i < rooms.size(); i++) {
            Hallway h = new Hallway(rooms.get(i).getCenter(), rooms.get(i - 1).getCenter(), true);
            hallways.add(h);
            h.drawHallway(tiles);
        }
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).drawWall();
        }
        for (int i = 0; i < hallways.size(); i++) {
            hallways.get(i).drawWall(tiles);
        }
        ter.renderFrame(tiles);
    }

}
