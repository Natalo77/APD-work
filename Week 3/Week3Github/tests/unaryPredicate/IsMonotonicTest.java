package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;


/**
 * A selection of tests testing IsMonotonic and its methods.
 */
class IsMonotonicTest
{
    IsMonotonic predicate = new IsMonotonic();

    @Test
    void testPositiveMonotonic(){
        Integer[] ints = {1, 2, 3, 4, 5};
        if(!predicate.test(ints))
            fail("Ints array 1, 2, 3, 4, 5");

        String[] strings = {"aaa", "bbb", "ccc", "ddd", "eee"};
        if(!predicate.test(strings))
            fail("String array aaa, bbb, ccc, ddd, eee");

        Character[] chars = {'a', 'b', 'c', 'd', 'e'};
        if(!predicate.test(chars))
            fail("Chars array a, b, c, d, e");
    }

    @Test
    void testNegativeMonotonic(){
        Integer[] ints = {5, 4, 3, 2, 1};
        if(!predicate.test(ints))
            fail("Ints array 5, 4, 3, 2, 1");

        String[] strings = {"eee", "ddd", "ccc", "bbb", "aaa"};
        if(!predicate.test(strings))
            fail("String array eee, ddd, ccc, bbb, aaa");

        Character[] chars = {'e', 'd', 'c', 'b', 'a'};
        if(!predicate.test(chars))
            fail("Chars array e, d, c, b, a");
    }

    @Test
    void testNonMonotonic(){
        Integer[] ints = {5, 4, 5, 2, 1};
        if(predicate.test(ints))
            fail("Ints array 5, 4, 5, 2, 1");

        String[] strings = {"eee", "ddd", "eee", "bbb", "aaa"};
        if(predicate.test(strings))
            fail("String array eee, ddd, eee, bbb, aaa");

        Character[] chars = {'a', 'b', 'a', 'd', 'e'};
        if(predicate.test(chars))
            fail("Chars array a, b, a, d, e");
    }

    @Test
    void testPlateau(){
        Integer[] ints = {5, 4, 4, 2, 1};
        if(predicate.test(ints))
            fail("Ints array 5, 4, 4, 2, 1");

        String[] strings = {"eee", "ddd", "ddd", "bbb", "aaa"};
        if(predicate.test(strings))
            fail("String array eee, ddd, ddd, bbb, aaa");

        Character[] chars = {'a', 'b', 'b', 'd', 'e'};
        if(predicate.test(chars))
            fail("Chars array a, b, b, d, e");
    }

    @Test
    void testNumberMonotonicInArray()
    {
        Integer[][] ints = new Integer[3][5];
        ints[0] = new Integer[]{1, 2, 3, 4, 5};
        ints[1] = new Integer[]{1, 1, 1, 1, 1};
        ints[2] = new Integer[]{1, 2, 3, 4, 5};

        if(predicate.numberSatisfying(ints) != 2)
            fail("Should be 2");
    }
}
