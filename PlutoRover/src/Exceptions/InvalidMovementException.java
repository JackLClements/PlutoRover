/*
 */
package Exceptions;

/**
 *
 * @author Jack L. Clements
 */
public class InvalidMovementException extends Exception{
    public InvalidMovementException(){
        super("Invalid move, movement must be Foward (F), Backward (B), Left (L) or Right (R).");
    }
    
    public InvalidMovementException(char invalidMove){
        super("Invalid move,\"" + invalidMove+ "\", movement must be Foward (F), Backward (B), Left (L) or Right (R).");
    }
    
    public InvalidMovementException(String message){
        super(message);
    }
    
    
    
    
}
