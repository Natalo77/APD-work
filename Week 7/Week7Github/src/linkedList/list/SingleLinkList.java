package linkedList.list;

import linkedList.node.SingleLinkNode;

/**
 * Created by u1661665(Joshua Pritchard) on 14/11/2018.
 * Version: 19/11/2018
 */
public class SingleLinkList<T> extends BasicList<SingleLinkNode<T>, T> implements List<T>
{
    /**
     * Used to record the size of the list.
     */
    private int size;

    /**
     * Create an empty SingleLinkList.
     */
    public SingleLinkList()
    {
        root = null;
        size = 0;
    }

    /**
     * Create a SingleLinkList with one node (the value passed in).
     *
     * @param value the value with which to create the root node of the SingleLinkList.
     */
    public SingleLinkList(T value)
    {
        root = new SingleLinkNode<T>(value);
        size = 1;
    }

    /**
     * Returns the size of the list.
     *
     * @return the size of the list.
     */
    public int getSize()
    {
        return this.size;
    }

    /**
     * Adds a new Value to the SingleLinkList at the index specified.
     *
     * @param index the index at which the new entry should be added.
     * @param value the value to be added.
     * @throws ListAccessError if the index is invalid in respect to the size of the list.
     */
    @Override
    public void add(int index, T value) throws ListAccessError
    {
        //Make sure the index trying to be accessed is not invalid.
        if(index < 0 || index > size) { throw new ListAccessError("Invalid index location " + index); }

        //Increase the recorded size of the list.
        size++;

        //Special case for adding to the root.
        if(index == 0)
        {
            setRoot(new SingleLinkNode<T>(value, getRoot()));
            return;
        }

        //Initial states for finding the previous and next elements (the case for adding at index 1)
        SingleLinkNode<T> previous = getRoot();

        /*
        If not adding at index 2:
        . Go through the list until the correct element prior to the element to be added is found.
        . Then find the element after this.
        . These two nodes will be used as the previous and next element for the element to be added.
         */
        for(int x = 2; x <= index; x++)
        {
            previous = previous.getNext();
        }
        SingleLinkNode<T> nextAfterNew = previous.getNext();

        //Create the new listNode and set up its next element.
        SingleLinkNode<T> newNode = new SingleLinkNode<>(value, nextAfterNew);

        //Correct the next element of the previousNode.
        previous.setNext(newNode);
    }

    /**
     * Remove the element at the specified index and return the value.
     *
     * @param index the index of the entry to be removed.
     * @return the value of the element removed.
     * @throws ListAccessError if the index is invalid in respect to the size of the list.
     */
    @Override
    public T remove(int index) throws ListAccessError
    {
        //Make sure the index trying to be accessed is not invalid.
        if(index < 0 || index >= size) { throw new ListAccessError("Invalid index location " + index); }

        //Initial states for finding the previous and the new index for the previous to point to
        //(the case for removing the root node).
        SingleLinkNode<T> previous = null;
        SingleLinkNode<T> previousNewNext = getRoot().getNext();

        //Get the value being removed before it becomes inaccessible via list modification.
        T removed = get(index);

        //The special case for removing the root node.
        if(index == 0)
        {
            setRoot(previousNewNext);
            size--;
            return removed;
        }

        //If the root node is not the one to be removed, set up basic conditions for removing an internal element.
        previous = getRoot();
        previousNewNext = previousNewNext.getNext();

        //Cycle through the list to find the correct values for previous and previousNewNext.
        for(int x = 1; x < index; x++)
        {
            previous = previous.getNext();
            previousNewNext = previousNewNext.getNext();
        }

        //Re-arrange the pointer for the previous element, and return the removed element.
        previous.setNext(previousNewNext);
        size--;
        return removed;
    }

    /**
     * Return the value of the element in the list at the index specified.
     *
     * @param index the index of the entry to be accessed.
     * @return the value of the element at the index specified.
     * @throws ListAccessError if the index is invalid in respect to the size of the list.
     */
    @Override
    public T get(int index) throws ListAccessError
    {
        //Make sure the index trying to be accessed is not invalid.
        if(index < 0 || index > size) { throw new ListAccessError("Invalid index location " + index); }

        //Set up a storage variable for the node being accessed.
        SingleLinkNode<T> get = getRoot();

        //Cycle through the list until the correct element has been found.
        for(int x = 1; x <= index; x++)
        {
            get = get.getNext();
        }

        //Return the element's value.
        return get.getValue();
    }
}
