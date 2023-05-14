public class AVLTree {
    private Node root;

    private class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }

    private Node insertRecursive(Node node, int value) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = insertRecursive(node.right, value);
        } else {
            // Nilai sudah ada dalam AVL Tree
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = balanceFactor(node);

        // Jika node tidak seimbang, lakukan rotasi sesuai kasus
        if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        }

        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        }

        if (balance > 1 && value > node.left.value) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && value < node.right.value) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void printPreorder() {
        printPreorderRecursive(root);
        System.out.println();
    }

    private void printPreorderRecursive(Node node) {
        if (node != null) {
            System.out.print(node.value + " ");
            printPreorderRecursive(node.left);
            printPreorderRecursive(node.right);
        }
    }

    public void printInorder() {
        printInorderRecursive(root);
        System.out.println();
    }

    private void printInorderRecursive(Node node) {
        if (node != null) {
            printInorderRecursive(node.left);
            System.out.print(node.value + " ");
            printInorderRecursive(node.right);
        }
    }

    public void printPostorder() {
        printPostorderRecursive(root);
        System.out.println();
    }

    private void printPostorderRecursive(Node node) {
        if (node != null) {
            printPostorderRecursive(node.left);
            printPostorderRecursive(node.right);
            System.out.print(node.value + " ");
        }
    }
}