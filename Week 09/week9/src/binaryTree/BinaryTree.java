package binaryTree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree<T extends Comparable<? super T>> implements BTree<T> {

    private TreeNode<T> root; // the root node

    /**
     * Construct an empty tree.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Construct a singleton tree.
     * A singleton tree contains a value in the root, but the left and right subtrees are
     * empty.
     * @param value the value to be stored in the tree.
     */
    public BinaryTree(T value) {
        root = new TreeNode(value);
    }

    /**
     * Construct a tree with a root value, and left and right subtrees.
     * @param value the value to be stored in the root of the tree.
     * @param left the tree's left subtree.
     * @param right the tree's right subtree.
     */
    public BinaryTree(T value,BinaryTree<T> left,BinaryTree<T> right) {
        root = new TreeNode(value,left,right);
    }

    /**
     * Check if the tree is empty.
     * @return true iff the tree is empty.
     */
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Insert a new value in the binary tree at a position determined by the current contents
     * of the tree, and by the ordering on the type T.
     * @param value the value to be inserted into the tree.
     */
    @Override
    public void insert(T value) {
        if(isEmpty())
        {
            root = new TreeNode<T>(value, new BinaryTree<>(), new BinaryTree<>());
            return;
        }

        if(value.compareTo(this.getValue()) < 0)
        {
            root.getLeft().insert(value);
        }
        else
        {
            root.getRight().insert(value);
        }
        // implement insert(T value) here
    }

    /**
     * Get the value stored at the root of the tree.
     * @return the value stored at the root of the tree.
     */
    @Override
    public T getValue() throws NullPointerException {
        // Note: it might make sense to define getValue() to throw a (custom) exception if an attempt
        // is made to access a value from an empty tree.
        // However, since a tree is empty iff it's root node is null, it is also acceptable to rely
        // on Java's NullPointerException.
        // This comment also applies to the other get and set methods defined in this interface.

        // placeholder return value below - replace with implementation of getValue()

        if(isEmpty())
        {
            throw new NullPointerException("Tree at current node is empty.");
        }
        else
        {
            return root.value;
        }
    }

    /**
     * Change the value stored at the root of the tree.
     * @param value the new value to be stored at the root of the tree.
     */
    @Override
    public void setValue(T value) {
        // implement setValue(T value) here
        if(isEmpty())
            root = new TreeNode<T>(value, new BinaryTree<>(), new BinaryTree<>());
        else
            root.value = value;
    }

    /**
     * Get the left subtree of this tree.
     * @return  This tree's left subtree.
     */
    @Override
    public BTree<T> getLeft() throws NullPointerException {
        // placeholder return value below - replace with implementation of getLeft()
        if(isEmpty())
        {
            throw new NullPointerException("Current node is empty.");
        }
        return root.left;
    }

    /**
     * Change the left subtree of this tree.
     * @param tree the new left subtree.
     */
    @Override
    public void setLeft(BTree<T> tree) {
        // implement setLeft(BTree<T> tree) here
        root.left = tree;
    }

    /**
     * Get the right subtree of this tree.
     * @return this tree's right subtree.
     */
    @Override
    public BTree<T> getRight() throws NullPointerException {
        // placeholder return value below - replace with implementation of getRight()
        if(isEmpty())
        {
            throw new NullPointerException("Current node is empty.");
        }
        return root.right;
    }

    /**
     * Change the right subtree of this tree.
     * @param tree the new right subtree.
     */
    @Override
    public void setRight(BTree<T> tree) {
        // implement setRight(BTree<T> tree) here
        root.right = tree;
    }

    /**
     * Check if the tree contains a given value.
     * @param value the value to be checked.
     * @return true iff the value is in the tree.
     */
    @Override
    public boolean contains(T value) {
        // placeholder return value below - replace with implementation of contains(T value)
        //Terminate this branch of the recursion if the current node is empty.
        if(!isEmpty())
        {
            //If the value is found, return it.
            if (value.equals(getValue()))
            {
                return true;
            }
            //Else search the left subtree if less than the current node.
            else if (value.compareTo(root.getValue()) < 0)
            {
                return root.getLeft().contains(value);
            }
            //Or search the right subtree if greater than the current node.
            else if (value.compareTo(root.getValue()) > 0)
            {
                return root.getRight().contains(value);
            }
        }
        return false;
    }

    /**
     * Traverse the tree, producing a list of all the values contained in the tree.
     * This is an implementation of an inOrder traversal.
     * @return a list of all the values in the tree.
     */
    @Override
    public List<T> traverse() {
        // placeholder return value below - replace with implementation of traverse()

        //Create a new arrayList to store the values.
        ArrayList<T> list = new ArrayList<>();

        //Call inorder traverse with this tree and the created list.
        inOrderTraverse(this, list);

        //Return the populated list.
        return list;
    }

    private void inOrderTraverse(BTree<T> tree, List<T> list)
    {
        //Terminate this branch of the recursion if the subtree is empty.
        if(tree.isEmpty())
        {
            return;
        }

        //recursively search the left subtree
        inOrderTraverse(tree.getLeft(), list);

        //add the current value.
        list.add(tree.getValue());

        //And recursively search the right subtree.
        inOrderTraverse(tree.getRight(), list);
    }
}

