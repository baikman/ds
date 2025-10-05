package P3;

/**
* This class extends the RuntimeException class to handle incorrect strings.
*
* @author Brandon Aikman
* @version 1.0
* File: InvalidRPNString.java
* Created: Oct 2025
* Summary of Modifications: Initial version
* ©Copyright Cedarville University, its Computer Science faculty, and the author.
*
* Description: This class extends the RuntimeException class to handle incorrect strings.
*/

public class InvalidRPNString extends RuntimeException {
    public InvalidRPNString(String msg) {
        super(msg);
    }
}