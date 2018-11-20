package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 13/11/2018.
 */

import java.util.Arrays;

/**
 * An implementation of MergeSort
 *
 * @param <T> the type of data held by the array.
 */
public class MergeSort<T extends Comparable<? super T>> implements ArraySort<T>
{
    /**
     * Sort the array using a MergeSort.
     *
     * (recursively breaks down the array into sub arrays until arrays of size 1 are reached.)
     * (then works backwards merging these arrays back into each other until a sorted array state is reached containing
     *  all the elements of the initial unsorted array, but now sorted).
     *
     * @param array the array to be sorted.
     * @return array (sorted)
     */
    @Override
    public T[] sort(T[] array)
    {
        //If the array being sorted is less than 2 elements, it is already sorted and cannot be split into two separate
        //     arrays, so return the initial array to prevent an exception.
        if(array.length < 2)
        {
            return array;
        }

        //Create two sub arrays from the initial array passed in.
        T[] temp1 = copy(array, 0, (array.length/2) + (array.length % 2));
        T[] temp2 = copy(array, (array.length/2) + (array.length % 2), array.length);

        //Sort these two arrays.
        temp1 = sort(temp1);
        temp2 = sort(temp2);

        //Merge the two subarrays back into the initial array.
        merge(array, temp1, temp2);

        //return the now sorted array.
        return array;
    }

    /**
     * Create and return a new array, comprised of a sublist of a passed in array.
     *
     * @param list the array to create the subarray from.
     * @param from the lower bound within list to copy (inclusive).
     * @param to the upper bound within list to copy (exclusive).
     * @return a new list comprised of the elements from 'from' to 'to' in 'list'
     */
    private <T> T[] copy(T[] list, int from, int to)
    {
        return Arrays.copyOfRange(list, from, to);
    }

    /**
     * Merge two sorted arrays into one larger array, keeping the sorted.
     *
     * @param target the array to merge source1 and source2 into.
     * @param source1 a sorted array
     * @param source2 a second sorted array.
     */
    private void merge(T[] target, T[] source1, T[] source2)
    {
        //Variables to keep track of the smallest unused element in each source array.
        int source1Index = 0;
        int source2Index = 0;

        int lowestMaxIndex = (source1.length < source2.length ? source1.length : source2.length);

        //targetIndex keeps track of the next index to place the next smallest element into.
        for(int targetIndex = 0; targetIndex < lowestMaxIndex * 2; targetIndex++)
        {
            //If the end of source1 has been reached, add the rest of source2 to target, then break the merge.
            if(source1Index == source1.length)
            {
                int iterator = 0;
                for(int x = source2Index; x < source2.length; x++)
                {
                    target[targetIndex + iterator] = source2[x + iterator];
                }
                break;
            }
            //if the end of source2 has been reached, add the rest of source1 to target, then break the merge.
            if(source2Index == source2.length)
            {
                int iterator = 0;
                for(int x = source1Index; x < source1.length; x++)
                {
                    target[targetIndex + iterator] = source1[x + iterator];
                }
                break;
            }

            //Choose the smallest element from the two source arrays to place into the target(sorted) array
            if(source1[source1Index].compareTo(source2[source2Index]) < 0)
            {
                target[targetIndex] = source1[source1Index];
                source1Index++;
            }
            else
            {
                target[targetIndex] = source2[source2Index];
                source2Index++;
            }
        }


    }
}
