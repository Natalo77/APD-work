package unaryPredicate;

/**
 * A unary predicate is a function that takes a single values and returns a boolean.
 *
 * In this interface this function is called test.
 *
 * @param <T> the type of object to be tested by the test function
 *
 * @author Hugh Osborne
 * @version October 2018
 */
public interface UnaryPredicate<T> {
    public boolean test(T object);
}

