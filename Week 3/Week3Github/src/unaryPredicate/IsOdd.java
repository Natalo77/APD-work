package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

/**
 * An implementation of UnaryPredicate over integers.
 *
 * the overridden test method returns true if the passed in integer is odd.
 */
public class IsOdd extends CountingUnaryPredicate<Integer>
{

    /**
     * Returns a boolean specifying whether or not the integer n is odd or not.
     *
     * @param n an Integer to be checked.
     * @return true if n is odd, false if otherwise.
     */
    @Override
    public boolean test(Integer n)
    {
        return (Math.abs(n % 2) == 1);
    }


}
