package genericMethods;

/**
 * Provide a static method that will find the largest entry in an array of Comparables
 * between two indices.
 *
 * @author Hugh Osborne
 * @version October 2018
 *
 */
class MaxModel {

    /**
     * Find the largest element in an array of Comparables.
     * @param array the array to be searched.
     * @return the largest element in the array, or null, if the array is empty.
     * Note: this exercise makes use of bounded generics, introduced in week 4.
     */
    static <T extends Comparable<T>> T max(T[] array,int from,int to) {
        if (from < 0 || to > array.length) {
            throw new ArrayIndexOutOfBoundsException("[from,to) must be a section of [0," + array.length + ")");
        }
        if (to <= from) {
            throw new ArrayIndexOutOfBoundsException("to must be greater than from");
        }
        T currentLargest = array[from]; // current largest value is the first element
        for (int i = from+1; i < to; i++) { // for all values in the array
            if (array[i].compareTo(currentLargest) > 0) { // if this value is larger than the current largest
                currentLargest = array[i]; // update the current largest to the new, larger value
            }
        } // have now checked all values in the array, so current largest is the maximum value in the whole array
        return currentLargest;
    }
}
