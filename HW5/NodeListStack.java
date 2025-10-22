package HW5;

/**
* This class builds a stack using a NodePositionList.
*
* @author Brandon Aikman
* @version 1.0
* File: NodeListStack.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class builds a stack using a NodePositionList.
*/

public class NodeListStack<E> {
    private NodePositionList<E> list;

    /**
     * NodeListStack constructor.
     *
     */
    public NodeListStack() {
        list = new NodePositionList<>();
    }

    /**
     * push method, which pushes an element on the stack.
     *
     * @param element Element to push on the stack.
     */
    public void push (E element) {
        list.addLast(element);
    }

    /**
     * pop method, which pops and returns the top item on the stack.
     * 
     * @return E Top item from stack
     */
    public E pop() throws StackEmptyException {
        if (list.isEmpty()) throw new StackEmptyException("Cannot pop from an empty stack.");
        return list.remove(list.last());
    }

    /**
     * top method, which returns the top item on the stack.
     * 
     * @return E Top item from stack
     */
    public E top() throws StackEmptyException {
        if (isEmpty()) throw new StackEmptyException("Cannot view top of empty stack.");
        Position<E> lastP = list.last();
        return lastP.getElement();
    }

    /**
     * size method, which returns the size of the stack.
     * 
     * @return int Size of stack
     */
    public int size() {
        return list.size();
    }

    /**
     * isEmpty method, which returns if the stack is empty or not.
     * 
     * @return boolean Truth value of the stack's emptiness.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}