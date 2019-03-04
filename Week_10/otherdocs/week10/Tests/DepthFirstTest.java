import graph.AdjacencyGraph;
import graph.DepthFirstTraversal;
import graph.GraphError;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

/**
 * Created by u1661665(Joshua Pritchard) on 30/11/2018.
 * Version: 30/11/2018
 */
public class DepthFirstTest<T> extends AdjacencyGraph<T>
{

    private final int NUMBER_CIRCULAR_GRAPH_TESTS = 10;

    @Test
    void test() throws GraphError
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

    @Test
    void circularGraphTest() throws GraphError
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
            {}
            else if(returnedList.get(0) == 2 && returnedList.get(1) == 3 && returnedList.get(2) == 4 && returnedList.get(3) == 5 && returnedList.get(4) == 1)
            {}
            else if(returnedList.get(0) == 3 && returnedList.get(1) == 4 && returnedList.get(2) == 5 && returnedList.get(3) == 1 && returnedList.get(4) == 2)
            {}
            else if(returnedList.get(0) == 4 && returnedList.get(1) == 5 && returnedList.get(2) == 1 && returnedList.get(3) == 2 && returnedList.get(4) == 3)
            {}
            else if(returnedList.get(0) == 5 && returnedList.get(1) == 1 && returnedList.get(2) == 2 && returnedList.get(3) == 3 && returnedList.get(4) == 4)
            {}
            else
            {
                fail("Returned list did not match any possible test case.");
            }
        }
    }

    /*
    think of simple graph structures.
        3 sets of interconnected node pairs.
    graph structures with easy testable properties.
    also put in doc about challenging to test this.
     */

}
