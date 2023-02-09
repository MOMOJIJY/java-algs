package chapter3.section1;

import edu.princeton.cs.algs4.Queue;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
        N = 0;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(Key key) {
        if (isEmpty()) {
            return false;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return true;
        return false;
    }

    public int rank(Key key) {
        return rank(key, 0, N-1);
    }

    private int rank(Key key, int lo, int hi) {
        if (lo > hi) return lo;
        int mid = lo + (hi - lo) / 2;
        int cmp = keys[mid].compareTo(key);
        if (cmp == 0) return mid;
        else if (cmp > 0) return rank(key, lo, mid-1);
        else return rank(key, mid+1, hi);
    }

    public Value get(Key key) {
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) return vals[i];
        return null;
    }

    private void resize(int newN) {
        Key[] newKeys = (Key[]) new Comparable[newN];
        Value[] newVals = (Value[]) new Object[newN];
        for (int i = 0; i < keys.length; i++) {
            newKeys[i] = keys[i];
        }
        for (int i = 0; i < vals.length; i++) {
            newVals[i] = vals[i];
        }
        keys = newKeys;
        vals = newVals;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = val;
            return;
        }
        if (keys.length < N+1) {
            resize(2 * N);
        }
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }
        N++;
        keys[i] = key;
        vals[i] = val;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(keys[i]);
        }
        return q;
    }
}
