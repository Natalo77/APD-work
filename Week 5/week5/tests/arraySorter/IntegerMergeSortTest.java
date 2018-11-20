package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.IntegerArrayGenerator;
import scope.IntegerScope;

/**
 * Created by u1661665(Joshua Pritchard) on 13/11/2018.
 */
public class IntegerMergeSortTest extends MergeSortTest<Integer> {
    @Override
    ArrayGenerator<Integer> getGenerator() {
        return new IntegerArrayGenerator(new IntegerScope(1, 13));
    }
}
