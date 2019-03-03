package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

/**
 * An implementation of UnaryPredicate over Strings.
 *
 * The test method returns whether or not the passed in string is a palindrome or not.
 */
public class IsPalindrome extends CountingUnaryPredicate<String>
{

    /**
     * Returns whether or not the string s is a palindrome or not.
     *
     * @param s the string to be checked for palindrome-ness.
     * @return true if the string s is a palindrome, false otherwise.
     */
    @Override
    public boolean test(String s)
    {
        //Check each pair of characters from the start/end to the middle pair of the string.
        //If any pair are not identical, the string is not a palindrome, so return false.
        String a = s.replaceAll("[^a-zA-Z]", "");
        a = a.toLowerCase();
        int end = a.length() - 1;
        for(int x = 0; x < end/2; x++)
        {
            if (a.charAt(x) != a.charAt(end - x))
            {
                return false;
            }
        }
        return true;
    }
}
