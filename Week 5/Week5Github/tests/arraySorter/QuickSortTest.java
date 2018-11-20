package arraySorter;

/**
 * Created by u1661665(Joshua Pritchard) on 12/11/2018.
 */
public abstract class QuickSortTest<T extends Comparable<? super T>> extends ArraySortTest<T> {
    @Override
    ArraySort<T> getSorter() {
        return new QuickSort<T>();
    }
}
