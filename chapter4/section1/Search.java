package chapter4.section1;

import edu.princeton.cs.algs4.UF;

public class Search {
    private UF uf;
    private int s;
    private Graph G;
    private int count = -1;

    public Search(Graph G, int s) {
        this.s = s;
        this.G = G;
        uf = new UF(G.V());
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                uf.union(i, j);
            }
        }
    }

    public boolean marked(int v) {
        return uf.connected(s, v);
    }

    public int count() {
        if (count >= 0)
            return count;
        for (int i = 0; i < G.V(); i++) {
            if (this.marked(i)) {
                count++;
            }
        }
        return count;
    }
}
