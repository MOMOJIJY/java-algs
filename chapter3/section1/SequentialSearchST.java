package chapter3.section1;

import edu.princeton.cs.algs4.Queue;

public class SequentialSearchST<Key, Value> {
    private Node first;
    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public boolean isEmpty() {
        return first == null;
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) return x.val;
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) return true;
        }
        return false;
    }

    public void delete(Key key) {
        if (first != null && first.key.equals(key)) {
            first = null;
            return;
        }
        Node x = new Node(null, null, first);
        while (x.next != null) {
            if (x.next.key.equals(key)) {
                x.next = x.next.next;
                return;
            }
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<>();
        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }
}
