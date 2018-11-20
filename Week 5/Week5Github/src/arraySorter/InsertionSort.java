package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 12/11/2018.
 */

/**
 * An implementation of an Insertion Sort.
 *
 * @param <T> the type of data held by the array.
 */
public class InsertionSort<T extends Comparable<? super T>> implements ArraySort<T>
{

    /**
     * Sorts the array using an insertion sort.
     *
     * @param array the array to be sorted.
     * @return the sorted array.
     */
    @Override
    public T[] sort(T[] array)
    {
        //sorted = the largest element index that has been sorted
        //    (Start out with the first element in the list being the sorted list).
        for(int sorted = 0; sorted < array.length - 1; sorted++)
        {
            //The element to be 'inserted' into the sorted part of the array.
            T newElement = array[sorted + 1];
            //Compare to the largest element in the sorted part of the array first.
            int sortedListCompareIndex = sorted;

            //Move larger elements up in their place in the sorted list. (making space for the newElement
            while (sortedListCompareIndex >= 0 && newElement.compareTo(array[sortedListCompareIndex]) < 0)
            {
                array[sortedListCompareIndex + 1] = array [sortedListCompareIndex];
                sortedListCompareIndex--;
            }
            //Place the newElement in its correct place within the array.
            array[sortedListCompareIndex + 1] = newElement;
        }
        return array;
    }
}
