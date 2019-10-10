package hw2;

import java.lang.*;
import edu.princeton.cs.algs4.*;

public class PercolationStats {

    private double[] thresholds;
    private double T;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        thresholds = new double[T];
        for (int i = 0; i < T; ++i) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                perc.open(StdRandom.uniform(0, N - 1), StdRandom.uniform(0, N - 1 ));
            }
            thresholds[i] = perc.numberOfOpenSites()/ (N*N);
        }
    }

    public double mean() {
        return StdStats.mean(thresholds);
    }

    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(T));
    }

    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(T));
    }
}
