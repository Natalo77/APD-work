package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

/**
 * an Implementation of a UnaryPredicate over integers.
 *
 * The test method returns whether or not the passed in Integer is prime or not.
 */
public class IsPrime extends CountingUnaryPredicate<Integer>
{
    /**
     * Returns whether or not the integer passed in is a prime number or not.
     *
     * @param n the integer to be checked for prime-ness.
     * @return true if n is prime, false otherwise.
     */
    @Override
    public boolean test(Integer n)
    {
        n = Math.abs(n);
        if(n == 0 || n == 1 || n == 2)
        {
            return false;
        }

        //Go through each number from 2 to n-1, checking if a modulus comes back as 0. If it does for any value
        //of 'x', then return false, as n is not prime.
        for(int x = 2; x < n; x++)
        {
            if(n % x == 0)
            {
                return false;
            }
        }
        return true;
    }
}
