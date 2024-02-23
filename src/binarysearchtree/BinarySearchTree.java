package binarysearchtree;

import java.util.Random;

/**
 * BinarySearchTree class represents a binary search tree data structure.
 */
public class BinarySearchTree {
    /**
     * TreeNode class represents a node in the binary search tree.
     */
    public class TreeNode {
        public int data;
        public TreeNode leftChild, rightChild, parent;

        public TreeNode(int d) {
            data = d;
            leftChild = null;
            rightChild = null;
            parent = null;
        }

        public void setLeftChild(TreeNode n) {
            leftChild = n;
            if (n != null) {
                n.parent = this;
            }
        }

        public void setRightChild(TreeNode n) {
            rightChild = n;
            if (n != null) {
                n.parent = this;
            }
        }
    }

    private TreeNode root;

    /**
     * Find method searches for a node with the given value in the tree.
     *
     * @param findMe The value to find.
     * @return The node containing the value if found, otherwise null.
     */
    public TreeNode find(int findMe) {
        TreeNode n = root;
        while (n != null) {
            if (n.data == findMe)
                return n;
            if (n.data < findMe)
                n = n.rightChild;
            else
                n = n.leftChild;
        }
        return null;
    }

    /**
     * Output method prints the contents of the tree in ascending order.
     */
    public void output() {
        if (root != null) {
            output(root);
        }
    }

    private void output(TreeNode node) {
        if (node != null) {
            output(node.leftChild);
            System.out.print(node.data + " ");
            output(node.rightChild);
        }
    }

    /**
     * Insert method inserts the new value into the tree, respecting the order.
     *
     * @param value The value to insert.
     */
    public void insert(int value) {
        if (root == null) {
            root = new TreeNode(value);
        } else {
            insertBelow(root, value);
        }
    }

    private void insertBelow(TreeNode node, int value) {
        if (value > node.data) {
            if (node.rightChild == null)
                node.setRightChild(new TreeNode(value));
            else
                insertBelow(node.rightChild, value);
        } else {
            if (node.leftChild == null)
                node.setLeftChild(new TreeNode(value));
            else
                insertBelow(node.leftChild, value);
        }
    }

    /**
     * Remove method finds and removes the given value from the tree if it exists.
     *
     * @param value The value to remove.
     */
    public void remove(int value) {
        TreeNode node = find(value);
        if (node == null)
            return;
        if (node.leftChild == null) {
            replaceNode(node, node.rightChild);
        } else if (node.rightChild == null) {
            replaceNode(node, node.leftChild);
        } else {
            TreeNode successor = findMax(node.leftChild);
            node.data = successor.data;
            replaceNode(successor, successor.leftChild);
        }
    }

    private TreeNode findMax(TreeNode node) {
        while (node.rightChild != null) {
            node = node.rightChild;
        }
        return node;
    }

    private void replaceNode(TreeNode node, TreeNode replacement) {
        TreeNode parent = node.parent;
        if (parent == null) {
            root = replacement;
            if (root != null)
                root.parent = null;
        } else if (node == parent.leftChild)
            parent.setLeftChild(replacement);
        else
            parent.setRightChild(replacement);
    }

    /**
     * Generates an array of random values.
     *
     * @param howMany The number of values to generate.
     * @return An array of random values.
     */
    public static int[] randomValues(int howMany) {
        int[] result = new int[howMany];
        Random random = new Random();
        for (int i = 0; i < howMany; i++)
            result[i] = random.nextInt() % (10 * howMany);
        return result;
    }

    /**
     * Inserts all the values in the given array into the tree.
     *
     * @param values       The array of values to insert.
     * @param printResults Whether to print the tree after each insertion.
     */
    public void insertAll(int[] values, boolean printResults) {
        for (int value : values) {
            insert(value);
            if (printResults)
                output();
        }
    }

    /**
     * Removes all the values in the given array from the tree if present.
     *
     * @param values       The array of values to remove.
     * @param printResults Whether to print the tree after each removal.
     */
    public void removeAll(int[] values, boolean printResults) {
        for (int value : values) {
            remove(value);
            if (printResults)
                output();
        }
    }

    /**
     * The main method to demonstrate the functionality of the binary search tree.
     *
     * @param args The command line arguments (not used).
     */
    public static void main(String[] args) {
        // How many values to generate
        int numValues = 10;
        // Whether to print results. Only use with small numbers of values.
        boolean printResults = true;

        BinarySearchTree t = new BinarySearchTree();
        int[] a = randomValues(numValues);
        if (printResults) {
            System.out.print("Input values: ");
            for (int i = 0; i < numValues; i++)
                System.out.print(a[i] + " ");
            System.out.println();
        }
        long start = System.currentTimeMillis();
        t.insertAll(a, printResults);
        t.removeAll(a, printResults);
        long now = System.currentTimeMillis();
        double elapsed = (now - start) / 1000.0;
        System.out.println();
        System.out.println("Elapsed time = " + elapsed + " seconds");
    }
}
