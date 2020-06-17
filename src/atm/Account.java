
package atm;

/**
 *  @Author: Lesly Brisson 
 *    @Date: 06/01/20 
 * @Updated: 06/01/20 
 *@FileName: ATM.java 
 * @Purpose: Defines Account functions for ATM program
 */


public class Account {
   
    private double balance;
    private int withdrawals;
    private final double SERVICE_CHARGE = 1.5;
  
    // Default Constructor 
    public Account() {
        balance = 0.00;
        withdrawals = 0;
    }
      
    
    public void withdraw(double amount) throws InsufficientFunds {
        withdrawals++; 
        double serviceCharges = getServiceCharges();
      
        if(balance < amount + serviceCharges)
            throw new InsufficientFunds();
            balance = balance - amount - serviceCharges;
    }
  
   
    public void deposit(double amount) {
        balance = balance + amount;
    } 
  
    
    public void transfer(double amount, Account targetAccount) throws InsufficientFunds {
        if(balance < amount)
            throw new InsufficientFunds();
            balance = balance - amount;
            targetAccount.deposit(amount);      
    } 

   
    public double getBalance() {
        return balance;
    } 
  
    private double getServiceCharges() throws InsufficientFunds {
        if(withdrawals > 4) {
            if(balance - SERVICE_CHARGE < 0)
                throw new InsufficientFunds();  
          
                return balance - SERVICE_CHARGE;
        }    
        return 0;
    }
} 
