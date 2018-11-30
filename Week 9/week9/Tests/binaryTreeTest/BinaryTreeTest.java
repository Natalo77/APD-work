package binaryTreeTest;

import binaryTree.BTree;
import binaryTree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by u1661665(Joshua Pritchard) on 30/11/2018.
 * Version: 30/11/2018
 */
public class BinaryTreeTest
{
    /**
     * Test that a null tree is created correctly by using the isEmpty() and getValue() methods.
     */
    @Test
    void testNullTreeConstructor()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>();

        if(!intTree.isEmpty())
            fail("Binary tree has not been created empty.");

        try
        {
            int x = intTree.getValue();
            fail("Exception for root being null not caught.");
        }
        catch(NullPointerException e){}

        try
        {
            BTree<Integer> leftTree = intTree.getLeft();
            fail("Exception for trying to access left tree from null node not caught.");
        }
        catch(NullPointerException e){}

        try
        {
            BTree<Integer> rightTree = intTree.getRight();
            fail("Exception for trying to access right tree from null node not caught.");
        }
        catch(NullPointerException e){}
    }

    /**
     * Test that a single node tree is created correctly by using the isEmpty() and getValue() methods.
     */
    @Test
    void testRootTreeConstructor()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(1);

        if(intTree.isEmpty())
            fail("Binary tree created empty.");

        try
        {
            BTree<Integer> leftTree = intTree.getLeft();
        }
        catch (NullPointerException e)
        {
            fail("Exception thrown trying to access available left null subtree.");
        }

        try
        {
            BTree<Integer> rightTree = intTree.getRight();
        }
        catch(NullPointerException e)
        {
            fail("Exception thrown trying to access available right null subtree.");
        }

        try
        {
            assertTrue(1 == intTree.getValue(), "Root value not 1 as expected.");
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown against node holding a value.");
        }
    }

    /**
     * Test that a tree created with a left subtree and a null right subtree is created correctly using the
     * isEmpty(), getValue(), getLeft() and getRight() methods.
     */
    @Test
    void testRootAndLeftTreeConstructor()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(2, new BinaryTree<>(1), new BinaryTree<>());
        /*
         *         2
         *        / \
         *       1   null
         */

        if(intTree.isEmpty())
            fail("Binary tree created empty.");

        try
        {
            int x = intTree.getValue();
            if(x != 2)
                fail("Root value not 2 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown against node holding a value");
        }

        try
        {
            BTree<Integer> lTree = intTree.getLeft();
            int x = lTree.getValue();
            if(x != 1)
                fail("left subtree value not 1 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available subtree/its value.");
        }

        try
        {
            BTree<Integer> rTree = intTree.getRight();
            try
            {
                int x = rTree.getValue();
                fail("Exception for right root being null not caught.");
            }
            catch(NullPointerException e) {}
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available right null subtree.");
        }
    }

    /**
     * Test that a tree created with a right subtree and a null left subtree is created correctly using the
     * isEmpty(), getValue(), getLeft() and getRight() methods.
     */
    @Test
    void testRootAndRightTreeConstructor()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(2, new BinaryTree<>(), new BinaryTree<>(3));
        /*
         *        2
         *       / \
         *   null   3
         */

        if(intTree.isEmpty())
            fail("Binary tree created empty.");

        try
        {
            int x = intTree.getValue();
            if(x != 2)
                fail("Root value not 2 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown against node holding a value");
        }

        try
        {
            BTree<Integer> rTree = intTree.getRight();
            int x = rTree.getValue();
            if(x != 3)
                fail("Right subtree value not 3 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available subtree/its value.");
        }

        try
        {
            BTree<Integer> lTree = intTree.getLeft();
            try
            {
                int x = lTree.getValue();
                fail("Exception for left root being null not caught.");
            }
            catch(NullPointerException e) {}
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available left null subtree.");
        }
    }

    /**
     * Test that a tree created with a left subtree and a right subtree is created correctly using the
     * isEmpty(), getValue(), getLeft() and getRight() methods.
     */
    @Test
    void testRootAndBothTreeConstructor()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(2, new BinaryTree<>(1), new BinaryTree<>(3));
        /*
         *      2
         *     / \
         *    1   3
         */

        if(intTree.isEmpty())
            fail("Binary tree created empty.");

        try
        {
            int x = intTree.getValue();
            if(x != 2)
                fail("Root value not 2 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown against node holding a value");
        }

        try
        {
            BTree<Integer> rTree = intTree.getRight();
            int x = rTree.getValue();
            if(x != 3)
                fail("Right subtree value not 3 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available right subtree/its value.");
        }

        try
        {
            BTree<Integer> lTree = intTree.getLeft();
            int x = lTree.getValue();
            if(x != 1)
                fail("Left subtree value not 1 as expected.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown trying to access available left subtree/its value.");
        }
    }

    @Test
    void testInsertFullLeft()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(5, new BinaryTree<>(4), new BinaryTree<>(6));
        /*
         *      5
         *     / \
         *    4   6
         */

        intTree.insert(3);
        /*
         *      5
         *     / \
         *    4   6
         *   /
         *  3
         */

        try
        {
            if (intTree.getLeft().getLeft().getValue() != 3)
                fail("Left Left subtree of intTree not correctly set as 3.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for inserted accessible value.");
        }
    }

    @Test
    void testInsertLeftThenRight()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(10, new BinaryTree<>(5), new BinaryTree<>(15));
        /*
         *      10
         *     /  \
         *    5    15
         */

        intTree.insert(6);
        /*
         *      10
         *     /  \
         *    5    15
         *     \
         *      6
         */

        try
        {
            if (intTree.getLeft().getRight().getValue() != 6)
                fail("Left Right subtree of intTree not correctly set as 6.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for inserted accessible value.");
        }
    }

    @Test
    void testInsertRightThenLeft()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(10, new BinaryTree<>(5), new BinaryTree<>(15));
        /*
         *      10
         *     /  \
         *    5    15
         */

        intTree.insert(14);
        /*
         *      10
         *     /  \
         *    5    15
         *        /
         *       14
         */
        try
        {
            if (intTree.getRight().getLeft().getValue() != 14)
                fail("Right Left subtree of intTree not correctly set as 14.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for inserted accessible value.");
        }
    }

    @Test
    void testInsertFullRight()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(5, new BinaryTree<>(4), new BinaryTree<>(6));
        /*
         *      5
         *     / \
         *    4   6
         */

        intTree.insert(7);
        /*
         *      5
         *     / \
         *    4   6
         *         \
         *          7
         */
        try
        {
            if (intTree.getRight().getRight().getValue() != 7)
                fail("Right Right subtree of intTree not correctly set as 7.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for inserted accessible value.");
        }
    }

    @Test
    void testGetValue()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(2, new BinaryTree<>(1), new BinaryTree<>());
        /*
         *      2
         *     / \
         *    1   null
         */
        try
        {
            if (intTree.getValue() != 2)
                fail("getValue() not correctly returned 2.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for accessible value.");
        }

        try
        {
            if (intTree.getLeft().getValue() != 1)
                fail("getValue() not correctly returned 1.");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for accessible value.");
        }
    }

    @Test
    void testSetValue()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>();
        /*
         *    null
         */

        intTree.setValue(2);
        /*
         *      2
         *     / \
         * null   null
         */

        try
        {
            if (intTree.getValue() != 2)
                fail("2 not correctly set as intTree value");
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            fail("Exception thrown for accessible value.");
        }
    }

    @Test
    void testContains()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(10);
        intTree.insert(5);
        intTree.insert(2);
        intTree.insert(7);
        intTree.insert(15);
        intTree.insert(12);
        intTree.insert(17);

        /*
         *             10
         *           /    \
         *         5        15
         *       /  \      /   \
         *     2    7     12    17
         */

        if(!intTree.contains(2))
            fail("2 not found within tree.");
        if(!intTree.contains(7))
            fail("7 not found within tree.");
        if(!intTree.contains(5))
            fail("5 not found within tree.");
        if(!intTree.contains(10))
            fail("10 not found within tree.");
        if(!intTree.contains(15))
            fail("15 not found within tree.");
        if(!intTree.contains(12))
            fail("12 not found within tree.");
        if(!intTree.contains(17))
            fail("17 not found within tree.");

        if(intTree.contains(1))
            fail("1 found within tree");
    }

    /**
     * Output is known seeing as a predefined traversal method is being used.
     */
    @Test
    void testTraversal()
    {
        BinaryTree<Integer> intTree = new BinaryTree<>(10);
        intTree.insert(5);
        intTree.insert(2);
        intTree.insert(7);
        intTree.insert(15);
        intTree.insert(12);
        intTree.insert(17);

        /*
         *             10
         *           /    \
         *         5        15
         *       /  \      /   \
         *     2    7     12    17
         */

        ArrayList<Integer> expectedList = new ArrayList<>();
        expectedList.add(2);
        expectedList.add(5);
        expectedList.add(7);
        expectedList.add(10);
        expectedList.add(12);
        expectedList.add(15);
        expectedList.add(17);

        ArrayList<Integer> returnedList = (ArrayList<Integer>)intTree.traverse();

        for (int x = 0; x < 7; x++)
        {
            if(!Objects.equals(expectedList.get(x), returnedList.get(x)))
                fail("Discrepancy between expected and returned results at index: " + x);
        }
    }




}
