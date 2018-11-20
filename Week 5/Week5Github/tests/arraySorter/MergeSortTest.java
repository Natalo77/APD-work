package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 13/11/2018.
 */
public abstract class MergeSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new MergeSort<T>();
    }
}
