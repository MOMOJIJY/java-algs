package chapter3.section3;

public class SingleTopDownTree<Key extends Comparable, Value> {
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

    public void put(Key key, Value value) {
        if (key == null) {
            return;
        }

        if (value == null) {
            delete(key);
            return;
        }

        boolean isANewNode = !contains(key);

        Node godparentNode = null;
        Node grandparentNode = null; //Used to update references from grandparentNode after balancing its children
                                        //Only used to update bottom node on a rotate right operation
        Node parentNode = null; //Used to update references from parent after balancing its children

        Node currentNode = root;

        if (root == null) {
            root = new Node(key, value, 1, RED);
        } else {

            while (currentNode != null) {
                if (isANewNode) {
                    currentNode.size = currentNode.size + 1;
                }

                //Flip colors on the way down
                if (isRed(currentNode.left) && isRed(currentNode.right)) {
                    flipColors(currentNode);
                }

                //Check if parent needs to be balanced after color flip
                if (grandparentNode != null && isRed(grandparentNode.left) && isRed(grandparentNode.left.left)) {
                    grandparentNode = rotateRight(grandparentNode);
                    updateParentReference(godparentNode, grandparentNode);
                }

                //Balance on the way down
                if (isRed(currentNode.right) && !isRed(currentNode.left)) {
                    currentNode = rotateLeft(currentNode);
                    updateParentReference(parentNode, currentNode);
                }

                if (isRed(currentNode.left) && isRed(currentNode.left.left)) {
                    currentNode = rotateRight(currentNode);
                    updateParentReference(parentNode, currentNode);
                }

                int compare = key.compareTo(currentNode.key);

                if (compare < 0) {

                    if (currentNode.left == null) {
                        currentNode.left = new Node(key, value, 1, RED);
                        break;
                    }

                    godparentNode = grandparentNode;
                    grandparentNode = parentNode;
                    parentNode = currentNode;

                    currentNode = currentNode.left;
                } else if (compare > 0) {

                    if (currentNode.right == null) {
                        currentNode.right = new Node(key, value, 1, RED);
                        break;
                    }

                    godparentNode = grandparentNode;
                    grandparentNode = parentNode;
                    parentNode = currentNode;

                    currentNode = currentNode.right;
                } else {
                    currentNode.value = value;
                    break;
                }
            }
        }

        //Extra color flipping and balancing on the last node
        //currentNode is the new node's parent
        //parentNode is the new node's grandparent
        if (currentNode != null) {
            if (isRed(currentNode.left) && isRed(currentNode.right)) {
                flipColors(currentNode);
            }

            if (isRed(currentNode.right) && !isRed(currentNode.left)) {
                currentNode = rotateLeft(currentNode);
                updateParentReference(parentNode, currentNode);
            }

            if (parentNode != null && isRed(parentNode.left) && isRed(parentNode.left.left)) {
                parentNode = rotateRight(parentNode);
                updateParentReference(grandparentNode, parentNode);
            }
        }

        root.color = BLACK;
    }

    private void updateParentReference(Node parent, Node child) {
        if (parent == null) {
            root = child;
        } else {
            boolean isCurrentNodeLeftChild = child.key.compareTo(parent.key) < 0;

            if (isCurrentNodeLeftChild) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

}

