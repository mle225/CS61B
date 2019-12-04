package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.LinkedList;

public class Solver {

    private class searchNode implements Comparable<searchNode> {

        private WorldState ws;
        private int moves;
        private int dSoFar;
        searchNode prev;

        public searchNode(WorldState ws, searchNode prev, int dSoFar) {
            this.ws = ws;
            moves = ws.estimatedDistanceToGoal();
            this.prev = prev;
            this.dSoFar = dSoFar;
        }

        public int getMoves() {
            return moves;
        }

        //del if not needed
        public searchNode getPrev() {
            return prev;
        }

        public WorldState getWs() {
            return ws;
        }

        public Iterable<WorldState> getNeighbors() {
           return ws.neighbors();
        }

        public int dSoFar() {
            return dSoFar;
        }

        public boolean isGoal() {
            return ws.isGoal();
        }

        @Override
        public int compareTo(searchNode other) {
            if (dSoFar + moves == other.getMoves() + other.dSoFar()) {
                return 0;
            }
            int res;
            res = (dSoFar + moves > other.getMoves() + other.dSoFar()) ? 1 : -1;
            return res;
        }
    }

    private int totalMoves;
    private int counter = 0;
    private Iterable<WorldState> iws;
    private MinPQ<searchNode> pq = new MinPQ<>();
    LinkedList<WorldState> res = new LinkedList<>();

    public Solver(WorldState initial) {

        int soFar = 0;
        //insert initial node first
        searchNode init = new searchNode(initial, null, 0);
        pq.insert(init);
        //add initial sNode to return iterable
        soFar++;
        //remove Min
        pq.delMin();
        //add each nb of min to pq
        Iterable<WorldState> nb = init.getNeighbors();
        for (WorldState w : nb) {
            pq.insert(new searchNode(w, init, 1));
            ++counter;
        }
        //by this point, finished adding first sNode's neighbors into pq
        //proceed to repeat process for each nb in pq
        while (!pq.min().isGoal()) {
            searchNode min = pq.min();
            searchNode prev = min.getPrev();
            soFar++;
            if (min.dSoFar() < soFar) {
                soFar = min.dSoFar();
            }
            pq.delMin();
            Iterable<WorldState> nbs = min.getNeighbors();
            for (WorldState w : nbs) {
                //check if equals grandparent, if not -> insert
                if (!w.equals(prev.getWs())) {
                    pq.insert(new searchNode(w, min, min.dSoFar() + 1));
                    ++counter;
                }
            }
        }
        getPath(pq.min());
        totalMoves = ++soFar;
    }

    private void getPath(searchNode sn) {
        while (sn != null) {
            res.addFirst(sn.getWs());
            sn = sn.getPrev();
        }
    }

    public int moves() {
        return totalMoves;
    }

    public Iterable<WorldState> solution() {
        return res;
    }

    public int getCounter() {
        return counter;
    }
}
