package hw2;

import java.lang.*;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF UFTree;
    private int size;
    private boolean[][] map;
    private int numOpenSites = 0;
    private boolean[] openChannels;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        UFTree = new WeightedQuickUnionUF(n*n);
        size = n;
        map = new boolean[n][n];
        openChannels = new boolean[size];
    }

    public void open (int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (!map[row][col]) {
            map[row][col] = true;
            numOpenSites++;
            if (row == 0) {
                openChannels[col] = true;
            }
        }
        connect (row, col);
    }

    public boolean isOpen(int row, int col)  {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        return map[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
        int len = openChannels.length;
        int index = getIndex(row, col);
        for (int i = 0; i < len; i++) {
            if (openChannels[i]) {
                if (UFTree.connected(index,i)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        for (int i = 0; i < map[0].length; i++) {
            if (isFull(map.length - 1,i )) {
                return true;
            }
        }
        return false;
    }

    private int getIndex(int row, int col) {
        return size * row + col;
    }

    private void connect(int row, int col) {
        int index1 = getIndex(row, col);

        if (col - 1 >=0 && map[row][col - 1]) {
            int index2 = getIndex(row, col - 1);
            if (!UFTree.connected(index1, index2)) {
                UFTree.union(index1, index2);
            }
        }
        if (row - 1 >= 0 && map[row - 1][col]) {
            int index2 = getIndex(row - 1, col);
            if (!UFTree.connected(index1, index2)) {
                UFTree.union(index1, index2);
            }
        }
        if (row + 1 < size && map[row + 1][col]) {
            int index2 = getIndex(row + 1, col);
            if (!UFTree.connected(index1, index2)) {
                UFTree.union(index1, index2);
            }
        }
        if (col + 1 < size && map[row][col + 1]) {
            int index2 = getIndex(row, col + 1);
            if (!UFTree.connected(index1, index2)) {
                UFTree.union(index1, index2);
            }
        }
    }

}
