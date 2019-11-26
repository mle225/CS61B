package byog.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.ArrayList;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

public class Game {
    private TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    private int WIDTH = 62;
    private int HEIGHT = 34;
    private int seed;
    private Random r;
    private boolean gameOver = false;
    public static int mCount = 0;

    public static TETile[][] world;
    public static ArrayList<Room> rooms = new ArrayList<>(15);
    private static ArrayList<Hallway> hallways = new ArrayList<>(25);
    public static ArrayList<Monster> monsters = new ArrayList<>(8);

    private Fov fov;
    private Player p;
    private boolean hasBoss = false;
    public static Boss boss;
    private Key key;
    private Point door;
    private int level;
    private boolean bossAlive = true;

    public Game() {
        world = new TETile[60][30];
        ter.initialize(WIDTH, HEIGHT, 1, 4);
        fillWorld();
        level = 0;
    }

    private static void fillWorld() {
        int height = Game.world[0].length;
        int width = Game.world.length;
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                Game.world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
        menuScreen();
        StdDraw.clear(Color.orange);
        seed = solicitSeed();
        r = new Random(seed);
        newGame();
        gameOver();
    }

    private void menuScreen() {
        StdDraw.clear(Color.darkGray);
        Font bigFont = new Font ("Monaco", Font.BOLD, 50);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH /2, HEIGHT - 5, "The Game");
        Font sFont = new Font (" Monaco", Font.PLAIN, 25);
        StdDraw.setFont(sFont);
        StdDraw.text(WIDTH /2, HEIGHT - 14, "New Game (N)");
        StdDraw.text(WIDTH /2, HEIGHT - 17, "Load (L)");
        StdDraw.text(WIDTH /2, HEIGHT - 20, "Quit (Q)");
        StdDraw.show();

        String input = "";
        while (input.length() < 1) {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            char temp = StdDraw.nextKeyTyped();
            if (temp == 'n' || temp == 'l') {
                input += temp;
                break;
            } else if (temp == 'q') {
                System.exit(0);
            }
        }
    }

    private void topHUD(String message) {
        Font smallFont = new Font("sans serif", 0,14);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(new Color(23, 50, 80));
        StdDraw.filledRectangle(WIDTH/ 2, HEIGHT - 0.5, 31 , 1);
        StdDraw.setPenColor(Color.white);
        StdDraw.textLeft(1, HEIGHT - 1, "Health: " + p.getHealth());
        StdDraw.text(WIDTH / 2, HEIGHT - 1, message);
        StdDraw.textRight(WIDTH - 1, HEIGHT - 1, "Level: " + level);
        StdDraw.show();
        StdDraw.setFont(new Font("sans serif", 0, 14));
    }

    public void botHUD() {
        //This is boxes and stuff
        Font smallFont = new Font ("sans serif", 0, 14);
        StdDraw.setFont(smallFont);
        StdDraw.setPenColor(new Color(23, 50, 80));
        StdDraw.filledRectangle(WIDTH/ 2, 0.25, 31 , 1);
        //This is word stuff
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.text(WIDTH / 7,0.5, "Menu (M)");
        StdDraw.text( 2* WIDTH / 7, 0.5, "Inventory (I)");
        StdDraw.text(3* WIDTH / 7, 0.5, "Stuff (S)");
        StdDraw.text(5 * WIDTH / 7, 0.5, "You can feel the cool grass on your feet");
        StdDraw.show();
        StdDraw.setFont(new Font("sans serif", 0, 14));
    }

    public void newGame() {
        setup(false);
        gameLoop();
    }

    private void gameLoop() {
        long count = 0;
        String message = "Find the key!";
        while (!gameOver) {
            topHUD(message);
//            botHUD();
            ++count;
            if (count == 100000000) {
                count = 0;
            }
            if (count % 800 == 0 && monsters.size() < 3) {
                Monster m = new Monster();
                monsters.add(m);
            }
            if (count % 50 == 0) {
                moveMonsters();
            }
            if (count % 50 == 0) {
                for (Monster m : monsters) {
                    m.attack();
                }
            }
            if (count % 25 == 0 && hasBoss) {
                moveBoss();
            }
            fov.clearField();
            fov.makeFov();
            if (!p.alive()) {
                gameOver = true;
            }
            if (p.hasKey && !hasBoss) {
                message = "Beat the Boss!";
                boss = new Boss();
                hasBoss = true;
            }
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if (c != 'm') {
                    p.action(c);
                }
                else {
                    menu();
                }
            }
            ter.renderFrame(world);
        }
    }

    private void gameOver() {
        StdDraw.clear(Color.black);
        Font bigFont = new Font ("Monaco", Font.BOLD, 50);
        StdDraw.setPenColor(Color.white);
        StdDraw.setFont(bigFont);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Game Over");
        StdDraw.show();
        StdDraw.pause(2000);
        System.exit(0);
    }

    private void moveBoss() {
        boss.move();
    }

    private void moveMonsters() {
        for (Monster m : monsters) {
            m.move();
        }
    }

    private void menu() {
        while (!StdDraw.hasNextKeyTyped()) {
            Font mFont = new Font("monaco", 0, 20);
            StdDraw.setFont(mFont);
            //Edge rect
            StdDraw.setPenColor(new Color(23, 5, 81));
            StdDraw.filledRectangle(WIDTH / 2, HEIGHT / 2, 15,8);
            StdDraw.setPenColor(new Color(15, 12, 86));
            //big rect
            StdDraw.filledRectangle(WIDTH / 2, HEIGHT / 2, 14,7);
            //smaller rects
            StdDraw.setPenColor(new Color(107, 105, 146));
            StdDraw.filledRectangle(WIDTH / 2, HEIGHT - 15, 3, 2);
            StdDraw.text(WIDTH / 2, HEIGHT - 21, "Quit (Q)");
            StdDraw.show();
        }
    }

    private void setup (boolean visible) {
        Font font = new Font("sans serif", 0, 16);
        StdDraw.setFont(font);
        generateRooms(visible);
        generateHallways(visible);
        generateWalls();
        generateDoor();
        p = new Player();
        fov = new Fov(7);
        key = new Key(visible);
    }

    private int solicitSeed() {
        StdDraw.setPenColor(Color.RED);
        String input = "";
        Font seedFont = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(seedFont);
        StdDraw.text(WIDTH/2, HEIGHT / 2, "Please Enter Seed #####S");
        StdDraw.show();
        char temp = '!';
        while (temp != 's') {
            if (!StdDraw.hasNextKeyTyped()) {
                continue;
            }
            temp = StdDraw.nextKeyTyped();
            input+= temp;
        }
        int res = Integer.parseInt(input.substring(0, input.length() - 1));
        return res;
    }

    private void generateRooms(boolean visible) {
        for (int i = 0; i < 25 ; i++) {
            Room room = new Room(r.nextInt(7) + 1, r.nextInt(7) + 1, 60, 30, visible);
            rooms.add(room);
            room.drawRoom();
        }
    }

    private void generateHallways(boolean visible) {
        for (int i = 1; i < 25; i++) {
            Hallway h = new Hallway(rooms.get(i).getCenter(), rooms.get(i - 1).getCenter(), visible);
            hallways.add(h);
            h.drawHallway(world);
        }
    }

    private void generateWalls() {
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).drawWall();
        }
        for (int i = 0; i < hallways.size(); i++) {
            hallways.get(i).drawWall(world);
        }
    }

    private void generateDoor() {
        door = rooms.get(r.nextInt(rooms.size())).spawnDoor();
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().

        TETile[][] finalWorldFrame = null;
        return finalWorldFrame;
    }
}
