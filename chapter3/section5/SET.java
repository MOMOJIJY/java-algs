package chapter3.section5;

import chapter3.section3.RedBlackBST;

public class SET <Key extends Comparable<Key>> {
    private RedBlackBST<Key, Integer> st;
    public SET() {
        this.st = new RedBlackBST<>();
    }

    public void add(Key key) {
        st.put(key, 0);
    }

    public void delete(Key key) {
        if (!contains(key)) return;
        st.delete(key);
    }

    public boolean contains(Key key) {
        return st.contains(key);
    }

    public boolean isEmpty() {
        return st.isEmpty();
    }

    public int size() {
        return st.size();
    }

    public Iterable<Key> keys() {
        return st.keys();
    }
}
