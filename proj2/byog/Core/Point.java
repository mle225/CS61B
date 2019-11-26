package byog.Core;

import java.util.Random;

public class Point {

    private int x;
    private int y;
    private static final int SEED = 21141;
    private static final Random r = new Random(SEED);

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x(){
        return x;
    }

    public int y(){
        return y;
    }

    public boolean alignHorizontal(Point other) {
        if (x == other.x()) {
            return true;
        }
        return false;
    }

    public boolean alignVertical(Point other) {
        if (y == other.y()) {
            return true;
        }
        return false;
    }
}
