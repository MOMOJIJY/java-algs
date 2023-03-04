package chapter4.section1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s])
                dfs(G, s, s);
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w])
                dfs(G, w, v);
            else if (w != u)
                hasCycle = true;
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }

    public static void main(String[] args) {
        Graph G = new Graph(new In(args[0]));
        for (int s = 0; s < G.V(); s++) {
            StdOut.print(s + ": ");
            for (int x : G.adj(s)) {
                StdOut.print(x + "->");
            }
            StdOut.println();
        }
        StdOut.println();

        Cycle c = new Cycle(G);
        StdOut.println("hasCycle: " + c.hasCycle());
    }
}
