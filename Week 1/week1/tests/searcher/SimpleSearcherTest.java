package searcher;



/**
 * @author Hugh Osborne
 * @version September 2018
 */

class SimpleSearcherTest extends SearcherTest {

    @Override
    protected Searcher createSearcher(int[] array, int index) throws IndexingError {
        return new SimpleSearcher(array,index);
    }

}