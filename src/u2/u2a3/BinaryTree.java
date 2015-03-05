package u2.u2a3;

import java.util.Arrays;

public class BinaryTree {
    private char[] tree;

    /**
     * Determines the index of the father.
     * <p>
     * The root is its own father.
     *
     * @param node index of the children
     * @return index of the father
     */
    public static int father(int node) {
        return (node - 1) / 2;
    }

    /**
     * Determines the index of the left child.
     *
     * @param node index of the father
     * @return index of the left child
     */
    public static int leftChild(int node) {
        return (node + 1) * 2 - 1;
    }

    /**
     * Determines the index of the right child.
     *
     * @param node index of the father
     * @return index of the right child
     */
    public static int rightChild(int node) {
        return (node + 1) * 2;
    }

    /**
     * Check if the given array represents a valid binary tree.
     *
     * @param array a binary tree encoded as char array
     * @throws IllegalArgumentException if check fails
     */
    private static void checkTree(char[] array) {
        char[] myArray = Arrays.copyOf(array, array.length);

        checkTree(myArray, 0);

        for (char c : myArray) {
            if (c != ' ') {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void checkTree(char[] array, int pos) {
        if (!(pos < array.length)) {
            return;
        }

        if (array[pos] == ' ') {
            return;
        }

        if (rightChild(pos) < array.length) {
            //Check children
            checkTree(array, rightChild(pos));
        }

        if (leftChild(pos) < array.length) {
            checkTree(array, leftChild(pos));
        }
        
        array[pos] = ' ';
    }

    /**
     * Create a new binary tree from the given array representation.
     * <p>
     * The array stores the values of the binary tree in breadth-first order.
     * A space encodes a missing node.
     *
     * @param array the new tree
     * @throws IllegalArgumentException if array does not represent a valid binary tree.
     */
    public BinaryTree(char[] array) {
        checkTree(array);
        this.tree = array;
    }

    /**
     * convert {@link u2.u2a3.BinaryTree#tree} into indented form.
     * <p>
     * One line per node, depth-first. One space of indentation per depth.
     */
    public String toString() {
        return output(0, 0);
    }

    private String output(int pos, int depth) {
        if (!(pos < tree.length)) {
            throw new IllegalArgumentException();
        }

        if (tree[pos] == ' ') {
            return "";
        }

        String s = "";

        if (depth != 0) {
            s += String.format("%" + depth + "s%s\n", "", tree[pos]);
        } else {
            s += String.format("%s\n", tree[pos]);
        }

        if (leftChild(pos) < tree.length) {
            s += output(leftChild(pos), depth + 1);
        }

        if (rightChild(pos) < tree.length) {
            s += output(rightChild(pos), depth + 1);
        }
        
        return s;
    }
}
