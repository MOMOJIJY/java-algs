package chapter3.section3;

import edu.princeton.cs.algs4.StdOut;

public class AVL<Key extends Comparable<Key>, Value> {
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;
        private int height;

        public Node(Key key, Value val, int n, int h) {
            this.key = key;
            this.val = val;
            this.size = n;
            this.height = h;
        }
    }

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
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

    private Node rotateLeft(Node x) {
        Node t = x.right;
        x.right= t.left;
        t.left = x;
        // 顺序不能倒
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        t.height = Math.max(height(t.right), height(t.left)) + 1;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
 
    }

    private Node rotateRight(Node x) {
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        // 顺序不能倒
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        t.height = Math.max(height(t.right), height(t.left)) + 1;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return x.height;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    public Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1, 0);
        }
        
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
            return x;
        }

        x.height = 1 + Math.max(height(x.left), height(x.right));
        x.size = 1 + size(x.left) + size(x.right);

        if (balanceFactor(x) > 1) {
            // 左子树比右子树高大于1
            if (balanceFactor(x.left) < 0) {
                // 避免旋转后右子树太高，可以画图思考一下
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        } else if (balanceFactor(x) < -1) {
            // 右子树比左子树高大于1
            if (balanceFactor(x.right) < 0) {
                // 避免旋转后左子树太高，可以画图思考一下
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        return x;
    }

    private int balanceFactor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

    private boolean isAVL() {
            return isAVL(root);
        }

    private boolean isAVL(Node node) {
        if (node == null) {
            return true;
        }

        int balanceFactor = balanceFactor(node);
        if (balanceFactor < -1 || balanceFactor > 1) {
            return false;
        }

        return isAVL(node.left) && isAVL(node.right);
    }

    private boolean isSubtreeCountConsistent() {
        return isSubtreeCountConsistent(root);
    }

    private boolean isSubtreeCountConsistent(Node node) {
        if (node == null) {
            return true;
        }

        int totalSubtreeCount = 0;
        if (node.left != null) {
            totalSubtreeCount += node.left.size;
        }
        if (node.right != null) {
            totalSubtreeCount += node.right.size;
        }

        if (node.size != totalSubtreeCount + 1) {
            return false;
        }

        return isSubtreeCountConsistent(node.left) && isSubtreeCountConsistent(node.right);
    }

    public static void main(String[] args) {
        AVL<Integer, Integer> avlTree = new AVL<>();

        avlTree.put(5, 5);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(1, 1);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(9, 9);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(2, 2);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(0, 0);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(99, 99);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(-1, -1);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(-2, -2);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(3, 3);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");
        avlTree.put(-5, -5);
        StdOut.println("Is AVL: " + avlTree.isAVL() + " Expected: true");

        StdOut.println("Size consistent: " + avlTree.isSubtreeCountConsistent() + " Expected: true\n");

    }
}
