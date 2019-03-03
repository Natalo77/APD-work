package genericMethods;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by u1661665 (Joshua Pritchard) on 10/11/2018.
 */
public class MaxTest {

    /**
     * A selection of tests for the Max class.
     */

    private Integer[] INTS = {10, 5, 8, 11};

    @Test
    void testCorrectValue()
    {
        Integer[] ints = {45, 12, 5, 23, 787, 4534, 12, 7567, 32, 98};

        if(Max.max(ints, 2, 7) != 7567)
        {
            Assertions.fail("Wrong value given.");
        }
    }

    @Test
    void testIndex1Lower()
    {
        if(Max.max(INTS, 0, 3) != 11)
        {
            Assertions.fail("Index 3 (11) not returned - index1 not recognised as smaller value");
        }
    }

    @Test
    void testIndex2Lower()
    {
        if(Max.max(INTS, 3, 0) != 11)
        {
            Assertions.fail("Index 3 (11) not returned - index2 not recognised as smaller value");
        }
    }

    @Test
    void testIndex1Negative()
    {
        try
        {
            int a = Max.max(INTS, -1, 3);
            Assertions.fail("Index1 negative not caught");
        }
        catch(IndexOutOfBoundsException e){}

    }

    @Test
    void testIndex2Negative()
    {
        try
        {
            int a = Max.max(INTS, 3, -1);
            Assertions.fail("Index2 negative not caught.");
        }
        catch(IndexOutOfBoundsException e){}

    }

    @Test
    void testIndex1GreaterThanArraySize()
    {
        try
        {
            int a = Max.max(INTS, 4, 0);
            Assertions.fail("Index1 greater than array size not caught.");
        }
        catch(IndexOutOfBoundsException e){}

    }

    @Test
    void testIndex2GreaterThanArraySize()
    {
        try
        {
            int a = Max.max(INTS, 0, 4);
            Assertions.fail("Index2 greater than array size not caught");
        }
        catch(IndexOutOfBoundsException e){}

    }

    @Test
    void testArraySize1Edge0()
    {
        if(Max.max(INTS, 0, 0) != 10)
        {
            Assertions.fail("Correct element not selected.");
        }
    }

    @Test
    void testArraySize1Edge3()
    {
        if(Max.max(INTS, 3, 3) != 11)
        {
            Assertions.fail("Correct element not selected.");
        }
    }

    @Test
    void testArraySize1NoEdge()
    {
        if(Max.max(INTS, 1, 1) != 5)
        {
            Assertions.fail("Correct element not selected.");
        }
    }

    @Test
    void testGenericStrings()
    {
        String[] strings = {"asdasdasd", "bbb", "ASDASDASDASDASDASD"};

        if(!("bbb".equals(Max.max(strings, 0, 2))))
        {
            Assertions.fail("Correct string not selected.");
        }
    }

    @Test
    void testGenericChars()
    {
        Character[] chars = {'a', 'c', 'b'};

        if(!('c' == Max.max(chars, 0, 2)))
        {
            Assertions.fail("Correct char not selected.");
        }
    }


}
