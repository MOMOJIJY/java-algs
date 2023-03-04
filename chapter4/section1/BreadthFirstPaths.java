package chapter4.section1;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

// 广度优先算法，解决找到最短路径问题
public class BreadthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public BreadthFirstPaths(Graph G, int s) {
        this.marked = new boolean[G.V()];
        this.edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);
    }

    private void dfs(Graph G, int s) {
        Queue<Integer> queue = new Queue<>();
        marked[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int x : G.adj(v)) {
                if (!marked[x]) {
                    marked[x] = true;
                    edgeTo[x] = v;
                    queue.enqueue(x);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!marked[v])
            return null;
        Stack<Integer> stack = new Stack<>();
        for (int w = v; w != s; w = edgeTo[w]) {
            stack.push(w);
        }
        stack.push(s);
        return stack;
    }
}
