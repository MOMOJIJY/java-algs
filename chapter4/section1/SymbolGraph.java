package chapter4.section1;

import chapter3.section3.RedBlackBST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SymbolGraph {
    private RedBlackBST<String, Integer> st;
    private String[] keys;
    private Graph G;

    public SymbolGraph(String filename, String delim) {
        In in = new In(filename);
        st = new RedBlackBST<>();
        // 第一步：读文件内容，生成每一个字符顶点的ID
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            for (String s : a) {
                if (!st.contains(s))
                    st.put(s, st.size());
            }
        }

        // 第二步：生成顶点的反向索引
        keys = new String[st.size()];
        for (String key : st.keys()) {
            keys[st.get(key)] = key;
        }

        // 第三步：读文件内容，生成符号图
        Graph G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delim);
            int v1 = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int v2 = st.get(a[i]);
                G.addEdge(v1, v2);
            }
        }
        this.G = G;
    }

    public boolean contains(String key) {
        return st.contains(key);
    }

    public int index(String key) {
        return st.get(key);
    }

    public String name(int v) {
        return keys[v];
    }

    public Graph G() {
        return G;
    }

    public static void main(String[] args) {
        String filename = args[0];
        String delim = args[1];
        SymbolGraph sg = new SymbolGraph(filename, delim);

        Graph G = sg.G();

        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            for (int w : G.adj(sg.index(source))) {
                StdOut.println("   " + sg.name(w));
            }
        }
    }
}
