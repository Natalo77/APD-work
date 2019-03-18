package graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DepthFirstSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T> {

	Stack<T> sort; // contains the traversal
	Set<T> visited; // nodes that have been, or are being visited
	
	@Override
	/**
	 * Perform a topological sort on the graph.
	 * Note: a Java Stack, somewhat counterintuitively, has the top of the stack (the most recent
	 * addition) as the rightmost entry when listed.  To achieve the ordering we want it is necessary
	 * to reverse the final result.
	 */
	public List<T> sort() throws GraphError {
		sort = new Stack<T>(); // start a new sort
		visited = new HashSet<T>(); // no nodes as yet visited
		for (T node: getNodes()) { // for all the nodes in the graph
			if (!visited.contains(node)) { // if this node has not yet been visited
				visitNode(node); // continue the sort starting at this node
			}
		}
		Collections.reverse(sort); // reverse the sort to have the top entry at the left
		return sort;
	}
	
	/**
	 * Visit a node by adding it to the traversal, and then recursively visiting all its neighbours.
	 * @param node the node to be visited
	 * @throws GraphError if the node does not exist in this graph
	 */
	private void visitNode(T node) throws GraphError {
		if (visited.contains(node)) return;  // if the node has been, or is being visited no need to do anything
		visited.add(node); // we are now visiting this node
		for (T neighbour: neighbours(node)) { // continue the sort recursively in all the node's neighbours...
			visitNode(neighbour); // ...so that all its descendants are added to the sort before it is
		}
		sort.push(node); // add this node to the front of the traversal now that all its descendants have been added
	}
}