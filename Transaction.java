import java.lang.CloneNotSupportedException;

public class Transaction extends Account implements Cloneable //main class derived from Account
{
  private int transNum = 0; //private instance variables
  private int transType = 0;
  
  /*PRECONDITIONS: 5 data fields are brought into a 5 arg contructor
  POSTCONDITIONS: super class used to initialize 3 of the variables, transNum and transType initialized using set methods
  */
  
public Transaction(int newAccountID, int newPin, double newBalance, int newTransNum, int newTransType) //5 arg constructor
{
    super(newAccountID, newPin, newBalance); //super class
    setTransNum(newTransNum); //set methods
    setTransType(newTransType);
}

/*PRECONDITIONS: executes two checks for validity when called
POSTCONDITIONS: depending on the validity of the transaction number and transaction type, the following messages are displayed 
*/
  
  private void isValid2() //checks validity and manipulates data accordingly
  {
      boolean transNumValidity = checkTransNum();  //boolean variables begin validation
      boolean transTypeValidity = checkTransType();
      if(transNumValidity == false) //if false, set transNum to 1000
      {
          System.out.println("Invalid transaction number. Must be a postitive four digit integer. Set to default value of 1000.");
          setTransNum(1000);
      }
      if(transTypeValidity == false) //if false, set transType to 1
      {
          System.out.println("Invalid transaction type. Must be an integer between 1 and 4, inclusively. Set to default value of 1.");
          setTransType(1);
      }
  }
  
  /*PRECONDITIONS: transaction number brought in, boolean initialized
  POSTCONDITIONS: if out of bounds, the number is invalid. Otherwise, valid and the boolean is returned
  */
  
  private boolean checkTransNum()
  {
      boolean numValidity;
      if(getTransNum() < 1000 || getTransNum() > 9999)
      {
          numValidity = false;
      }
      else
      {
          numValidity = true;
      }
      return numValidity;
  }
  
  /*PRECONDITIONS: transaction type is brought in, boolean initialized
  POSTCONDITIONS: boolean returned based on validity
  */
  
  private boolean checkTransType()
  {
      boolean transValidity;
      if(getTransType() < 0 || getTransType() > 4)
      {
          transValidity = false;
      }
      else
      {
          transValidity = true;
      }
      return transValidity;
  }
  
  
   public int getTransNum() //accessor method
    {
        return transNum;
    }

    public void setTransNum(int transNum) //mutator method
    {
        this.transNum = transNum;
        isValid2(); //checks validity 
    }
    
    public int getTransType() 
    {
        return transType;
    }

    public void setTransType(int transType) 
    {
        this.transType = transType;
        isValid2();
    }
    
    /*PRECONDITIONS: n/a
    POSTCONDITIONS: displays the information and any errors when called
    */
    
    public String toString()
    {
        String output = "(Account Id: " + getAccountId() + " TransNum: " + getTransNum() + " TransType: " + getTransType() + " )";
        return output;
    }
    
    /*PRECONDITIONS: object is brought in. boolean initialized
    POSTCONDITIONS: casted to type Transaction and each element is compared. Boolean returned based on compared elements
    */
    
    
    public boolean equals(Object item)
    {
        boolean result = false;
        Transaction T = (Transaction)item;
        if(super.getAccountId() == T.getAccountId() && getTransNum() == T.transNum && transType == T.transType) //compares each of the elements of the two objects
        {
            result = true;
        }
        return result;
    }
    
    /*PRECONDITIONS: object brought in. double initialized
    POSTCONDITIONS: casted to type Transaction. A double is returned based on the difference in values of either accountID and transaction number
    */
    
    
    public double compareTo(Object item) 
    {
        double result = 0;
        Transaction T = (Transaction)item;
        if(super.getAccountId() - T.accountID < 0)
        {
            result = super.getAccountId() - T.accountID;
        }
        else if(super.getAccountId() - T.accountID > 0)
        {
            result = super.getAccountId() - T.accountID;
        }
        else if(super.getAccountId() == T.accountID)
        {
            if(getTransNum() - T.transNum < 0)
            {
                result = getTransNum() - T.transNum;
            }
            else if(getTransNum() - T.transNum > 0)
            {
                result = getTransNum() - T.transNum;
            }
            else
            {
                result = 0;
            }
        }
        return result;
    }
    
    public Transaction clone() throws CloneNotSupportedException
    {
        return (Transaction) super.clone();
    }

    double getAmount() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    double getFee() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}//end
  
