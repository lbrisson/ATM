package atm;

/**
 *  @Author: Lesly Brisson 
 *    @Date: 06/01/20 
 * @Updated: 06/01/20 
 *@FileName: ATM.java 
 * @Purpose: Class that handles Insufficient Funds Error for Program
 */

public class InsufficientFunds extends Exception {
    
    public InsufficientFunds() {
        super();
    }

   
    public InsufficientFunds(String message) {
        super(message);
    }   
}   
