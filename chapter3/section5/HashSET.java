package chapter3.section5;

import chapter3.section4.SeparateChainHashST;

public class HashSET<Key> {
    private SeparateChainHashST<Key, Integer> st;
    public HashSET() {
        this.st = new SeparateChainHashST<>();
    }

    public void add(Key key) {
        st.put(key, null);
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
}
