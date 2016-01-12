import java.lang.CloneNotSupportedException;

public class Withdrawal extends Transaction implements Cloneable //main class derived from Transaction
{
    private double amount; //private instance variables
    private double fee;

/* PRECONDITIONS: 6 data fields brought in and accepted by 6 arg constructor
    POSTCONDITIONS: All data fields except "amount" are constructed using the super class and the amount is intialized using a set method
    */    

public Withdrawal(int newAccountID, int newPin, double newBalance, int newTransNum, int newTransType, double newAmount) //6 arg constructor
{
    super(newAccountID, newPin, newBalance, newTransNum, newTransType); //super class
    setAmount(newAmount); //set method
}

/* PRECONDITIONS: when called, checks if there are enough available funds to withdraw as well as checks the transaction type
POSTCONDITIONS: data is manipulated if both data are valid, otherwise keep initial conditions
*/

private void isValid3() //checks the validity of the amount withdrawn (if enough funds are available) and check transaction type
{
    boolean amountValidity = checkAmount();
    boolean typeValidity = checkType();
    if(amountValidity == true)
    {
        if(typeValidity == true)
        {
            setBalance(super.getBalance() - getAmount() - getFee()); //if both are valid, balance is adjust accordingly
        }
    }   
}

/*PRECONDITIONS: executes a check on the balance if funds are available to be withdrawn
POSTCONDITIONS: if desired withdraw amount exceeds the balance, error message is displayed. Otherwise, withdraw the amount as well as the fee
*/


private boolean checkAmount()
{
    boolean amountValidity = true;
    if(super.getBalance() - getAmount() < 0)
    {
        System.out.println("Withdrawal denied -- not enough balance.");
        amountValidity = false;
        checkFee(); 
    }
    if(super.getBalance() - getAmount() >= 0)
    {
        amountValidity = true;
        checkFee();
    }
    return amountValidity;
}

/*PRECONDITIONS: performs a check on the transaction type
POSTCONDITIONS: if the type is 2, returns that it is valid. Otherwise, exit the program
*/

 public boolean checkType()
    {
        boolean typeValidity = false;
        if(getTransType() == 2)
        {
            typeValidity = true;
        }
        else
        {
            System.exit(1);
        }
        return typeValidity;
    }

/*PRECONDITIONS: executes a check on the fee 
 POSTCONDITIONS: depending on the desired amount withdrawn, the fee is set to 1% or 1$
 */
 
 
private void checkFee()
{
    double fee = 0;
    if(getAmount() >= 100)
    {
        setFee(getAmount() * .01);
    }
    else if(getAmount() < 100)
    {
        setFee(1);
    }
}


    public double getAmount() //accessor method
    {
        return amount;
    }

    public void setAmount(double amount)  //mutator method
    {
        this.amount = amount;
        isValid3();
    }

    public double getFee() 
    {
        return fee;
    }

    public void setFee(double fee) 
    {
        this.fee = fee;
    }
    
    /*PRECONDITIONS: n/a
    POSTCONDITIONS: when called, displayed the following message
    */
    
    public String toString()
    {
        String output;
        output = "(Account Id: " + getAccountId() + " TransNum: " + getTransNum() + " TransType: " + getTransType() + " ):(Withdrawal) Amount: " + getAmount() + " Fee: " + getFee() + " Balance: " + getBalance();
        return output;
    }
    
    /*PRECONDITIONS: object is brought in
    POSTCONDITIONS: object casted to type Withdrawal and each element is compared. A boolean is return based on validity
    */
    
    
    public boolean equals(Object item)
    {
        boolean temp = false;
        Withdrawal W = (Withdrawal)item;
        if(super.getAccountId() == W.getAccountId() && super.getBalance() == W.getBalance() && super.getPin() == W.getPin() && super.getTransNum() == W.getTransNum() && super.getTransType() == W.getTransType() && getFee() == W.fee && getAmount() == W.amount )
        {
            temp = true;
        }
        return temp;
    }
    
    /*PRECONDITIONS: object is brought in
    POSTCONDITIONS: casted to type Withdrawal and a double is eventually returned based on the comparisons of the elements
    */
    
    
    public double compareTo(Object item)
    {
        double result = 0;
        Withdrawal W = (Withdrawal)item;
        if(super.getAccountId() - W.accountID < 0)
        {
            result = super.getAccountId() - W.accountID;
        }
        else if(super.getAccountId() - W.accountID > 0)
        {
            result = super.getAccountId() - W.accountID;
        }
        else if(super.getAccountId() == W.accountID)
        {
            if(super.getTransNum() - W.getTransNum() < 0)
            {
                result = super.getTransNum() - W.getTransNum();
            }
            else if(super.getTransNum() - W.getTransNum() > 0)
            {
                result = super.getTransNum() - W.getTransNum();
            }
            else if(super.getTransNum() == W.getTransNum())
            {
                if(getAmount() - W.getAmount() < 0)
                {
                    result = getAmount() - W.getAmount();
                }
                else if(getAmount() - W.getAmount() > 0)
                {
                    result = getAmount() - W.getAmount();
                }
                else
                {
                    result = 0;
                }
            }
        }
        return result;
    }
    
    public Withdrawal clone() throws CloneNotSupportedException
    {
        return (Withdrawal) super.clone();
    }
} //end


