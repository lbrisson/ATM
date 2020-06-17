package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *  @Author: Lesly Brisson 
 *    @Date: 06/01/20 
 * @Updated: 06/01/20 
 *@FileName: ATM.java 
 * @Purpose: Defines GUI Design for ATM program
 */


public class ATM extends JFrame implements ActionListener {
  
   private ButtonGroup buttonsGroup = new ButtonGroup();
   private JButton jbuttWithdraw = new JButton("Withdraw");
   private JButton jbuttDeposit = new JButton("Deposit");
   private JButton jbuttTransfer = new JButton("Transfer To");
   private JButton jbuttBalance = new JButton("Balance");
   private JTextField jtfAmount = new JTextField(20);
  
   private Account activeAccount = new Account();
   private Account inactiveAccount = new Account();
   private Account checkingAccount = new Account();
   private Account savingsAccount = new Account();
   
   private JPanel buttonsPanel = new JPanel();
   private JPanel mainPanel = new JPanel();
  
   private ButtonGroup radioButtGroup = new ButtonGroup();  
      
   
   //Default Constructor 
   public ATM() {      
       mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 10));
       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
      
       jtfAmount.setHorizontalAlignment(JTextField.RIGHT);
      
       mainPanel.add(jtfAmount);
       add(mainPanel, BorderLayout.SOUTH);
      
       radioButtGroup.add(checkingRadioButton);
       radioButtGroup.add(savingsRadioButton);      
      
       buttonsGroup.add(jbuttWithdraw);
       buttonsGroup.add(jbuttDeposit);
       buttonsGroup.add(jbuttBalance);
       buttonsGroup.add(jbuttTransfer);      
      
       buttonsPanel.setLayout(new GridLayout(3, 2, 10, 10));
       buttonsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 0, 15));
      
       buttonsPanel.add(jbuttWithdraw);      
       buttonsPanel.add(jbuttDeposit);  
       buttonsPanel.add(jbuttTransfer); 
       buttonsPanel.add(jbuttBalance);
       buttonsPanel.add(savingsRadioButton);      
       buttonsPanel.add(checkingRadioButton);
              
       add(buttonsPanel, BorderLayout.CENTER);
      
       checkingRadioButton.addActionListener(this);
       savingsRadioButton.addActionListener(this);
       jbuttBalance.addActionListener(this);   
       jbuttTransfer.addActionListener(this);
       jbuttWithdraw.addActionListener(this);
       jbuttDeposit.addActionListener(this);
   }
   
   public JRadioButton checkingRadioButton = new JRadioButton(new AbstractAction("Checking") {
       public void actionPerformed(ActionEvent checking) {
           if(checkingRadioButton.isSelected()) {
               activeAccount = checkingAccount;
               inactiveAccount = savingsAccount;
           }
       }
   }); 
  
   
   public JRadioButton savingsRadioButton = new JRadioButton(new AbstractAction("Savings") {
       public void actionPerformed(ActionEvent savings) {
           if(savingsRadioButton.isSelected()) {
               activeAccount = savingsAccount;
               inactiveAccount = checkingAccount;
           }
       }
   }); 
   
       
   public void actionPerformed(ActionEvent ae) {
       
       Object source = ae.getSource();
       if(source == jbuttWithdraw) {
           try {
               String str = jtfAmount.getText();
	       double amount = Double.parseDouble(str);
               if(amount < 0) {
                   JOptionPane.showMessageDialog(null, "Amount must be a positive value. Try Again");
	       } else if(amount % 20 == 0) {
                   activeAccount.withdraw(amount);
                   JOptionPane.showMessageDialog(null, "Amount is withdrawn successfully. Thank You");
	       } else {
                   JOptionPane.showMessageDialog(null, "Amount must be in increments of 20");
               }
           }
           catch(NumberFormatException nfe) {
               JOptionPane.showMessageDialog(null, "Amount must be a numeric value. Thank You");
           }
           catch(InsufficientFunds ife) {
               JOptionPane.showMessageDialog(null, "Sorry, Insufficient funds!");
           }
       } else if(source == jbuttDeposit) {          
           try {
               String str = jtfAmount.getText();
               double amount = Double.parseDouble(str);
               if(amount < 0) {
                   JOptionPane.showMessageDialog(null, "Amount must be a positive value. Try Again.");
               } else {
                   activeAccount.deposit(amount);
                   JOptionPane.showMessageDialog(null, "Amount is deposited successfully, Thank You");
               }
           } catch(NumberFormatException nfe) {
               JOptionPane.showMessageDialog(null, "Amount must be a numeric value. Thank You");
           }
           
       } else if(source == jbuttTransfer) {
           try {
               String str = jtfAmount.getText();
               double amount = Double.parseDouble(str);
               if(amount < 0) {
                   JOptionPane.showMessageDialog(null, "Amount must be a positive value please!");
               } else {
                   activeAccount.transfer(amount, inactiveAccount);
                   JOptionPane.showMessageDialog(null, "Amount is transferred successfully, Thank you!");
               }
           } catch(NumberFormatException nfe) {
               JOptionPane.showMessageDialog(null, "Sorry, Amount must be a numeric value");
           } catch(InsufficientFunds ex) {
               JOptionPane.showMessageDialog(null, "Sorry, Insufficient funds!");
           }
       } else if(source == jbuttBalance) {
           JOptionPane.showMessageDialog(null, activeAccount.getBalance());
       }      
   } 

   
   public static void main(String[] args) {
       ATM frame = new ATM();      
       frame.setTitle("ATM Machine");
       frame.setSize(400, 300);
       frame.setResizable(false);
       frame.setVisible(true);      
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}