package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by u1661665(Joshua Pritchard) on 10/12/2018.
 * Version: 10/12/2018
 */
public class ReferenceCountingTopologicalSort<T> extends AdjacencyGraph<T> implements TopologicalSort<T>
{
    private HashMap<T, Integer> nodes;
    private ArrayList<T> sorted;

    public ReferenceCountingTopologicalSort()
    {
        this.nodes = new HashMap<>();
        this.sorted = new ArrayList<>();
    }

    @Override
    public List<T> getSort() throws GraphError
    {
        populateNodesMap();

        //Get an initial node to add.
        T noPredecessors = getNoPredecessorNode();

        //If there are still nodes unsorted.
        while(noPredecessors != null)
        {
            //Add it to the topological sort.
            sorted.add(noPredecessors);

            //Remove the node from the graph.
            removeFromGraph(noPredecessors);

            //Get another node with no predecessors
            noPredecessors = getNoPredecessorNode();
        }

        //TODO: if sorted not same size as nodes as graph then not acyclic.

        //Return the topological sort.
        return sorted;
    }

    private void populateNodesMap() throws GraphError
    {
        //TODO: Two stage. 1. set up every node in hashmap. 2. Neighbours iteration.

        //Used for keeping track of the reference count of the current node being calculated.
        int referenceCount;

        //Go through each node in the graph.
        for(T node: getNodes())
        {
            //Reset the reference count tracker.
            referenceCount = 0;

            //Check the node against all other nodes in the graph.
            for(T otherNode : getNodes())
            {
                //...Ignoring itself.
                //And if the other node contains an edge leading to the node being calculated.
                if(otherNode != node && getNeighbours(otherNode).contains(node))
                {
                    //Increase the reference count tracker for the node being calculated.
                    referenceCount++;
                }
            }

            //Add the calculated node along with its reference count to the hashmap.
            nodes.put(node, referenceCount);
        }
    }

    private T getNoPredecessorNode()
    {
        //For each node in the graph.
        for(T node : getNodes())
        {
            //If it is unsorted, and has a reference count of 0...
            if(nodes.get(node) == 0 && !sorted.contains(node))
            {
                //Return it
                return node;
            }
        }

        //Base return null (Should never occur if this method is used in accordance with a correct
        // reference counting topological sort algorithn.
        return null;
    }

    private void removeFromGraph(T node) throws GraphError
    {
        //For each successor of the passed in node.
        for(T successor: getNeighbours(node))
        {
            //Decrement the reference Count held against it in the hashmap by 1.
            nodes.replace(successor, nodes.get(successor) - 1);
        }
    }
}
