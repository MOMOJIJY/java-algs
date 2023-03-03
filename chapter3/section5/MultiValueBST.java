package chapter3.section5;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class MultiValueBST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key k, Value v, int N) {
            this.key = k;
            this.val = v;
            this.N = N;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.N;
    }

    public int height() {
        return height(root);
    }

    public int avgCompares() {
        return internalPathLen(root, 0) / size();
    }

    private int internalPathLen(Node x, int len) {
        if (x == null) {
            return 0;
        }
        int lLen = internalPathLen(x.left, len + 1);
        int rLen = internalPathLen(x.right, len + 1);
        return len + 1 + lLen + rLen;
    }

    private int height(Node x) {
        if (x == null) {
            return 0;
        }

        int lh = height(x.left);
        int rh = height(x.right);
        int h;
        if (lh > rh)
            h = lh;
        else
            h = rh;
        return h + 1;
    }

    public Value get(Key k) {
        return get(root, k);
    }

    private Value get(Node x, Key k) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(k);
        if (cmp > 0) {
            return get(x.left, k);
        } else if (cmp < 0) {
            return get(x.right, k);
        }
        return x.val;
    }

    public Value getNoRecursion(Key k) {
        Node x = root;
        while (x != null) {
            int cmp = k.compareTo(x.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                return x.val;
            }
        }
        return null;
    }

    public void putNoRecursion(Key k, Value v) {
        if (root == null) {
            root = new Node(k, v, 1);
            return;
        }

        Stack<Node> s = new Stack<>();
        Node x = root;
        while (true) {
            s.push(x);
            int cmp = k.compareTo(x.key);
            if (cmp < 0) {
                if (x.left == null) {
                    x.left = new Node(k, v, 1);
                    break;
                }
                x = x.left;
            } else {
                if (x.right == null) {
                    x.right = new Node(k, v, 1);
                    break;
                }
                x = x.right;
            }
        }

        while (!s.isEmpty()) {
            x = s.pop();
            x.N++;
        }
    }

    public void put(Key k, Value v) {
        root = put(root, k, v);
    }

    private Node put(Node x, Key k, Value v) {
        if (x == null) {
            return new Node(k, v, 1);
        }

        int cmp = x.key.compareTo(k);
        if (cmp >= 0) {
            Node t = put(x.left, k, v);
            x.left = t;
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        } else {
            Node t = put(x.right, k, v);
            x.right = t;
            x.N = size(x.left) + size(x.right) + 1;
            return x;
        }
    }

    public boolean contains(Key k) {
        if (get(k) == null) {
            return false;
        }
        return true;
    }

    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    public Key floor(Key k) {
        if (root == null) {
            return null;
        }
        return floor(root, k).key;
    }

    private Node floor(Node x, Key k) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(k);
        if (cmp == 0) {
            return x;
        } else if (cmp > 0) {
            return floor(x.left, k);
        }
        Node t = floor(x.right, k);
        if (t == null) {
            return x;
        }
        return t;
    }

    public Key ceiling(Key k) {
        if (root == null) {
            return null;
        }

        return ceiling(root, k).key;
    }

    private Node ceiling(Node x, Key k) {
        if (x == null) {
            return null;
        }

        int cmp = x.key.compareTo(k);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.right, k);
        }
        Node t = floor(x.left, k);
        if (t == null) {
            return x;
        }
        return t;
    }

    public Key select(int n) {
        Node x = select(root, n);
        if (x != null) {
            return x.key;
        }
        return null;
    }

    private Node select(Node x, int n) {
        if (x == null) {
            return null;
        }
        if (x.N == n) {
            return x;
        } else if (x.N > n) {
            return select(x.left, n);
        } else {
            return select(x.right, n - x.N - 1);
        }
    }

    public int rank(Key k) {
        return rank(root, k);
    }

    private int rank(Node x, Key k) {
        if (x == null) {
            return 0;
        }
        int cmp = x.key.compareTo(k);
        if (cmp == 0) {
            return size(x);
        } else if (cmp > 0) {
            return rank(x.left, k);
        } else {
            return 1 + size(x.left) + rank(x.right, k);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key k) {
        root = delete(root, k);
    }

    private Node delete(Node x, Key k) {
        if (x == null) {
            return null;
        }
        int cmp = x.key.compareTo(k);
        if (cmp > 0) {
            x.left = delete(x.left, k);
        } else if (cmp < 0) {
            x.right = delete(x.right, k);
        } else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = delete(t.left, k);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }
        int cmpLo = lo.compareTo(x.key);
        int cmpHi = hi.compareTo(x.key);
        if (cmpLo < 0)
            keys(x.left, queue, lo, hi);
        if (cmpLo <= 0 && cmpHi >= 0)
            queue.enqueue(x.key);
        if (cmpHi > 0)
            keys(x.right, queue, lo, hi);
    }

    public static void main(String[] args) {
        String[] a = { "E", "A", "S", "Y", "Q", "U", "E", "S", "T", "I", "O", "N" };
        MultiValueBST<String, Integer> st = new MultiValueBST<>();
        int i = 0;
        for (String s : a) {
            st.put(s, i);
            i++;
        }
        StdOut.println("keys:");
        for (String s : st.keys()) {
            StdOut.println(s);
        }
        StdOut.println("get E:" + st.get("E"));
        st.delete("E");
        StdOut.println("delete E:" + st.get("E"));
    }
}
