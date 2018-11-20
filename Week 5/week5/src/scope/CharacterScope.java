package scope;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of an {@link scope.AlphabetScope} for characters.
 *
 * @author Hugh Osborne
 * @version October 2018
 */

public class CharacterScope extends AlphabetScope<Character> {

    // Default alphabet.  This uses the standard lower case English alphabet.
    // It would be better to redefine this to take its character set from the {@link Locale}.
    private static final String _1_DEFAULT_ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final String _2_DEFAULT_ALPHABET = _1_DEFAULT_ALPHABET + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String _3_DEFAULT_ALPHABET = _2_DEFAULT_ALPHABET + "1234567890!£$%^&*()-=_+#~@";

    /**
     * Utility to convert a {@link String} specification of an alphabet to a set of characters.
     *
     * Note that since the target is a set, and duplicates will automatically be removed.
     *
     * @param source the String specification of the desired alphabet.
     * @return a set of all the characters in the source string.
     */
    private static Set<Character> toCharacterSet(String source) {
        char[] chars = source.toCharArray();
        Set<Character> characterSet = new HashSet<Character>();
        for (int i = 0; i < chars.length; i++) {
            characterSet.add(chars[i]);
        }
        return characterSet;
    }

    /**
     * If no scope is specified, use the default alphabet.
     */
    public CharacterScope() {
        super(toCharacterSet(_3_DEFAULT_ALPHABET));
    }

    /**
     * Specify the scope by means of a String.
     *
     * @param alphabet the alphabet to be used for this scope.
     */
    public CharacterScope(String alphabet) {
        super(toCharacterSet(alphabet));
    }

    /**
     * Specify the scope by means of a set of values.
     *
     * @param alphabet the set of permitted values for this scope.
     */
    public CharacterScope(Set<Character> alphabet) {
        super(alphabet);
    }
}
