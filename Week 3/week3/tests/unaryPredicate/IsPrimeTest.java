package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

/**
 * A selection of tests testing the IsPrime Class and its methods.
 */
class IsPrimeTest
{
    private IsPrime predicate = new IsPrime();

    @Test
    void testZero()
    {
        assertFalse(predicate.test(0));
    }

    @Test
    void testOne()
    {
        assertFalse(predicate.test(1));
    }

    @Test
    void testTwo()
    {
        assertFalse(predicate.test(2));
    }

    @Test
    void testThree()
    {
        assertTrue(predicate.test(3));
    }

    @Test
    void testFour()
    {
        assertFalse(predicate.test(4));
    }

    @Test
    void testFive(){
        assertTrue(predicate.test(5));
    }

    @Test
    void testSix(){
        assertFalse(predicate.test(6));
    }

    @Test
    void testSeven(){
        assertTrue(predicate.test(7));
    }

    @Test
    void testNine(){
        assertFalse(predicate.test(9));
    }

    @Test
    void testEleven(){
        assertTrue(predicate.test(11));
    }

    @Test
    void testTwelve(){
        assertFalse(predicate.test(12));
    }

    @Test
    void testFifteen(){
        assertFalse(predicate.test(15));
    }

    @Test
    void testMinusOne(){
        assertFalse(predicate.test(-1));
    }

    @Test
    void testMinusTwo(){
        assertFalse(predicate.test(-2));
    }

    @Test
    void testMinusEleven(){
        assertTrue(predicate.test(-11));
    }

    @Test
    void testNumberPrimeInArray()
    {
        Integer[] ints = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        if(predicate.numberSatisfying(ints) != 3)
            fail("Should be 3");
    }

}
