package stringSearcher;
/**
 * A class defining an implementation of a String searcher, using the BoyerMoore string searching algorithm.
 *
 * @author Joshua Pritchard.
 * @version November 2018
 */
public class BoyerMoore extends StringSearcher
{
    /**
     * Creates a BoyerMoore instance
     * @param string the string to be used as the substring that this BoyerMoore searches for.
     */
    public BoyerMoore(char[] string)
    {
        super(string);
    }

    /**
     * Creates a BoyerMoore instance.
     * @param string the string to be used as the substring that this BoyerMoore searches for.
     */
    public BoyerMoore(String string)
    {
        super(string);
    }

    /**
     * Find the first occurrence of the substring member variable 'string' in param superstring.
     * Implementation is the BoyerMoore algorithm.
     *
     * @param superstring the superstring to be searched
     * @return the index value of the first occurrence of the substring 'string'
     * @throws NotFound if the substring is not found within param superstring.
     */
    @Override
    public int occursIn(char[] superstring) throws NotFound
    {
        int i = getString().length - 1;
        int j = getString().length - 1;

        do
        {
            if(getString()[j] == superstring[i])
            {
                if(j == 0)
                {
                    return i;
                }
                else
                {
                    i--;
                    j--;
                }
            }
            else
            {
                i = i + getString().length - Math.min(j, 1 + last(superstring[i]));
                j = getString().length - 1;
            }
        } while(i < superstring.length);

        throw new NotFound();
    }

    /**
     * Return the last occurrence's index of a given character in the subString.
     *
     * @param c The character to be searched for in the substring.
     * @return the index value of the rightmost occurrence of c in subString.\nReturn -1 if c does not occur.
     */
    private int last(char c)
    {
        for (int x = getString().length - 1; x > -1; x--)
        {
            if (c == getString()[x])
                return x;
        }

        return -1;
    }
}
