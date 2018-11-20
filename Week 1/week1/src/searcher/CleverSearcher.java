package searcher;

import java.util.Arrays;

/**
 * @author JPritchardU1661665
 * @version October 2018
 */
public class CleverSearcher extends Searcher
{
    CleverSearcher(int[] array, int k)
    {
        super(array, k);
    }

    /**
     * Find the kth largest element in an array of ints using the "Clever"
     * solution from the lecture.
     *
     * <ul>
     *     <li> Create an auxilliary array of the first k elements in the original array</li>
     *     <li> For each of the remaining elements in the original array</li>
     *     <ul>
     *         <li> If it's bigger than the smallest element in the Aux array</li>
     *         <ul>
     *             <li> Replace the smallest element with the new element</li>
     *             <li> Sort the aux array</li>
     *         </ul>
     *     </ul>
     *     <li> When finished, return the smallest element in the Aux array (The kth largest in the original array)</li>
     * </ul>
     *
     * @return kth largest element of array.
     * @throws IndexingError
     */
    @Override
    public int findElement() throws IndexingError
    {
        int k = super.getIndex();
        int[] array = super.getArray();

        //Throw an exception if an invalid index has been provided.
        if(k <= 0 || k > array.length)
        {
            throw new IndexingError();
        }

        //Create an array of size k
        //This will hold the 'k' largest elements at the end of the process.
        int[] auxArray = new int[k];

        //Populate it with the first k elements from the original array.
        for(int x = 0; x<k; x++)
        {
            auxArray[x] = array[x];
        }

        //Sort the 'k' array so that the smallest element is at the start.
        Arrays.sort(auxArray);

        //For each of the remaining elements in the original array.
        for(int x = k; x < array.length; x++)
        {
            //If it is larger than the smallest number in the 'current' 'k' largest elements...
            if(array[x] > auxArray[0])
            {
                //'bump' it out of the 'k' largest elements (replace it)
                auxArray[0] = array[x];
            }

            //Re-sort the 'k' array so the smallest is at the start again.
            Arrays.sort(auxArray);
        }
        //return the smallest element in the 'k' array (the kth largest element)
        return auxArray[0];
    }
}
