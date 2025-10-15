package P4;

/**
* This class implements an array-based queue, implementing the Queue interface.
*
* @author Brandon Aikman
* @version 1.0
* File: ArrayQueue.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class implements an array-based queue extending the Queue interface.
*/

public class ArrayQueue<E> implements Queue<E> {
    // Static variable
    private static final int DEFAULT_CAPACITY = 8;

    // Instance variables
    private E[] arr;
    private int front = 0;
    private int size = 0;

    /**
     * ArrayQueue constructor, which constructs an array based on the DEFAULT_CAPACITY (8).
     *
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * ArrayQueue constructor, which constructs an array based on integer capacity passed to it.
     * 
     * @param newCapacity new capacity for queue.
     */
    public ArrayQueue(int newCapacity) {
        arr = (E[])new Object[newCapacity];
    }
    
    /**
     * enqueue method, which enqueues a new element onto the queue.
     *
     * @param element Element to enque.
     */
    public void enqueue(E element) throws InvalidDataException {
        if (element == null) throw new InvalidDataException("Tried inserting null element.");
        if (size == arr.length) {
            int i = front;
            E[] newArr = (E[])new Object[arr.length * 2];
            for (i = 0; i < size; i++) {
                newArr[i] = dequeue();
                size++;
            }
            arr = newArr;
            arr[size] = element;
            front = 0;
        } else {
            int next = (front + size) % arr.length;
            arr[next] = element;
        }
        size++;
    }

    /**
     * dequeue method, which returns and dequeues the last element off the queue.
     *
     * @return E returns element dequeued.
     */
    public E dequeue() throws QueueEmptyException {
        if (isEmpty()) throw new QueueEmptyException("Tried to deque on empty queue.");
        E remove = arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        size--;
        return remove;
    }

    /**
     * front method, which returns the last element from the queue.
     *
     * @return E returns front element.
     */
    public E front() throws QueueEmptyException {
        if (isEmpty()) throw new QueueEmptyException("Tried to front on empty queue.");
        return arr[front];
    }

    /**
     * size method, which returns the size of the queue.
     *
     * @return int returns queue size.
     */
    public int size() {
        return size;
    }

     /**
     * isEmpty method, which returns the status of the queue;s emptiness.
     *
     * @return boolean returns condition of emtpiness of queue.
     */
    public boolean isEmpty() {
        return (size == 0);
    }
}