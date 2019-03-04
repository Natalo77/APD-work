package stringSearcher;

/**
 * An implementation of the StringSearcherTest class's test methods for the BoyerMoore string searcher.
 *
 * @author Joshua Pritchard - Adapted from work by Hugh Osborne.
 * @version November 2018
 */
class BoyerMooreTest extends StringSearcherTest {

    @Override
    StringSearcher getSearcher(String string) {
        return new BoyerMoore(string);
    }
}