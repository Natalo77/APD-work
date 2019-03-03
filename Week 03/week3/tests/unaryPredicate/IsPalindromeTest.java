package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * A selection of tests testing the IsPalindrome class and its methods.
 */
class IsPalindromeTest
{
    IsPalindrome predicate = new IsPalindrome();

    @Test
    void testBasicPalindromes(){
        if(!predicate.test("mum"))
            fail("mum");
        if(!predicate.test("naan"))
            fail("naan");
        if(!predicate.test("radar"))
            fail("radar");
        if(!predicate.test("rotator"))
            fail("rotator");
    }

    @Test
    void testBasicNonPalindromes()
    {
        if(predicate.test("hvds"))
            fail("hvds");
        if(predicate.test("palindrome"))
            fail("palindrome");
        if(predicate.test("boi"))
            fail("boi");
        if(predicate.test("yes"))
            fail("yes");
    }

    @Test
    void testCasePalindromes()
    {
        if(!predicate.test("Malayalam"))
            fail("Malayalam");
        if(!predicate.test("RaCEcAr"))
            fail("RaCEcAr");
    }

    @Test
    void testPunctuation()
    {
        if(!predicate.test("Gods's dog"))
            fail("God's dog");
        if(!predicate.test("Able was I ere I saw Elba"))
            fail("Able was I ere I saw Elba");
        if(!predicate.test("A man, A plan, A canal - Panama!"))
            fail("A man, A plan, A canal, - Panama!");
    }

    @Test
    void testNumberPalindromesInArray()
    {
        String[] strings = {"naan", "Malayalam", "God's Dog", "nope"};
        if(predicate.numberSatisfying(strings) != 3)
            fail("Should be 3");
    }
}
