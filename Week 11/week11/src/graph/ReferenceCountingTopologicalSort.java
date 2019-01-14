package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by u1661665(Joshua Pritchard) on 10/12/2018.
 * Version: 14/01/2019
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

        //if sorted not same size as nodes as graph then not acyclic.
        if(sorted.size() != getNoOfNodes())
        {
            throw new GraphError("Graph not acyclic");
        }

        //Return the topological sort.
        return sorted;
    }

    private void populateNodesMap() throws GraphError
    {
        /*
        //---OLD IMPLEMENTATION---
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
        //---END OF OLD IMPLEMENTATION---
        */


        //Two stage operation.

        //Add each node to the hashmap.
        for(T node: getNodes())
        {
            nodes.put(node, 0);
        }

        //For each node.
        for(T node: getNodes())
        {
            //For each neighbour
            for(T neighbour: getNeighbours(node))
            {
                //Increase the reference count stored against it by 1.
                nodes.replace(node, nodes.get(node) + 1);
            }
        }
    }

    private T getNoPredecessorNode()
    {
        //TODO: Look at this algorithm reaching a point where no ref 0 node can be found.

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
        // reference counting topological sort algorithm).
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
