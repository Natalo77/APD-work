import graph.Graph;
import graph.GraphError;
import graph.ReferenceCountingTopologicalSort;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by u1661665(Joshua Pritchard) on 14/01/2019.
 * Version: 15.01.2019
 */
public class RefCountTopoSortTest
{
    /**
     * Create an acyclic graph with 4 elements and make sure that the returned list contains all the same elements
     * as are present in the graph.
     *
     * @throws GraphError if any attempts to make illegal accesses are made.
     */
    @Test
    public void testContents() throws GraphError
    {
        //Create a graph.
        ReferenceCountingTopologicalSort<Integer> intSort = new ReferenceCountingTopologicalSort<>();

        //Populate it with nodes.
        intSort.add(1);
        intSort.add(2);
        intSort.add(3);
        intSort.add(4);

        //Create some edges.
        intSort.add(1, 2);
        intSort.add(1, 3);
        intSort.add(2, 4);

        /*
              __2---4
            1 __
                3
         */

        //Get the sort.
        List<Integer> returned = intSort.getSort();

        //Print the sort.
        System.out.println(returned.toString());

        //If any of the nodes are not contained within the sort, it's a fail.
        if(!returned.contains(1))
        {
            fail("1 not found.");
        }
        if(!returned.contains(2))
        {
            fail("2 not found.");
        }
        if(!returned.contains(3))
        {
            fail("3 not found.");
        }
        if(!returned.contains(4))
        {
            fail("4 not found.");
        }
    }

    /**
     * Create an acyclic graph with 4 elements and test that the size of the list returned matches the number of
     * nodes given to the graph.
     *
     * @throws GraphError if any illegal attempts to access the nodes or edges of this graph are made.
     */
    @Test
    public void testSizeList() throws GraphError
    {
        //Create a graph.
        ReferenceCountingTopologicalSort<Integer> intSort = new ReferenceCountingTopologicalSort<>();

        //Populate it with nodes.
        intSort.add(1);
        intSort.add(2);
        intSort.add(3);
        intSort.add(4);

        //Create some edges.
        intSort.add(1, 2);
        intSort.add(1, 3);
        intSort.add(2, 4);

        /*
              __2---4
            1 __
                3
         */

        //Get the sort.
        List<Integer> returned = intSort.getSort();

        //If the size of the list is not 4, then something has gone wrong.
        if(returned.size() != 4)
        {
            fail("Size not 4 elements.");
        }
    }

    /**
     * Create an acyclic graph with 4 elements and test that the topological properties of the sort are present.
     *
     * @throws GraphError if any illegal attempts to access the nodes or edges of this graph are made.
     */
    @Test
    public void testSimpleGraphTopological() throws GraphError
    {
        //Create a graph.
        ReferenceCountingTopologicalSort<Integer> intSort = new ReferenceCountingTopologicalSort<>();

        //Populate it with nodes.
        intSort.add(1);
        intSort.add(2);
        intSort.add(3);
        intSort.add(4);

        //Create some edges.
        intSort.add(1, 2);
        intSort.add(1, 3);
        intSort.add(2, 4);

        /*
              __2---4
            1 __
                3
         */

        //Get the sort.
        List<Integer> returned = intSort.getSort();

        //1 must be the first element, 2,3 and 4 must be after 1, 4 must be after 2.

        //1 must be the first element.
        if(returned.get(0) != 1)
        {
            fail("First element is not 1.");
        }

        //2, 3 and 4 are now inherently after 1.

        //4 must be after 2.
        if(returned.indexOf(4) < returned.indexOf(2))
        {
            fail("Index of 4 is before index of 2.");
        }
    }

    /**
     * Create an acyclic graph of 4 string elements and test that the topological properties are retained for
     * applications of this generic method to other data types.
     *
     * @throws GraphError if any illegal attempts to access the nodes or edges of this graph are made.
     */
    @Test
    public void testSimpleGraphGenericness() throws GraphError
    {
        //Create a graph.
        ReferenceCountingTopologicalSort<String> stringSort = new ReferenceCountingTopologicalSort<>();

        //Populate it with nodes.
        stringSort.add("First");
        stringSort.add("Second");
        stringSort.add("Third");
        stringSort.add("Fourth");

        //Create some edges.
        stringSort.add("First", "Second");
        stringSort.add("First", "Third");
        stringSort.add("Second", "Fourth");

        /*
                  __Second---Fourth
            First __
                     Third
         */

        //Get the sort.
        List<String> returned = stringSort.getSort();

        //First must be the first element, Second, Third and Fourth must be after First, Fourth must be after Second.

        //First must be the first element.
        if(!returned.get(0).equals("First"))
        {
            fail("First element is not 1.");
        }

        //Second, Third and Fourth are now inherently after First.

        //Fourth must be after Second.
        if(returned.indexOf("Fourth") < returned.indexOf("Second"))
        {
            fail("Index of Fourth is before index of Second.");
        }
    }

    /**
     * Create a cyclic graph with 4 elements and test the sorters ability to detect the cyclic nature and throw
     * an exception detailing this.
     *
     * @throws GraphError if any illegal attempts are made to access the nodes or edges of this graph
     *                      ASIDE from the testing exception which is expected.
     */
    @Test
    public void testCyclicGraphException() throws GraphError
    {
        //Create a graph.
        ReferenceCountingTopologicalSort<Integer> intSort = new ReferenceCountingTopologicalSort<>();

        //Populate it with nodes.
        intSort.add(1);
        intSort.add(2);
        intSort.add(3);
        intSort.add(4);

        //Create some edges.
        intSort.add(1, 2);
        intSort.add(1, 3);
        intSort.add(2, 4);

        //Create a cyclic edge.
        intSort.add(4, 2);

        /*
              __2<->4
            1 __
                3
         */

        //Try to return a sort, an error should be thrown.
        try
        {
            //Get the sort.
            List<Integer> returned = intSort.getSort();

            //If this point was reached then the required exception was not thrown.
            fail("Exception not thrown.");
        }
        catch(GraphError e)
        {

        }
    }
}
