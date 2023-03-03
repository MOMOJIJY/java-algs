package chapter3.section4;

import chapter3.section1.SequentialSearchST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class SeparateChainHashST<Key, Value> {
    private int N; // 键的数量
    private int M; // 散列表的数组大小
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainHashST() {
        this(997);
    }

    private SeparateChainHashST(int M) {
        this.M = M;
        this.st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private void resize(int cap) {
        SeparateChainHashST<Key, Value> st = new SeparateChainHashST<>(cap);
        for (int i = 0; i < this.M; i++) {
            SequentialSearchST<Key, Value> each = this.st[i];
            if (each.isEmpty()) continue;
            for (Key key : each.keys()) {
                st.put(key, each.get(key));
            }
        }
        this.M = st.M;
        this.st = st.st;
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return st[hash(key)].get(key);
    }

    public void put(Key key, Value val) {
        // key已经存在，修改键值，N保持不变
        if (contains(key)) st[hash(key)].put(key, val);
        // key不存在，新增一个键值对，N+1
        st[hash(key)].put(key, val);
        N++;
        if (N >= M*8) resize(2*M);
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        st[hash(key)].delete(key);
        N--;
        if (N > 0 && N <= M*2) resize(M/2);
    }

    public boolean contains(Key key) {
        return st[hash(key)].contains(key);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            SequentialSearchST<Key, Value> eachST = st[i];
            if (eachST.isEmpty())
                continue;
            for (Key k : eachST.keys()) {
                queue.enqueue(k);
            }
        }
        return queue;
    }

    public static void main(String[] args) {
        SeparateChainHashST<String, Integer> st = new SeparateChainHashST<>();
        st.put("S", 1);
        st.put("A", 1);
        st.put("C", 1);
        st.put("K", 1);
        st.put("G", 1);
        st.put("E", 1);
        st.put("P", 1);
        StdOut.println("原数组：");
        for (String k : st.keys()) {
            StdOut.printf("key=%s, val=%d\n", k, st.get(k));
        }
        st.delete("C");
        StdOut.println("删除后的数组：");
        for (String k : st.keys()) {
            StdOut.printf("key=%s, val=%d\n", k, st.get(k));
        }
    }

}
