import java.lang.CloneNotSupportedException;

public class Deposit extends Transaction implements Cloneable //main class derived from Transaction
{
    private double amount; //private instance variable initialized
    
    /* PRECONDITIONS: 6 data fields are accepted into a 6 arg constructor
    POSTCONDITIONS: All data fields except "amount" are contructed using the super class and the amount is initialized using a set method
    */
    
    public Deposit(int newAccountID, int newPin, double newBalance, int newTransNum, int newTransType, double newAmount) //6 arg constructor
    {
        super(newAccountID, newPin, newBalance, newTransNum, newTransType); //super class
        setAmount(newAmount); //mutator
    }
    
    /* PRECONDITIONS: executes a check method to ensure the correct transaction type
    POSTCONDITIONS: data is manipulated if valid, otherwise keep the initial conditions
    */
    
    private void isValid3() //checks the validity of the transaction type
    {
        boolean typeValidity = checkType(); //boolean value set to execute a check method
        if(typeValidity == true) //if true, balance is adjusted
        {
            setBalance(super.getBalance() + getAmount());
        }
    }
    
    /* PRECONDITIONS: executes a check on the validity of the transaction type
    POSTCONDITIONS: if the type is correct (3), returns a boolean true, otherwise false
    */
    
    public boolean checkType() //performs a check and returns a boolean  
    {
        boolean typeValidity = false; 
        if(getTransType() == 3)
        {
            typeValidity = true;
        }
        else
        {
            System.exit(1);
        }
        return typeValidity;
    }

    
    public double getAmount()  //accessor method
    {
        return amount;
    }

    public void setAmount(double amount) //mutator method 
    {
        this.amount = amount;
        isValid3();
    }
    
    /* PRECONDITIONS: n/a
    POSTCONDITIONS: outputs the following message and any errors
    */
    
    public String toString() //toString method is called to display the following
    {
        String output;
        output = "(Account Id: " + getAccountId() + " TransNum: " + getTransNum() + " TransType: " + getTransType() + " ):(Deposit) Amount: " + getAmount() + " Balance: " + getBalance();
        return output;
    }
    
    /* PRECONDITIONS: object is brought in
    POSTCONDITIONS: casted to type Deposit. Each component of the objects are compared and the appropriate boolean is returned
    */
    
    public boolean equals(Object item) //method to compare each element of the objects
    {
        boolean temp = false;
        Deposit D = (Deposit)item;
        if(super.getAccountId() == D.getAccountId() && super.getBalance() == D.getBalance() && super.getPin() == D.getPin() && super.getTransNum() == D.getTransNum() && super.getTransType() == D.getTransType() && getAmount() == D.amount )
        {
            temp = true;
        }
        return temp;
    }
    
    /* PRECONDITIONS: object is brought in
    POSTCONDITIONS: a double is initialized and eventually returned based on the comparisons of the elements
    */
    
    public double compareTo(Object item) //method to compare the values of the accountID, transNum, and amount
    {
        double result = 0;
        Deposit D = (Deposit)item;
        if(super.getAccountId() - D.accountID < 0)
        {
            result = super.getAccountId() - D.accountID;
        }
        else if(super.getAccountId() - D.accountID > 0)
        {
            result = super.getAccountId() - D.accountID;
        }
        else if(super.getAccountId() == D.accountID)
        {
            if(super.getTransNum() - D.getTransNum() < 0)
            {
                result = super.getTransNum() - D.getTransNum();
            }
            else if(super.getTransNum() - D.getTransNum() > 0)
            {
                result = super.getTransNum() - D.getTransNum();
            }
            else if(super.getTransNum() == D.getTransNum())
            {
                if(getAmount() - D.getAmount() < 0)
                {
                    result = getAmount() - D.getAmount();
                }
                else if(getAmount() - D.getAmount() > 0)
                {
                    result = getAmount() - D.getAmount();
                }
                else
                {
                    result = 0;
                }
            }
        }
        return result;
    }
    
    
    public Deposit close() throws CloneNotSupportedException
    {
        return (Deposit) super.clone();
    }
} //end
