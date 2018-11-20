package stringSearcher;

import arrayGenerator.CharacterArrayGenerator;
import scope.CharacterScope;
import timer.Timer;

/**
 * Times the Boyer Moore searcher for varying sizes of substring.  A large fixed size superstring is
 * created, and Boyer Moore searchers of various sizes are timed against it.
 *
 * @author Joshua Pritchard - Adapated from work by Hugh Osborne.
 * @version November 2018
 */
public class BoyerMooreSubstringSizeTimer extends BoyerMoore implements Timer
{
    private static CharacterArrayGenerator generator = new CharacterArrayGenerator(new CharacterScope("ab"));

    private static char[] getString(int size) {
        Character[] tempArray = generator.getArray(size);
        char[] string = new char[size];
        for (int i = 0; i < size; i++) {
            string[i] = tempArray[i];
        }
        return string;
    }

    private final static int SUPERSTRING_SIZE = 20000000;
    private static char[] superstring = getString(SUPERSTRING_SIZE);

    public BoyerMooreSubstringSizeTimer(char[] string) {
        super(string);
    }

    @Override
    public Timer getTimer(int size) {
        return new BoyerMooreSubstringSizeTimer(getString(size));
    }

    @Override
    public void timedMethod() {
        try {
            occursIn(superstring);
        } catch (NotFound notFound) {

        }
    }

    @Override
    public long getMaximumRuntime() {
        return 5;
    }

    @Override
    public int getMinimumTaskSize() {
        return 1;
    }

    @Override
    public int getMaximumTaskSize() {
        return 1000;
    }

    @Override
    public int getRunSetSize() {
        return 10;
    }

    public static void main(String[] args) {
        BoyerMooreSubstringSizeTimer timer = new BoyerMooreSubstringSizeTimer(null);
        timer.timingSequence();
    }
}
