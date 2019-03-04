package genericMethods;

/**
 * Created by u1661665 (Joshua Pritchard) on 24/10/2018.
 */

import RandomMethods.RandomStringArray;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * Defines a set of testing methods for the class 'Swap'
 */
class SwapTest
{

    //RandomStringsArray values - Use these to modify the operation of the testRandomStringsArray test method.
    private final int HIGHEST_SIZE_ARRAY = 10;
    private final int LOWEST_SIZE_ARRAY = 3;
    private final int HIGHEST_SIZE_STRING = 10;
    private final int LOWEST_SIZE_STRING = 3;

    //Iterations of each random test method ran.
    private final int NUM_RANDOM_TESTS = 500000;

    /**
     * Defines a generic method to test that the swapped values have been swapped into
     * the correct positions.
     *
     * @param array the array to be modified.
     * @param index1 the first index of the array to be swapped.
     * @param index2 the second index of the array to be swapped.
     * @param <T> the type of data contained by the array.
     * @return true if after the swap, the elements have indeed, swapped place. false otherwise.
     */
    private <T> boolean testSwapContents(T[] array, int index1, int index2)
    {
        //Copy the initial values of the array values at the indices.
        T valueI1 = array[index1];
        T valueI2 = array[index2];

        //perform the swap operation on the array.
        Swap.swap(array, index1, index2);

        //return the result of the comparison of initial values to 'post-swap' values.
        return array[index2] == valueI1 && array[index1] == valueI2;
    }

    private int getIndex(boolean badIndex, int size)
    {
        Random rand = new Random();

        //get a random index location inside of the array
        int ind = rand.nextInt(size - 1);

        //if a bad index is required, make sure it's not 0, then minus this index value.
        if(badIndex)
        {
            if(ind == 0) ind++;
            ind = -ind;
        }

        return ind;
    }

    /**
     * Defines a method for testing whether a bad index was passed in to a Swap operation or not.
     * This method expects that the indices passed to it are bad.
     *
     * @param array the array to be modified.
     * @param index1 the first index location to be swapped.
     * @param index2 the second index location to be swapped.
     * @param <T> the type of data contained within param 'array'
     * @return false if the indices were fine, true if the indices were bad, as expected.
     */
    private <T> boolean testBadIndex(T[] array, int index1, int index2)
    {
        try{
            Swap.swap(array, index1, index2);
            return false;
        }
        catch (IndexOutOfBoundsException e)
        {
            return true;
        }
    }

    /**
     * A test method that creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values'
     * at the top of this class. Then tests this random array with the testSwapContents method.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testRandomStringsArray()
    {
        //Run the test a number of times specified by NUM_RANDOM_TESTS
        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            //Get a random arrayList of strings as per the values defined under 'testRandomStringsArray values'
            ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                    LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

            //Create two valid indices, making sure they are inequal.
            int i1 = getIndex(false, testData.size());
            int i2;
            do{
                i2 = getIndex(false, testData.size());
            }while(i1 == i2);

            //Run the test method, failing the test if testSwapContents comes back false.
            if(!testSwapContents(testData.toArray(), i1, i2))
            {
                fail("contents not swapped correctly.");
            }
        }
    }

    /**
     * Creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values' at the top of
     * this class. Then creates a bad index1 value and runs the testBadIndex method with the generated data.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testBadIndex1Strings()
    {
        ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            if(!testBadIndex(testData.toArray(), getIndex(true, testData.size()), getIndex(false, testData.size())))
            {
                fail("bad index was not caught.");
            }
        }
    }

    /**
     * Creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values' at the top of
     * this class. Then creates a bad index2 value and runs the testBadIndex method with the generated data.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testBadIndex2Strings()
    {
        ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            if(!testBadIndex(testData.toArray(), getIndex(false, testData.size()), getIndex(true, testData.size())))
            {
                fail("bad index was not caught.");
            }
        }
    }

    /**
     * Creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values' at the top of
     * this class. Then creates a bad index1 and index2 value and runs the testBadIndex method with the generated data.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testBothBadIndicesStrings()
    {
        ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            if(!testBadIndex(testData.toArray(), getIndex(true, testData.size()), getIndex(true, testData.size())))
            {
                fail("bad index was not caught.");
            }
        }
    }

    /**
     * Creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values' at the top of
     * this class. Then creates one index value and runs the testBadIndex method with the generated data, using this index
     * value for both of the index parameters.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testBothIndicesSameValue()
    {
        ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

        for(int x = 0; x < NUM_RANDOM_TESTS; x++)
        {
            int ind = getIndex(false, testData.size());
            if(!testBadIndex(testData.toArray(), ind, ind))
            {
                fail("Identical indices not caught.");
            }
        }
    }

    /**
     * Creates a random array of strings per the bounds of the values labelled 'RandomStringsArray values' at the top of
     * this class. Then creates 2 valid indices to swap and runs the swap.
     *
     * Then checks that each element in the test data before the swap is contained within the test data after the swap
     * failing if it is not found.
     *
     * This is ran a number of times specified by NUM_RANDOM_TESTS
     */
    @Test
    void testContentsBeforeAndAfter()
    {

        for(int x  = 0; x < NUM_RANDOM_TESTS; x++)
        {
            ArrayList<String> testData = RandomStringArray.getRandomStringArray(LOWEST_SIZE_ARRAY, HIGHEST_SIZE_ARRAY,
                    LOWEST_SIZE_STRING, HIGHEST_SIZE_STRING);

            ArrayList<String> testDataBefore = new ArrayList<>(testData);

            int i1 = getIndex(false, testData.size());
            int i2;
            do
            {
                i2 = getIndex(false, testData.size());
            } while (i1 == i2);

            Swap.swap(testData.toArray(), i1, i2);
            for (String s : testDataBefore)
            {
                if (!testData.contains(s))
                {
                    fail("String: " + s + " not present after swap occurred.");
                }
            }
        }
    }

    /**
     * A test method to test an array of integers using random index values to make sure the contents are swapped correctly.
     */
    @Test
    void testIntegers()
    {
        Integer[] ints = {5, 23, 456, 2, 3463, 123, 83456};

        int i1 = getIndex(false, ints.length);
        int i2;
        do
        {
            i2 = getIndex(false, ints.length);
        } while (i1 == i2);

        if(!testSwapContents(ints, i1, i2))
        {
            fail("Contents not swapped properly with integers.");
        }
    }

    /**
     * A test method to test an array of chars using random index values to make sure the contents are swapped correctly.
     */
    @Test
    void testChars()
    {
        Character[] chars = {'a', 'b', 'y', '2', 'g', 'A', '='};

        int i1 = getIndex(false, chars.length);
        int i2;
        do
        {
            i2 = getIndex(false, chars.length);
        } while (i1 == i2);

        if(!testSwapContents(chars, i1, i2))
        {
            fail("Contents not swapped properly with characters.");
        }
    }


}