package chapter3.section3;

import edu.princeton.cs.algs4.StdOut;

public class CacheNodeBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private boolean color;

        public Node(Key k, Value v, int N, boolean color) {
            this.key = k;
            this.val = v;
            this.N = N;
            this.color = color;
        }
    }

    private Node root;
    private Node latest;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public Value get(Key k) {
        if (latest != null && latest.key.compareTo(k) == 0) {
            return latest.val;
        }
        latest = get(root, k);
        return latest.val;
    }

    private Node get(Node x, Key k) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(k);
        if (cmp > 0) {
            return get(x.left, k);
        } else if (cmp < 0) {
            return get(x.right, k);
        }
        return x;
    }

    public boolean contains(Key key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    public void put(Key key, Value val) {
        if (latest != null && latest.key.compareTo(key) == 0) {
            latest.val = val;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            latest = new Node(key, val, 1, RED);
            return latest;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else {
            latest = x;
            x.val = val;
        }

        // 顺序不能乱
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.right) && isRed(x.left))
            flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        st.put("e", 1);
        st.put("a", 2);
        StdOut.println(st.get("a"));
    }
}
