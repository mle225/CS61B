package hw4.puzzle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class mTest {

    private int[][] board;
    private int est;
    private int size;

    public mTest(int[][] a) {
        size = a.length;
        board = a;
    }

    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int expected = size * i + (j + 1);
                int actual = board[i][j];
                if (actual != 0) {
                    if (actual != expected) {
                        int expR = (actual - 1) / size;
                        int expC = (actual - 1) % size;
                        sum += Math.abs(expR - i) + Math.abs(expC - j);
                    }
                }
            }
        }
        est = sum;
        return sum;
    }

    public static void main(String[] args) {

        int[][] b1 =
                {{0,1,3},
                 {4,2,5},
                 {7,8,6}};

        mTest m1 = new mTest(b1);
        System.out.println(m1.manhattan());
    }
}
