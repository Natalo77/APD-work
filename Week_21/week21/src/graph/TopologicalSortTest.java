package graph;
/**
 * The test class DepthFirstTraversalTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */


import graph.GraphError;

import java.util.List;

public class TopologicalSortTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class DepthFirstTraversalTest
     */
    public TopologicalSortTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }

    /**
     * Given an empty (topological sort) graph will add nodes and links to create the graph
     * used as an example in the topological sort lecture (week 110
     */
    public TopologicalSort<Integer> buildLectureGraph(TopologicalSort<Integer> graph) throws GraphError {
        graph.add(0);
        graph.add(1);
        graph.add(2);
        graph.add(3);
        graph.add(4);
        graph.add(5);
        graph.add(6);
        graph.add(7);
        graph.add(8);
        graph.add(9);
        graph.add(0, 1);
        graph.add(0, 5);
        graph.add(1, 7);
        graph.add(3, 2);
        graph.add(3, 4);
        graph.add(3, 8);
        graph.add(6, 0);
        graph.add(6, 1);
        graph.add(6, 2);
        graph.add(8, 7);
        graph.add(8, 4);
        return graph;
    }
    
    /**
     * Test whether the (topological sort) graph does create a correct topological sort.
     */
    public <T> void testGraph(TopologicalSort<T> graph) throws GraphError
    {
    	// Get the graph's topological sort
        List<T> traversal = graph.sort();
        // Put your tester code here...
        // It should check that the traversal has the correct properties
        // required of a topological sort for the given graph.
        // See the topological sort lecture notes (week 11) for ideas on 
        // what some of these properties might be.
    }
}