package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

/**
 * An implementation of UnaryPredicate over an array of Comparables.
 *
 * the test method returns whether or not the array is monotonic. (constantly increasing or constantly decreasing.
 */
public class IsMonotonic extends CountingUnaryPredicate<Comparable[]>
{
    /**
     * Returns whether or not the specified array comparables is monotonic.
     *
     * @param comparables an Array of Comparable to be checking for monotonicity.
     * @return true if the array is monotonic, false otherwise.
     */
    @Override
    public boolean test(Comparable[] comparables)
    {
        boolean increasing = comparables[0].compareTo(comparables[1]) < 0;
        for(int x = 1; x < (comparables.length - 1); x++)
        {
            if(increasing && comparables[x].compareTo(comparables[x+1]) > 0)
            {
                return false;
            }
            else if(!increasing && comparables[x].compareTo(comparables[x+1]) < 0)
            {
                return false;
            }
            else if(comparables[x].compareTo(comparables[x+1]) == 0)
            {
                return false;
            }
        }
        return true;
    }
}
