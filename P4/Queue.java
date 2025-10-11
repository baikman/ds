package P4;

/**
* This interface describes the basic methods that all queues must implement.
*
* @author Brandon Aikman
* @version 1.0
* File: Queue.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This interface describes the basic methods that all queues must implement.
*/

public interface Queue<E> {
    public void enqueue(E element) throws InvalidDataException;
    public E dequeue() throws QueueEmptyException;
    public E front() throws QueueEmptyException;
    public int size();
    public boolean isEmpty();
}