package graph;

import java.util.*;

/**
 * Created by u1661665(Joshua Pritchard) on 30/11/2018.
 * Version: 30/11/2018
 */
public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T>
{
    /**
     * Used to hold the list in which the nodes of the graph are traversed.
     */
    ArrayList<T> traversal;

    /**
     * A constructor for a depth first traversal that initializes the data structure used for the traversal list.
     */
    public DepthFirstTraversal()
    {
        traversal = new ArrayList<>();
    }


    /**
     * An implementation of the traverse() method defined in Traversal.
     *
     * @return Returns a list of nodes in the order of which they were found during traversal.
     * @throws GraphError if the graph given to the traversal is empty.
     */
    @Override
    public List<T> traverse() throws GraphError
    {
        //This is used to ensure the existence of at least one node.
        if(getNoOfNodes() == 0)
            throw new GraphError("Graph is empty.");


        //At least one node exists so this can be used safely.
        T node = getUnvisitedNode();
        do
        {
            //Begin the population of the traversal list from the chosen node.
            populateTraversal(getUnvisitedNode());

            //Check for any more unvisited nodes.
            node = getUnvisitedNode();

        //Repeat if another unvisited node was found after the first pass.
        }while(node != null);

        //Return the now populated traversal list.
        return traversal;
    }

    /**
     * A recursive method to find and populate the traversal list for graph based on the initial T node.
     *
     * @param node the node to begin the traversal and search from.
     * @throws GraphError
     */
    private void populateTraversal(T node) throws GraphError
    {
        //If the node has not already been visited.
        if(!traversal.contains(node))
        {
            //Record the node as visited and add it to the traversal list.
            traversal.add(node);

            //For each of its neighbours, run this method again.
            for(T neighbour:getNeighbours(node))
            {
                populateTraversal(neighbour);
            }
        }
    }

    /**
     * Get an unvisited node from the graphs set of nodes.
     *
     * @return a T node within the graph that has been unvisited according to the traversal list in this class. OR null
     * if no unvisited node has been found./
     * if no unvisited node has been found.
     */
    private T getUnvisitedNode()
    {
        //For each node in the set of nodes.
        for(T node : getNodes())
        {
            //If it is unvisited, return this node.
            if(!traversal.contains(node))
                return node;
        }

        //Default case for when no node is found.
        return null;
    }
}
