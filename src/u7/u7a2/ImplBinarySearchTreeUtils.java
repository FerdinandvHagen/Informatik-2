package u7.u7a2;

import java.util.ArrayList;

/**
 * Created by Ferdinand on 15.04.2015.
 */
public class ImplBinarySearchTreeUtils<T> implements IBinarySearchTreeUtils<T> {
    @Override
    public int height(BinarySearchTree<T> tree) {
        if (tree == null) {
            return 0;
        }

        return 1 + Math.max(height(tree.left), height(tree.right));
    }

    @Override
    public boolean isLeaf(BinarySearchTree<T> tree) {
        return tree == null ? true : tree.left == null && tree.right == null;
    }

    @Override
    public boolean hasOneChild(BinarySearchTree<T> tree) {
        return tree != null && tree.left == null ^ tree.right == null;
    }

    @Override
    public ArrayList<T> preOrder(BinarySearchTree<T> tree) {
        if (tree == null) {
            return new ArrayList<>();
        }

        ArrayList<T> list = new ArrayList<>();
        list.add(tree.thing);
        list.addAll(preOrder(tree.left));
        list.addAll(preOrder(tree.right));

        return list;
    }

    @Override
    public ArrayList<T> inOrder(BinarySearchTree<T> tree) {
        if (tree == null) {
            return new ArrayList<>();
        }

        ArrayList<T> list = new ArrayList<>();
        list.addAll(inOrder(tree.left));
        list.add(tree.thing);
        list.addAll(inOrder(tree.right));

        return list;
    }

    @Override
    public ArrayList<T> postOrder(BinarySearchTree<T> tree) {
        if (tree == null) {
            return new ArrayList<>();
        }

        ArrayList<T> list = new ArrayList<>();
        list.addAll(postOrder(tree.left));
        list.addAll(postOrder(tree.right));
        list.add(tree.thing);

        return list;
    }

    @Override
    public BinarySearchTree<T> insert(BinarySearchTree<T> tree, int key, T thing) {
        if (tree == null) {
            return new BinarySearchTree<T>(key, thing);
        }

        if (key > tree.key) {
            return new BinarySearchTree<T>(tree.key, tree.thing, tree.left, insert(tree.right, key, thing));
        } else if (key < tree.key) {

            return new BinarySearchTree<T>(tree.key, tree.thing, insert(tree.left, key, thing), tree.right);
        } else {
            //Keys equal -> replace
            return new BinarySearchTree<T>(tree.key, thing, tree.left, tree.right);
        }
    }

    @Override
    public T find(BinarySearchTree<T> tree, int key) {
        if (tree == null) {
            return null;
        }

        if (tree.key == key) {
            return tree.thing;
        }

        if (key > tree.key) {
            return find(tree.right, key);
        }

        return find(tree.left, key);
    }

    @Override
    public UnlinkSmallestResult<T> unlinkSmallest(BinarySearchTree<T> tree) {
        if (tree.left != null) {
            UnlinkSmallestResult result = unlinkSmallest(tree.left);
            return new UnlinkSmallestResult<>(result.smallest, new BinarySearchTree(tree.key, tree.thing, result.tree, tree.right));
        }

        return new UnlinkSmallestResult<>(tree, null);
    }

    @Override
    public BinarySearchTree<T> remove(BinarySearchTree<T> tree, int key) {
        if (tree == null) {
            return null;
        }

        if (key > tree.key) {
            return new BinarySearchTree<T>(tree.key, tree.thing, tree.left, remove(tree.right, key));
        } else if (key < tree.key) {
            return new BinarySearchTree<T>(tree.key, tree.thing, remove(tree.left, key), tree.right);
        }

        //Replace it!
        if(tree.right == null && tree.left == null) {
            return null;
        }

        if(tree.right == null) {
            return tree.left;
        }

        if(tree.left == null) {
            return tree.right;
        }

        UnlinkSmallestResult result = unlinkSmallest(tree.right);
        BinarySearchTree result1 = insert(result.smallest, result.tree);
        return insert(result1, tree.left);
    }

    private BinarySearchTree<T> insert(BinarySearchTree<T> smallest, BinarySearchTree tree) {
        if(tree == null) {
            return smallest;
        }

        if (smallest == null) {
            return tree;
        }

        if (tree.key > smallest.key) {
            return new BinarySearchTree<T>(smallest.key, smallest.thing, smallest.left, insert(smallest.right, tree));
        } else {
            return new BinarySearchTree<T>(smallest.key, smallest.thing, insert(smallest.left, tree), smallest.right);
        }
    }
}
