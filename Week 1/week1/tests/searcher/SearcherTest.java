package searcher;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CleverRandomListingGenerator;


import java.util.Random;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * @author Hugh Osborne
 * @version September 2018
 */
abstract class SearcherTest {

    //Used to control the number of randomly generated tests ran.
    public static final int NUM_RANDOM_TESTS = 20;

    /**
     * Create a searcher of the right type
     */
    abstract protected Searcher createSearcher(int[] array, int index) throws IndexingError;

    /**
     * Test that the searcher finds the corretc value.  The test uses a random listing generator to create
     * a random listing of the requited size.  Because of the properties of random listings, the kth largest
     * element of a random listing of size n must be n-k.
     * @param arraySize the size of the random listing to be generated (the "n" value)
     * @param index the index (the "k" value)
     * @throws IndexingError if k is out of bounds for n
     */
    protected void testSearcher(int arraySize,int index) throws IndexingError {
        ArrayGenerator generator = new CleverRandomListingGenerator(arraySize);
        Searcher search = createSearcher(generator.getArray(), index);
        assertEquals(arraySize - index, search.findElement());
    }

    /**
     * Test that a searcher throws an indexing error when an invalid index error is passed.
     * The test uses a random listing generator to create a random listing of the required size.
     *
     * @param arraySize the size of the random listing to be generated.
     * @param index the index (Should be
     * @throws IndexingError should be thrown when an invalid index value is passed in relative to arraySize
     */
    protected void testIndexError(int arraySize, int index) throws IndexingError
    {
        if(index < 1 || index > arraySize)
        {
            ArrayGenerator generator = new CleverRandomListingGenerator(arraySize);
            Searcher search = createSearcher(generator.getArray(), index);
            assertThrows(IndexingError.class, search::findElement);
        }
        else
            fail("Expected an invalid index. re-evaluate test");
    }

    @org.junit.jupiter.api.Test
    void test2ndIn10() throws IndexingError {
        testSearcher(10,2);
    }

    @org.junit.jupiter.api.Test
    void test5thIn10() throws IndexingError {
        testSearcher(10,5);
    }
    @org.junit.jupiter.api.Test
    void test3rdIn100() throws IndexingError {
        testSearcher(100,3);
    }

    @org.junit.jupiter.api.Test
    void test16thIn100() throws IndexingError {
        testSearcher(100,16);
    }

    @org.junit.jupiter.api.Test
    void test8thIn1000() throws IndexingError {
        testSearcher(1000,8);
    }

    @org.junit.jupiter.api.Test
     void test107thIn1000() throws IndexingError {
        testSearcher(1000,107);
    }

    @org.junit.jupiter.api.Test
    void test1stIn10000() throws IndexingError {
        testSearcher(10000,1);
    }

    @org.junit.jupiter.api.Test
    void test1003rdn10000() throws IndexingError {
        testSearcher(10000,1003);
    }

    @org.junit.jupiter.api.Test
    void test11thIn100000() throws IndexingError {
        testSearcher(100000,11);
    }

    @org.junit.jupiter.api.Test
    void test4thIn1000000() throws IndexingError {
        testSearcher(1000000,4);
    }

    @org.junit.jupiter.api.Test
    void test10thIn10() throws IndexingError
    {
        testSearcher(10, 10);
    }

    @org.junit.jupiter.api.Test
    void test1stin1() throws IndexingError
    {
        testSearcher(1, 1);
    }

    /**
     * Create random test data between 1 and 200000
     * Run testSearcher with this generated data.
     *
     * Repeat this a number of times specified by NUM_RANDOM_TESTS
     *
     * @throws IndexingError If the index generated is invalid with respect to arraySize.
     */
    @org.junit.jupiter.api.Test
    void testRandom() throws IndexingError
    {
        Random rand = new Random();
        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            int size = rand.nextInt(199999) + 1;
            int index = rand.nextInt(size) + 1;
            testSearcher(size, index);
        }
    }


    @org.junit.jupiter.api.Test
    void testSmallest() throws IndexingError
    {
        testSearcher(10, 1);
    }

    @org.junit.jupiter.api.Test
    void test0thLargest() throws IndexingError
    {
        testIndexError(5, 0);
    }

    @org.junit.jupiter.api.Test
    void testNegativeIndex() throws IndexingError
    {
        testIndexError(5, -1);
    }

    @org.junit.jupiter.api.Test
    void testHighIndex() throws IndexingError
    {
        testIndexError(5, 6);
    }


}