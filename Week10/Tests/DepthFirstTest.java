import graph.AdjacencyGraph;
import graph.DepthFirstTraversal;
import graph.GraphError;
import org.junit.Test;

import static org.junit.Assert.fail;


import java.util.ArrayList;

/**
 * Created by u1661665(Joshua Pritchard) on 30/11/2018.
 * Version: 30/11/2018
 */
public class DepthFirstTest<T> extends AdjacencyGraph<T>
{

    //Specifies the number of repetitions of circularGraphTest()
    private final int NUMBER_CIRCULAR_GRAPH_TESTS = 10;

    /**
     * Creates a determined circular integer graph with 3 elements and tests a traversal of this graph for correctness.
     *
     * @throws GraphError if any attempt to access an invalid element is made.
     */
    @Test
    public void test() throws GraphError
    {
        DepthFirstTraversal<Integer> intGraph = new DepthFirstTraversal<>();

        intGraph.add(1);
        intGraph.add(2);
        intGraph.add(3);

        intGraph.add(1, 2);
        intGraph.add(2, 3);
        intGraph.add(3, 1);

        ArrayList<Integer> returnedList = (ArrayList<Integer>) intGraph.traverse();

        if (returnedList.get(0) == 1 && returnedList.get(1) == 2 && returnedList.get(2) == 3)
        {
            return;
        }
        else if (returnedList.get(1) == 1 && returnedList.get(2) == 2 && returnedList.get(0) == 3)
        {
            return;
        }
        else if(returnedList.get(2) == 1 && returnedList.get(0) == 2 && returnedList.get(1) == 3)
        {
            return;
        }
        else
        {
            fail("Correct path not returned.");
        }
    }

    /**
     * Creates a determined circular graph out of 5 elements and tests a traversal of this graph for correctness.
     *
     * @throws GraphError if any attempt to access an invalid element of the graph is made.
     */
    @Test
    public void circularGraphTest() throws GraphError
    {
        //Create a circular graphh
        DepthFirstTraversal<Integer> circularIntGraph = new DepthFirstTraversal<>();

        //Add nodes.
        circularIntGraph.add(1);
        circularIntGraph.add(2);
        circularIntGraph.add(3);
        circularIntGraph.add(4);
        circularIntGraph.add(5);

        /*
        1 2 3 4 5
         */

        //Add edges.
        circularIntGraph.add(1, 2);
        circularIntGraph.add(2, 3);
        circularIntGraph.add(3, 4);
        circularIntGraph.add(4, 5);
        circularIntGraph.add(5, 1);

        /*
            1 -> 2 -> 3 -> 4 -> 5
            ^                   |
            |___________________|
         */

        //---All test cases---
        //A search from 1 should return {1, 2, 3, 4, 5}
        //A search from 2 should return {2, 3, 4, 5, 1}
        //A search from 3 should return {3, 4, 5, 1, 2}
        //A search from 4 should return {4, 5, 1, 2, 3}
        //A search from 5 should return {5, 1, 2, 3, 4}

        //Do a number of tests to make sure all test cases are covered.
        for(int x = 0; x < NUMBER_CIRCULAR_GRAPH_TESTS; x++)
        {
            //Cast the returned set to an array list.
            ArrayList<Integer> returnedList = (ArrayList<Integer>) circularIntGraph.traverse();

            if(returnedList.get(0) == 1 && returnedList.get(1) == 2 && returnedList.get(2) == 3 && returnedList.get(3) == 4 && returnedList.get(4) == 5)
            {break;}
            else if(returnedList.get(0) == 2 && returnedList.get(1) == 3 && returnedList.get(2) == 4 && returnedList.get(3) == 5 && returnedList.get(4) == 1)
            {break;}
            else if(returnedList.get(0) == 3 && returnedList.get(1) == 4 && returnedList.get(2) == 5 && returnedList.get(3) == 1 && returnedList.get(4) == 2)
            {break;}
            else if(returnedList.get(0) == 4 && returnedList.get(1) == 5 && returnedList.get(2) == 1 && returnedList.get(3) == 2 && returnedList.get(4) == 3)
            {break;}
            else if(returnedList.get(0) == 5 && returnedList.get(1) == 1 && returnedList.get(2) == 2 && returnedList.get(3) == 3 && returnedList.get(4) == 4)
            {break;}
            else
            {
                fail("Returned list did not match any possible test case.");
            }
        }
    }

    /**
     * Creates a determined graph with 6 elements, then interconnects these to make 3 pairs. Then tests a traversal
     * of this graph for correctness based on the guaranteed difference between each pair.
     *
     * @throws GraphError if any attempt to access and invalid element of the graph is made.
     */
    @Test
    public void interconnectedPairsTest() throws GraphError
    {
        //Create a graph.
        DepthFirstTraversal<Integer> interconnectedPairsGraph = new DepthFirstTraversal<>();

        //Create 6 nodes.
        interconnectedPairsGraph.add(1);
        interconnectedPairsGraph.add(2);
        interconnectedPairsGraph.add(3);
        interconnectedPairsGraph.add(4);
        interconnectedPairsGraph.add(5);
        interconnectedPairsGraph.add(6);

        //Link 1&2, 3&4 and 5&6
        interconnectedPairsGraph.add(1, 2);
        interconnectedPairsGraph.add(2, 1);
        interconnectedPairsGraph.add(3, 4);
        interconnectedPairsGraph.add(4, 3);
        interconnectedPairsGraph.add(5, 6);
        interconnectedPairsGraph.add(6, 5);

        /*
            1<->2 3<->4 5<->6
         */


        //Traverse the graph.
        ArrayList<Integer> returnedList = (ArrayList<Integer>) interconnectedPairsGraph.traverse();

        //each pair will be adjacent in the list.
        if(Math.abs(returnedList.get(0) - returnedList.get(1)) != 1)
        {
            fail("first pair not adjacent");
        }
        else if(Math.abs((returnedList.get(2) - returnedList.get(3))) != 1)
        {
            fail("Second pair not adjacent.");
        }
        else if(Math.abs(returnedList.get(4) - returnedList.get(5)) != 1)
        {
            fail("Third pair not adjacent.");
        }
    }

    /**
     * Creates a determined circular graph with 3 string elements and tests a traversal of this graph for correctness.
     *
     * @throws GraphError if any attempt to access an invalid element of the graph is made.
     */
    @Test
    public void genericnessCircularGraphTest() throws GraphError
    {
        DepthFirstTraversal<String> stringGraph = new DepthFirstTraversal<>();

        stringGraph.add("first");
        stringGraph.add("second");
        stringGraph.add("third");

        stringGraph.add("first", "second");
        stringGraph.add("second", "third");
        stringGraph.add("third", "first");

        ArrayList<String> returnedList = (ArrayList<String>) stringGraph.traverse();

        if (returnedList.get(0).equals("first") && returnedList.get(1).equals("second") && returnedList.get(2).equals("third"))
        {
            return;
        }
        else if (returnedList.get(1).equals("first") && returnedList.get(2).equals("second") && returnedList.get(0).equals("third"))
        {
            return;
        }
        else if(returnedList.get(2).equals("first") && returnedList.get(0).equals("second") && returnedList.get(1).equals("third"))
        {
            return;
        }
        else
        {
            fail("Correct path not returned with a string graph");
        }
    }
}
