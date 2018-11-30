package graph;

import java.util.*;

/**
 * Created by u1661665(Joshua Pritchard) on 30/11/2018.
 * Version: 30/11/2018
 */
public class DepthFirstTraversal<T> extends AdjacencyGraph<T> implements Traversal<T>
{
    ArrayList<T> visited;
    LinkedList<T> toDo;
    ArrayList<T> traversal;
    ArrayList<T> nodes;

    public DepthFirstTraversal()
    {
        visited = new ArrayList<>();
        toDo = new LinkedList<>();
        traversal = new ArrayList<>();
        nodes = new ArrayList<>();
    }


    @Override
    public List<T> traverse() throws GraphError
    {
        if(getNoOfNodes() == 0)
        {
            throw new GraphError("Graph is empty.");
        }

        for(T obj: getNodes())
        {
            nodes.add(obj);
        }

        populateTraversal(nodes.get(0));

        return traversal;
    }

    private void populateTraversal(T node) throws GraphError
    {
        traversal.add(node);

        if(!visited.contains(node))
        {
            visited.add(node);
            for(T neighbour:getNeighbours(node))
            {
                toDo.add(neighbour);
            }

        }

        while(toDo.size() > 0)
        {
            populateTraversal(toDo.poll());
        }
    }
}
