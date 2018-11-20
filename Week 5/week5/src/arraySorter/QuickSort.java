package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 12/11/2018.
 */

/**
 * An Implementation of a QuickSort.
 *
 * @param <T> the type of data held by the array to be sorted.
 */
public class QuickSort<T extends Comparable<? super T>> implements ArraySort<T>
{
    /**
     * Sorts the passed in array using a QuickSort implementation, then returns the sorted array.
     *
     * @param array the array to be sorted.
     * @return array (sorted)
     */
    @Override
    public T[] sort(T[] array)
    {
        return quickSort(array, 0, array.length);
    }

    /**
     * A recursive method to arrange an array around a pivot point, then repeat this process to the left and right sides
     * of this pivot point until the array ends sorted.
     *
     * @param array the array on which the quicksort is being performed.
     * @param low the smallest index location to sort.
     * @param high the largest index location to sort + 1.
     * @return the sorted array.
     */
    private T[] quickSort(T[] array, int low, int high)
    {
        if(low < high)
        {
            //Arrange the array around an initial pivot point.
            int pivotNewLocation = partition(array, low, high);
            //QuickSort the array to the left of the original pivot point.
            quickSort(array, low, pivotNewLocation);
            //QuickSort the array to the right of the original pivot point.
            quickSort(array, pivotNewLocation + 1, high);
        }

        return array;
    }

    /**
     * On the array, set the pivot point as the left most element.
     * sort all smaller elements to the left of the pivot point.
     * sort all larget elements to the right of the pivot point.
     * swap the still leftmost pivot point into its correct position.
     * return the index location the pivot point was swapped into.
     *
     * @param array the array on which the QuickSort is operating.
     * @param low the bottom element to arrange around the pivot.
     * @param high the last element to arrange around the pivot
     * @return the index location in the array the pivot was placed into.
     */
    private int partition(T[] array, int low, int high)
    {
        T pivot = array[low];
        int leftWall = low;

        for(int i = low + 1; i < high; i++)
        {
            if(array[i].compareTo(pivot) < 0)
            {
                swap(array, i, leftWall + 1);
                leftWall++;
            }
        }
        swap(array, low, leftWall);

        return leftWall;
    }

    /**
     * Swap two elements within the array the QuickSort is operating on
     *
     * @param array the array the QuickSort is operating on.
     * @param i1 the first index location to swap.
     * @param i2 the second index location to swap.
     */
    private void swap(T[] array, int i1, int i2)
    {
        T temp = array[i1];
        array[i1] = array[i2];
        array[i2] = temp;
    }
}
