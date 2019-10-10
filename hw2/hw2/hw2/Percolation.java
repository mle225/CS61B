package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF UFTree;
    private int size;
    private bool[][] map;
    int numOpenSites = 0;
    boolean percolate = false;

    public Percolation(int n) {
        if (n <= 0) {
            throw java.lang.IllegalArgumentException; 
        }
        UFTree = new WeightedQuickUnionUF(n);
        size = n;
        map = new bool[n][n];
    }

    public void open (int row, int col) {
        if (map[row][col] != 1) {
            map[row][col] = 1;
        }
    }

    public boolean isFull(int row, int col) {
        return map[row][col] == 1;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        return percolate;
    }

    private int getIndex(int row, int col) {

        return size * row + col;
    }

    public static void main (String[] args) {

    }
}
