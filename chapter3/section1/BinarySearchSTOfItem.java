package chapter3.section1;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.Queue;

public class BinarySearchSTOfItem<Key extends Comparable<Key>, Value> {
    private Item[] items;
    private int N;

    private class Item implements Comparable<Item> {
        public Key key;
        public Value val;
        public Item(Key k, Value v) {
            this.key = k;
            this.val = v;
        }

        public int compareTo(Item item) {
            return this.key.compareTo(item.key);
        }
    }

    public BinarySearchSTOfItem(int capacity) {
        items = new BinarySearchSTOfItem.Item[capacity];
    }

    public BinarySearchSTOfItem(Item[] items) {
        Merge.sort(items);
        this.items = items;
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
        Item item = new Item(key, null);
        int i = rank(item);
        if (i < N && items[i].compareTo(item) == 0) return true;
        return false;
    }

    public int rank(Item item) {
        if (N == 0) {
            return N;
        }
        return rank(item, 0, N-1);
    }

    private int rank(Item item, int lo, int hi) {
        if (lo > hi) {
            return lo;
        }
        int mid = lo + (hi - lo) / 2;
        int cmp = this.items[mid].compareTo(item);
        if (cmp == 0) {
            return mid;
        } else if (cmp > 0) {
            return rank(item, lo, mid-1);
        } else {
            return rank(item, mid+1, hi);
        }
    }

    private void resize(int newN) {
        Item[] newItems = new BinarySearchSTOfItem.Item[newN];
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    public void put(Key k, Value v) {
        if (isEmpty()) {
            items[0] = new Item(k, v);
            N++;
        }
        if (N+1 > items.length) {
            resize(N*2);
        }
        Item item = new Item(k, v);
        int i = rank(item);
        if (i < N && this.items[i].compareTo(item) == 0) {
            this.items[i].val = v;
            return;
        }
        for (int j = N; j > i; j--) items[j] = items[j-1];
        items[i] = item;
        N++;
    }

    public Value get(Key k) {
        if (isEmpty()) {
            return null;
        }
        Item item = new Item(k, null);
        int i = rank(item);
        if (i < N && this.items[i].compareTo(item) == 0) {
            return this.items[i].val;
        }
        return null;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (int i = 0; i < N; i++) {
            q.enqueue(items[i].key);
        }
        return q;
    }
}
