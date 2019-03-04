package stringSearcher;

import arrayGenerator.CharacterArrayGenerator;
import scope.CharacterScope;
import timer.Timer;

/**
 * Times the Boyer Moore searcher for varying sizes of superstring.  A fixed substring is
 * created, and Boyer Moore searchers of various superstring sizes are timed against it.
 *
 * @author Joshua Pritchard - Adapated from work by Hugh Osborne.
 * @version November 2018
 */
public class BoyerMooreSuperstringSizeTimer extends BoyerMoore implements Timer
{
    private static CharacterArrayGenerator generator = new CharacterArrayGenerator(new CharacterScope("abc"));

    public BoyerMooreSuperstringSizeTimer(char[] string) {
        super(string);
    }

    private static char[] getString(int size) {
        Character[] tempArray = generator.getArray(size);
        char[] string = new char[size];
        for (int i = 0; i < size; i++) {
            string[i] = tempArray[i];
        }
        return string;
    }

    private static char[] superstring;

    private static char[] getSuperstring() {
        return superstring;
    }

    @Override
    public Timer getTimer(int size) {
        superstring = getString(size);
        char[] substring = getString(9);
        return new BoyerMooreSuperstringSizeTimer(substring);
    }

    @Override
    public void timedMethod() {
        try {
            occursIn(getSuperstring());
        } catch (NotFound notFound) {

        }
    }

    @Override
    public long getMaximumRuntime() {
        return 5;
    }

    @Override
    public int getMinimumTaskSize() {
        return 10;
    }

    @Override
    public int getMaximumTaskSize() {
        return 20000000;
    }

    @Override
    public int getRunSetSize() {
        return 10;
    }

    public static void main(String[] args) {
        BoyerMooreSuperstringSizeTimer timer = new BoyerMooreSuperstringSizeTimer(null);
        timer.timingSequence();
    }
}
