/*
 */
package Exceptions;

/**
 *
 * @author Jack L. Clements
 */
public class InvalidHeaderException extends Exception {
    public InvalidHeaderException(){
        super("Invalid direction, heading must be North (N), East (E), South (S) or West (W)");
    }
    
    public InvalidHeaderException(char invalidHeading){
        super("Invalid direction,\"" + invalidHeading+ "\", heading must be North (N), East (E), South (S) or West (W)");
    }
    
    public InvalidHeaderException(String message){
        super(message);
    }
    
}
