package chapter3.section3;

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
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        // 顺序不能乱
        if (isRed(x.right) && !isRed(x.left)) x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left)) x = rotateRight(x);
        if (isRed(x.right) && isRed(x.left)) flipColors(x);

        x.N = size(x.left) + size(x.right) + 1;
        return x;
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
        //Clear the node circle area
        StdDraw.filledCircle(node.xCoordinate, node.yCoordinate, nodeRadius);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.circle(node.xCoordinate, node.yCoordinate, nodeRadius);
        StdDraw.text(node.xCoordinate, node.yCoordinate, String.valueOf(node.key));

        drawNodes(node.right);
    }


    public static void main(String[] args) {
        RedBlackBST<String, Integer> st = new RedBlackBST<>();
        st.put("e", 1);
        st.put("a", 2);
        st.put("l", 3);
        st.put("h", 3);
        st.put("s", 3);
        st.put("e", 3);
        StdOut.println(st.get("a"));
        st.draw();
    }
}
