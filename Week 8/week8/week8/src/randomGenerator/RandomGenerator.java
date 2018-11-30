package randomGenerator;

/**
 * Random generators must provide a method that returns a random
 * value of the specified type
 *
 * @author Hugh Osborne
 * @version November 2018
 *
 * @param <T> the type of object generated
 */
public interface RandomGenerator<T> {
    /**
     * Generate and return a random value
     * @returns a random value of type T
     */
    public T generate();

    public class Error extends Exception {
        public Error(String message) {
            super("Random generator error: " + message);
        }
    }
}