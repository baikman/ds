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

    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayQueue(int newCapacity) {
        arr = (E[])new Object[newCapacity];
    }
    
    public void enqueue(E element) throws InvalidDataException {
        if (!(element instanceof E)) throw new InvalidDataException("invalid data test");
        if (size == arr.length) {
            E[] newArr = (E[])new Object[arr.length * 2];
            for (int i = 0; i < size; i++) newArr[i] = arr[i];
            arr = newArr;
            for (int i = size; i > 0; i--) arr[i+1] = arr[i];
            arr[0] = element;
        } else {
            int next = (front + size) % arr.length;
            arr[next] = element;
        }
        
        size++;
    }

    public E dequeue() throws QueueEmptyException {
        if (isEmpty()) throw new QueueEmptyException("tried to deque on empty queue");
        E remove = arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;
        size--;
        return remove;
    }

    public E front() throws QueueEmptyException {
        if (isEmpty()) throw new QueueEmptyException("tried to yield front on empty queue");
        return arr[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return (size == 0);
    }
}