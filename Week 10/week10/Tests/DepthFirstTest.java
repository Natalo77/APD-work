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

    /*
    circular graph test.
    think of simple graph structures.
        3 sets of interconnected node pairs.
    graph structures with easy testable properties.
    also put in doc about challenging to test this.
     */

}
