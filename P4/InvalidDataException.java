package P4;

/**
* This class extends the RuntimeException class to handle invalid data for the ArrayQueue class.
*
* @author Brandon Aikman
* @version 1.0
* File: InvalidDataException.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class extends the RuntimeException class to handle invalid data.
*/

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg) {
        super(msg);
    }
}