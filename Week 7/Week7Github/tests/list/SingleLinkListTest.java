package list;

/**
 * Created by u1661665(Joshua Pritchard) on 14/11/2018.
 * Version: 14/11/2018
 */

import linkedList.list.ListAccessError;
import linkedList.list.SingleLinkList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A selection of test methods to test the SingleLinkList class and its methods.
 */
public class SingleLinkListTest
{
    @Test
    void testCreateSize0()
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>();

        if(intList.getRoot() != null)
        {
            fail("Root is not null");
        }
        if(intList.getSize() != 0)
        {
            fail("Size is not 0");
        }
    }

    @Test
    void testCreateSize1()
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(2);

        if(intList.getRoot() == null)
        {
            fail("Root is null for some reason");
        }
        if(intList.getSize() != 1)
        {
            fail("Size of list is not 1.");
        }
    }

    @Test
    void testInitialNodeValue()
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);

        //List = {1}

        if(intList.getRoot().getValue() != 1)
        {
            fail("Root value is not 1 for some reason");
        }
    }

    @Test
    void testSizeWorks() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        intList.add(3, 4);

        //List = {1, 2, 3, 4}

        if(intList.getSize() != 4)
        {
            fail("Size is not 4");
        }

        intList.remove(0);
        //List = {2, 3, 4}
        if(intList.getSize() != 3)
        {
            fail("Size not correctly updated to 3");
        }

        intList.remove(1);
        //List = {2, 4}
        if(intList.getSize() != 2)
        {
            fail("Size not correctly updated to 2");
        }

        intList.remove(1);
        //List = {2}
        if(intList.getSize() != 1)
        {
            fail("Size not correctly updated to 1");
        }

        intList.remove(0);
        //List = {}
        if (intList.getSize() != 0)
        {
            fail("Size not correctly updated to 0");
        }
    }

    @Test
    void testAddIndexNegativeIndex() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        try
        {
            intList.add(-1, 1);
            fail("Index of -1 not caught.");
        }
        catch (ListAccessError e) {}
    }

    @Test
    void testAddIndexGreaterThanSize() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        try
        {
            intList.add(2, 1);
            fail("Index of 2 not caught");
        }
        catch(ListAccessError e) {}
    }

    @Test
    void testAddToEmptyListRootValue() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>();
        //List  = {}
        intList.add(0, 1);
        //List = {1}
        if(intList.getRoot().getValue() != 1)
        {
            fail("Root value is not 1 for some reason.");
        }
    }

    @Test
    void testAddToListSize1AtRoot() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        //List = {1}
        intList.add(0, 2);
        //List = {2, 1}
        if(intList.get(0) != 2)
        {
            fail("Root value is not 2");
        }
        if(intList.get(1) != 1)
        {
            fail("Root value of 1 was not moved up to index 1");
        }
    }

    @Test
    void testAddToListSize1AfterRoot() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        //List = {1}
        intList.add(1, 2);
        //List = {1, 2}
        if(intList.get(0) != 1)
        {
            fail("Root value is no longer 1");
        }
        if(intList.get(1) != 2)
        {
            fail("Value of 2 not correctly added to index 1");
        }
    }

    @Test
    void testAddToGenericInternalIndex() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}
        if(intList.get(0) != 1 || intList.get(1) != 2 || intList.get(2) != 3)
        {
            fail("List not set up correctly for test.");
        }

        intList.add(1, 10);
        //List = {1, 10, 2, 3}
        if(intList.get(0) != 1)
        {
            fail("Root node is no longer 1");
        }

        if(intList.get(1) != 10)
        {
            fail("10 not added to index 1");
        }
        if(intList.get(2) != 2)
        {
            fail("2 not correctly moved to index 2");
        }
        if(intList.get(3) != 3)
        {
            fail("3 not correctly moved to index 3");
        }

        if(intList.getSize() != 4)
        {
            fail("Size not correctly updated to 4.");
        }
    }

    @Test
    void testAddToEndOfList() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}
        if(intList.get(0) != 1 || intList.get(1) != 2 || intList.get(2) != 3)
        {
            fail("List not set up correctly for test.");
        }

        intList.add(3, 10);
        //List = {1, 2, 3, 10}

        if(intList.get(2) != 3)
        {
            fail("3 no longer at correct index of 2");
        }
        if(intList.get(3) != 10)
        {
            fail("10 not added to end of list correctly.");
        }

        if(intList.getSize() != 4)
        {
            fail("Size not correctly updated to 4.");
        }
    }

    @Test
    void testRemoveIndexNegativeIndex() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        try
        {
            intList.remove(-1);
            fail("Index of -1 not caught.");
        }
        catch (ListAccessError e) {}
    }

    @Test
    void testRemoveIndexEqualToSize() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        try
        {
            intList.remove(1);
            fail("Index of 1 not caught");
        }
        catch(ListAccessError e) {}
    }

    @Test
    void testRemoveIndexGreaterThanSize() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        try
        {
            intList.remove(2);
            fail("Index of 2 not caught");
        }
        catch(ListAccessError e) {}
    }

    @Test
    void testRemoveFromListSize1AtRoot() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        //List = {1}
        int removed = intList.remove(0);
        //List = {}
        if(removed != 1)
        {
            fail("Removed element value 1 not correctly returned.");
        }
        if(intList.getRoot() != null)
        {
            fail("root not removed correctly");
        }
    }

    @Test
    void testRemoveFromGenericInternalIndex() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}
        if(intList.get(0) != 1 || intList.get(1) != 2 || intList.get(2) != 3)
        {
            fail("List not set up correctly for test.");
        }

        int removed = intList.remove(1);
        //List = {1, 3}
        if(removed != 2)
        {
            fail("Removed element value 2 not correctly returned");
        }
        if(intList.get(0) != 1)
        {
            fail("Root node is no longer 1");
        }

        if(intList.get(1) != 3)
        {
            fail("3 not correctly moved down to index 1");
        }

        if(intList.getSize() != 2)
        {
            fail("Size not correctly updated to 2.");
        }
    }

    @Test
    void testRemoveFromEndOfList() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}
        if(intList.get(0) != 1 || intList.get(1) != 2 || intList.get(2) != 3)
        {
            fail("List not set up correctly for test.");
        }

        int removed = intList.remove(2);
        //List = {1, 2}

        if(removed != 3)
        {
            fail("Removed element value 3 not correctly returned.");
        }

        if(intList.get(1) != 2)
        {
            fail("2 no longer at correct index of 1");
        }

        if(intList.getSize() != 2)
        {
            fail("Size not correctly updated to 2.");
        }
    }

    @Test
    void testGetRoot() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}

        if(intList.get(0) != 1)
        {
            fail("Root element value 1 not correctly returned.");
        }
    }

    @Test
    void testGetGenericInternalIndex() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}

        if(intList.get(1) != 2)
        {
            fail("Element value 2 not correctly returned");
        }
    }

    @Test
    void testGetEndElement() throws ListAccessError
    {
        SingleLinkList<Integer> intList = new SingleLinkList<>(1);
        intList.add(1, 2);
        intList.add(2, 3);
        //List = {1, 2, 3}

        if(intList.get(2) != 3)
        {
            fail("End element value 3 not correctly returned.");
        }
    }

    @Test
    void testGenericCharList() throws ListAccessError
    {
        SingleLinkList<Character> charList = new SingleLinkList<>('a');
        charList.add(1, 'b');
        charList.add(2, 'c');
        //List = {a, b, c}

        if(charList.getSize() != 3)
        {
            fail("Size of 3 not correctly returned.");
        }

        charList.add(0, 'z');
        //List = {z, a, b, c}
        if(charList.getSize() != 4)
        {
            fail("list size not correctly updated to 4");
        }
        if(charList.get(0) != 'z')
        {
            fail("Root element not correctly changed to z");
        }
        if(charList.get(1) != 'a')
        {
            fail("root element a not correctly moved to index 1");
        }

        char removed = charList.remove(2);
        //List = {z, a, c}
        if(charList.getSize() != 3)
        {
            fail("List size not correctly updated to 3");
        }
        if(charList.get(1) != 'a')
        {
            fail("Previous element no longer a");
        }
        if(charList.get(2) != 'c')
        {
            fail("Next element no longer c");
        }
        if(removed != 'b')
        {
            fail("Removed element value b not correctly returned.");
        }
    }

    @Test
    void testGenericStringList() throws ListAccessError
    {
        SingleLinkList<String> stringList = new SingleLinkList<>("aaa");
        stringList.add(1, "bbb");
        stringList.add(2, "ccc");
        //List = {aaa, bbb, ccc}

        if(stringList.getSize() != 3)
        {
            fail("Size of 3 not correctly returned.");
        }

        stringList.add(0, "zzz");
        //List = {zzz, aaa, bbb, ccc}
        if(stringList.getSize() != 4)
        {
            fail("list size not correctly updated to 4");
        }
        if(!(stringList.get(0).equals("zzz")))
        {
            fail("Root element not correctly changed to zzz");
        }
        if(!(stringList.get(1).equals("aaa")))
        {
            fail("root element aaa not correctly moved to index 1");
        }

        String removed = stringList.remove(2);
        //List = {zzz, aaa, ccc}
        if(stringList.getSize() != 3)
        {
            fail("List size not correctly updated to 3");
        }
        if(!(stringList.get(1).equals("aaa")))
        {
            fail("Previous element no longer aaa");
        }
        if(!(stringList.get(2).equals("ccc")))
        {
            fail("Next element no longer ccc");
        }
        if(!(removed.equals("bbb")))        {
            fail("Removed element value bbb not correctly returned.");
        }
    }

}
