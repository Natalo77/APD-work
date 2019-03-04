package genericMethods;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by u1661665 (Joshua Pritchard) on 10/11/2018.
 */

/**
 * A class that defines a generic method to return the largest element within an array between 2 separate index
 * locations.
 */
public class Max {

    /**
     * Returns the largest element of type T between the two index values specified within the array given.
     *
     * @param array the array of type T to be searched.
     * @param index1 An upper/lower bound of index locations to search within the array.
     * @param index2 A separate bound of index locations to search within the array.
     * @param <T> The type of data contained within the array.
     * @return The largest element between index1 and index2 within array
     * @throws IndexOutOfBoundsException if either index1 or index2 are invalid relative to the size of array.
     */
    public static <T extends Comparable<T>> T max(T[] array, int index1, int index2) throws IndexOutOfBoundsException
    {
        //make sure no invalid indexes are passed in.
        if(index1 < 0 || index2 < 0 || index1 > array.length - 1 || index2 > array.length - 1)
        {
            throw new IndexOutOfBoundsException("Bad index values.");
        }

        //Find the lower value of the two indices.
        int lowerIndex = index1 < index2 ? index1 : index2;
        //Find the higher value of the two indices. (Then adjust the index value to exclude that element)
        int upperIndex = (lowerIndex == index1 ? index2 : index1);
        //Find the size of the sub array that needs to be created.
        int size = upperIndex - lowerIndex + 1;

        //If the array would be one element, return the only element within.
        if(size == 1)
        {
            return array[lowerIndex];
        }

        //Create a new array list and populate it with the elements between the two specified indices.
        ArrayList<T> newArray = new ArrayList<>();
        for(int x = 0; x < size; x++)
        {
            newArray.add(array[x + lowerIndex]);
        }

        //sort the array and then return the last element(the largest).
        Collections.sort(newArray);
        return newArray.get(newArray.size() - 1);
    }
}
