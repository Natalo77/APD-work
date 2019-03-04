package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 12/11/2018.
 */

/**
 * An implementation of a Selection Sort
 *
 * @param <T> The type of data held by the Array to be sorted.
 */
public class SelectionSort<T extends Comparable<? super T>> implements ArraySort<T>
{

    @Override
    public T[] sort(T[] array)
    {
        //For(The whole list is unsorted, there is still part of the list unsorted, another element has been sorted.)
        for (int sortedElements = 0; sortedElements < array.length; sortedElements++)
        {
            //Find the index of the largest unsorted element.
            int indexOfLargestElement = -1;
            T largestElement = null;
            for(int x = 0; x < array.length - sortedElements; x++)
            {
                if(largestElement == null)
                {
                    largestElement = array[x];
                    indexOfLargestElement = x;
                    continue;
                }
                if (array[x].compareTo(largestElement) > 0)
                {
                    largestElement = array[x];
                    indexOfLargestElement = x;
                }

            }

            //Swap it with the last unsorted element.
            T temp = array[array.length - sortedElements - 1];
            array[array.length - sortedElements - 1] = array[indexOfLargestElement];
            array[indexOfLargestElement] = temp;
        }
        return array;
    }
}
