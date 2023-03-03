package chapter3.section3;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;
        private boolean color;

        private double xCoordinate, yCoordinate;

        public Node(Key k, Value v, int N, boolean color) {
            this.key = k;
            this.val = v;
            this.N = N;
            this.color = color;
        }
    }

    private Node root;
    private int treeLevel;

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

    public boolean contains(Key key) {
        if (get(key) != null) {
            return true;
        }
        return false;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, RED);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;

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

    public boolean isEmpty() {
        return size(root) == 0;
    }

    private void flipColors2(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h) {
        flipColors2(h);
        if (h.right != null && isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
        }
        return h;
    }

    protected Node moveRedRight(Node node) {
        // Assuming that node is red and both node.right and node.right.left are black,
        // make node.right or one of its children red
        flipColors(node);

        if (node.left != null && isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }

        return node;
    }

    public void deleteMin() {
        if (isEmpty()) {
            return;
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);

        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }

        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return balance(node);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        if (isEmpty() || !contains(key)) {
            return;
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = delete(root, key);

        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            if (!isRed(node.left) && node.left != null && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }

            node.left = delete(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
            }

            if (key.compareTo(node.key) == 0 && node.right == null) {
                return null;
            }

            if (!isRed(node.right) && node.right != null && !isRed(node.right.left)) {
                node = moveRedRight(node);
            }

            if (key.compareTo(node.key) == 0) {
                Node aux = min(node.right);
                node.key = aux.key;
                node.val = aux.val;
                node.right = deleteMin(node.right);
            } else {
                node.right = delete(node.right, key);
            }
        }

        return balance(node);
    }

    public Node balance(Node x) {
        if (x == null) {
            return null;
        }
        // 顺序不能乱
        if (isRed(x.right)) {
            x = rotateLeft(x);
        }
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.right) && isRed(x.left))
            flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min() {
        if (root == null) {
            return null;
        }

        return min(root).key;
    }

    protected Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    public Key max() {
        if (root == null) {
            return null;
        }

        return max(root).key;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }

        return max(node.right);
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

    public void draw() {
        treeLevel = 0;
        setCoordinates(root, 0.9);

        StdDraw.setPenColor(StdDraw.BLACK);
        drawLines(root);
        drawNodes(root);
    }

    private void setCoordinates(Node node, double distance) {
        if (node == null) {
            return;
        }

        setCoordinates(node.left, distance - 0.05);
        node.xCoordinate = (0.5 + treeLevel++) / size();
        node.yCoordinate = distance - 0.05;
        setCoordinates(node.right, distance - 0.05);
    }

    private void drawLines(Node node) {
        if (node == null) {
            return;
        }

        drawLines(node.left);

        if (node.left != null) {
            if (node.left.color == RED) {
                setPenToWriteRedLine();
            }

            StdDraw.line(node.xCoordinate, node.yCoordinate, node.left.xCoordinate, node.left.yCoordinate);
            resetPen();
        }
        if (node.right != null) {
            if (node.right.color == RED) {
                setPenToWriteRedLine();
            }

            StdDraw.line(node.xCoordinate, node.yCoordinate, node.right.xCoordinate, node.right.yCoordinate);
            resetPen();
        }

        drawLines(node.right);
    }

    private void setPenToWriteRedLine() {
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.007);
    }

    private void resetPen() {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.0025);
    }

    private void drawNodes(Node node) {
        if (node == null) {
            return;
        }

        double nodeRadius = 0.032;

        drawNodes(node.left);

        StdDraw.setPenColor(StdDraw.WHITE);
        // Clear the node circle area
        StdDraw.filledCircle(node.xCoordinate, node.yCoordinate, nodeRadius);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(node.xCoordinate, node.yCoordinate, nodeRadius);
        StdDraw.text(node.xCoordinate, node.yCoordinate, String.valueOf(node.key));

        drawNodes(node.right);
    }

    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(x);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur.left != null) {
                if (isRed(cur) && isRed(cur.left))
                    return false;
                stack.push(cur.left);
            }
            if (cur.right != null) {
                if (isRed(cur.right))
                    return false;
                stack.push(cur.right);
            }
        }
        return true;
    }

    public boolean isBalance() {
        int blackPath = 0;
        Node cur = root;
        while (cur != null) {
            cur = cur.left;
            if (!isRed(cur))
                blackPath += 1;
        }
        return isBalance(root, blackPath);
    }

    private boolean isBalance(Node x, int blackPath) {
        if (x == null) {
            return blackPath == 0;
        }

        if (!isRed(x)) {
            blackPath -= 1;
        }
        return isBalance(x.left, blackPath) && isBalance(x.right, blackPath);
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    private boolean isBST(Node node, Comparable low, Comparable high) {
        if (node == null) {
            return true;
        }

        if (low != null && low.compareTo(node.key) >= 0) {
            return false;
        }
        if (high != null && high.compareTo(node.key) <= 0) {
            return false;
        }

        return isBST(node.left, low, node.key) && isBST(node.right, node.key, high);
    }

    public boolean isRedBlackBST() {
        return isBST() && is23() && isBalance();
    }

    public static void main(String[] args) {
        // draw test
        // RedBlackBST<String, Integer> st = new RedBlackBST<>();
        // st.put("e", 1);
        // st.put("a", 2);
        // st.put("l", 3);
        // st.put("h", 3);
        // st.put("s", 3);
        // st.put("e", 3);
        // StdOut.println(st.get("a"));
        // st.draw();

        // certification
        // RedBlackBST<Integer, String> st = new RedBlackBST<>();
        // st.root = new RedBlackBST().new Node(10, "Value 10", 7, false);
        // st.root.left = new RedBlackBST().new Node(5, "Value 5", 4, true);
        // st.root.left.left = new RedBlackBST().new Node(2, "Value 2", 1, false);
        // st.root.left.right = new RedBlackBST().new Node(9, "Value 9", 2, false);
        // st.root.left.right.left = new RedBlackBST().new Node(7, "Value 7", 1, true);

        // st.root.right = new RedBlackBST().new Node(14, "Value 14", 2, false);
        // st.root.right.left = new RedBlackBST().new Node(11, "Value 11", 1, true);

        // StdOut.println("Test 1");
        // StdOut.println(st.is23() + " Expected: true");
        // StdOut.println(st.isBalance() + " Expected: true");

        // test deleteMin()
        // RedBlackBST<Integer, String> st = new RedBlackBST<>();
        // st.put(7, "a");
        // st.put(5, "a");
        // st.put(11, "a");
        // st.put(4, "a");
        // st.put(6, "a");
        // st.put(9, "a");
        // st.put(12, "a");
        // st.put(8, "a");
        // st.put(10, "a");
        // StdOut.println(st.isRedBlackBST());
        // st.deleteMin();
        // StdOut.println(st.isRedBlackBST());
        // st.draw();

        RedBlackBST<Integer, String> st = new RedBlackBST<>();
        st.put(7, "a");
        st.put(5, "a");
        st.put(11, "a");
        st.put(4, "a");
        st.put(6, "a");
        st.put(9, "a");
        st.put(12, "a");
        st.put(8, "a");
        st.put(10, "a");
        for (int k : st.keys()) {
            StdOut.printf("key=%s, value=%s\n", k, st.get(k));
        }
    }
}
