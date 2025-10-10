package P4;

/**
* This class extends the RuntimeException class to handle empty queue errors for the ArrayQueue class.
*
* @author Brandon Aikman
* @version 1.0
* File: QueueEmptyException.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class extends the RuntimeException class to handle empty queue errors.
*/

public class QueueEmptyException extends RuntimeException {
    public QueueEmptyException(String msg) {
        super(msg);
    }
}