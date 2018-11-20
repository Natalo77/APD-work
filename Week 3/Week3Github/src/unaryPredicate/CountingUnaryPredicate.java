package unaryPredicate;

/**
 * Created by u1661665(Joshua Pritchard) on 11/11/2018.
 */

/**
 * A partial implementation of the UnaryPredicateCount interface, implementing the numberSatisfying method.
 */
abstract class CountingUnaryPredicate<T> implements UnaryPredicateCount<T>
{
    /**
     * Returns the number of objects within the array that satisfy the predicate's test method.
     *
     * @param array An array of objects of the type tested by this predicate's test
     * @return the number of objects within param array that satify test(). Return -1 if not all elements in the array
     * are of type T.
     */
    @Override
    public int numberSatisfying(T[] array)
    {
        for (Object a:array)
        {
            try
            {
                T b = (T)a;
            }catch (ClassCastException e)
            {
                return -1;
            }
        }

        int num = 0;
        for (Object a : array)
        {
            if(test((T) a))
            {
                num++;
            }
        }
        return num;
    }
}
