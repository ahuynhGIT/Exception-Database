import java.lang.CloneNotSupportedException;

class Account implements Cloneable//main class
{
  protected int accountID = 9999; //protected instance variables for the three data fields
  protected int pin = 9999;
  protected double balance = 0.0;
  
  
/* PRECONDITIONS: 3 inputs for the account information are brought in
 * POSTCONDITIONS: mutator methods are used to initialize the data into the private instance variables */
  
  public Account(int newAccountID, int newPin, double newBalance) // 3 arg constructor to initialize the 3 data fields and checks its validity
  {
    setAccountId(newAccountID);
    setPin(newPin);
    setBalance(newBalance);
  }
  
/* PRECONDITIONS: executes 3 check methods to validate the data
 * POSTCONDITIONS: data is manipulated if invalid entries, otherwise keep the initial conditions */
  
  private void isValid() //checks validity and manipulates data accordingly
  {
    boolean accountValidity = checkAccountID(); //boolean values begin validation
    boolean pinValidity = checkPin();
    boolean balanceValidity = checkBalance();
    if(accountValidity == false) //if the account number is invalid, it is set to 9999 and the error message is displayed
    {
      System.out.println("Invalid Account Number - Account is set to 9999");
      setAccountId(9999);
    }
    if(pinValidity == false) //if the pin number is invalid, it's set to 9999 and the error message is displayed
    {
      System.out.println("Invalid Pin Number - Pin is set to 9999");
      setPin(9999);
    }
    if(balanceValidity == false) //if the balance is invalid, it's set to 0 and the error message is displayed
    {
      System.out.println("Invalid Balance - Balance is set to 0.0");
      setBalance(0.0);
    }
  }
  
/* PRECONDITIONS: accountID integer brought in, boolean is initialized
 * POSTCONDITIONS: boolean is returned based on validity */
  
  private boolean checkAccountID() //if the account number is out of bounds, it's validity will be set as false (invalid)
  {
    boolean accountIDvalidity;
    if(getAccountId() < 1000 || getAccountId() > 9999)
    {
      accountIDvalidity = false;
    }
    else
    {
      accountIDvalidity = true;
    }
    return accountIDvalidity;
  }
  
/* PRECONDITIONS: pin integer brought in, boolean is initialized
 * POSTCONDITIONS: boolean is returned based on validity */
    
  private boolean checkPin() //if the pin number is out of bounds, it's validity willbe set as false
  {
    boolean pinvalidity = true;
    if(getPin() < 1000 || getPin() > 9999)
    {
      pinvalidity = false;
    }
    else
    {
      pinvalidity = true;
    }
  return pinvalidity;
  }
  
/* PRECONDITIONS:  balance double brought in, boolean is initialized
 * POSTCONDITIONS: boolean is returned based on validity */

  private boolean checkBalance() //if the balance is out of bounds (negative), it's validity will be false
  {
    boolean balancevalidity;
    if(getBalance() < 0)
    {
      balancevalidity = false;
    }
    else
    {
      balancevalidity = true;
    }
    return balancevalidity;
  }
  
  public int getAccountId() //accessor methods
  {
    return accountID;
  }

  public int getPin()
  {
    return pin;
  }
  
  public double getBalance()
  {
    return balance;
  }
  
  public void setAccountId(int newAccountID) //mutator methods with validation
  {
    accountID = newAccountID;
    isValid();
  }
  
  public void setPin(int newPin)
  {
    pin = newPin;
    isValid();
  }
  
  public void setBalance(double newBalance)
  {
    balance = newBalance;
    isValid();
  }
  
/* PRECONDITIONS: n/a
 * POSTCONDITIONS: displays the account information and any necessary errors */
  
  public String toString() //toString method to output the information
  {
    String output = "ID: " + getAccountId() + " Pin: " + getPin() + " Balance: $" + getBalance();
    return output;
  }
  
  public Account close() throws CloneNotSupportedException
  {
      return (Account) super.clone();
  }
} //end

 

