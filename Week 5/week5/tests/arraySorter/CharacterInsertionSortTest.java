package arraySorter;

import arrayGenerator.ArrayGenerator;
import arrayGenerator.CharacterArrayGenerator;

/**
 * Created by u1661665(Joshua Pritchard) on 13/11/2018.
 */
public class CharacterInsertionSortTest extends InsertionSortTest<Character> {
    @Override
    ArrayGenerator<Character> getGenerator() {
        return new CharacterArrayGenerator();
    }
}
