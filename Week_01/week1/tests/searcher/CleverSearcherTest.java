package searcher;

/**
 * @author JPritchardU1661665
 * @version October 2018
 */

class CleverSearcherTest extends SearcherTest{

    @Override
    protected Searcher createSearcher(int[] array, int index) throws IndexingError
    {
        return new CleverSearcher(array, index);
    }
}
