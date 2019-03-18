package graph;

/**
 * A demonstration of how to use the topological sort test class.
 * 
 * This test suite only contains one test.  Clearly, there should be more, testing a range of 
 * acyclic graphs.
 * 
 */
public class DepthFirstTest extends TopologicalSortTest {
	
	public void testLectureGraphDepthFirst() throws GraphError {
		TopologicalSort<Integer> lectureGraph = new DepthFirstSort<Integer>();
		lectureGraph = buildLectureGraph(lectureGraph);
		testGraph(lectureGraph);
	}

}
