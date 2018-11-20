package genericMethods;

/**
 * Created by u1661665 (Joshua Pritchard) on 24/10/2018.
 */


/**
 * This class defines a static method to swap two elements of the same type
 * in an array.
 */
public class Swap
{
    /**
     * Swaps two elements within an array
     *
     * @param array the array to be modified, of type T.
     * @param index1 the index of the first value to be swapped.
     * @param index2 the index of the second value to be swapped.
     * @param <T> the type of data the array contains.
     * @throws IndexOutOfBoundsException if any of (index1, index2) are invalid relative to param array.
     */
    public static <T> void swap(T[] array, int index1, int index2) throws IndexOutOfBoundsException
    {
        //This section builds a string of invalid indices, then if that string is not empty, throws an
        // indexOutOfBoundsException, passing through the indices that were found to be invalid and the size of the array.
        StringBuilder badIs = new StringBuilder("");
        boolean bad = false;
        if (index1 < 0 || index1 > array.length - 1 || index1 == index2)
        {
            badIs.append("1: " + index1 +", ");
            bad = true;
        }
        if (index2 < 0 || index2 > array.length - 1 || index1 == index2)
        {
            badIs.append("2: " + index2 +", ");
            bad = true;
        }
        if(bad)
        {
            throw new IndexOutOfBoundsException("Invalid indexes: " + badIs.toString() + " relative to param array of size: " + array.length);
        }

        //Use a temp T object to swap the two values.

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;

    }
}
