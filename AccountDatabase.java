import java.util.Scanner; //imports the following java utilities for use
import java.lang.CloneNotSupportedException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class AccountDatabase 
{
    private Transaction[] data; //initialization of private instance variables
    private int numAccts;
    private Scanner transStream; 
    private PrintWriter outputStream;
    
    /* PRECONDITION: n/a
     * POSTCONDITION: AccountDatabase object is created */
    
    public AccountDatabase() //no arg constructor
    {
        transStream = null; 
        outputStream = null;
        data = new Transaction[100]; //array of type Transaction with a capacity of 100 indices
        numAccts = 0;
    }
    
    /* PRECONDITION: the text file already exists
     * POSTCONDITION: reads in the file and creates an array with the file's contents */
    
    public void readTransaction(String fileName) throws InvalidTransTypeException
    {
        try //try loop checks for existence of file
        {
            File inputFile = new File(fileName);
            transStream = new Scanner(inputFile);
        }
        catch(FileNotFoundException tempException) //throws exception if not found
        {
            System.out.println("FileNotFoundException: " + tempException.getMessage());
        }
        
       
        int accountID; //creates variables so the parameters can be put into an array
        int pin;
        int transNum;
        int transType; 
        int transID = 1;
        int i = 0;
        double balance = 0.0;
        double amount = 0.0;
        
        while(transStream.hasNext()) //checks to see if another character exists
        {
            do
            {
                transID = checkTransChar(); //stores transaction type
            }
            while(transStream.hasNextLine() && transID == 4);
            
            if(transID == 4) //if transaction type is not T, D, or W, program closes
            {
                System.out.println("No valid transactions found in the input file. Program will now close.");
                System.exit(1);
            }
            
            accountID = getAccountId(); //calls methods to store temporary values
            pin = getPin2();
            balance= getBalance();
            transNum = getTransNum();
            transType = getTransType();
            
            //depending on transaction ID (type), constructs a transaction object accordingly
            if(transID == 1)
            {
                data[i] = new Transaction(accountID, pin, balance, transNum, transType);
            }
            else if(transID == 2)
            {
                amount = getAmount();
                data[i] = new Withdrawal(accountID, pin, balance, transNum, transType, amount);
            }
            else if(transID == 3)
            {
                amount = getAmount();
                data[i] = new Deposit(accountID, pin, balance,transNum, transType, amount);
            }
            i++; //increments the index for looping
        }
        
        numAccts = i;
        while(i<100)
        {
            data[i] = null;
            i++;
        }
        
        transStream.close();
        sortArray();
    }
    
    /* PRECONDITION: transaction.txt is read 
     * POSTCONDITION: transtype is determined */
    
    private int checkTransChar()
    {
        int transType = 0;
        if(transStream.hasNext("T"))
        {
            transType = 1;
            transStream.next();
        }
        else if(transStream.hasNext("W"))
        {
            transType = 2;
            transStream.next();
        }
        else if(transStream.hasNext("D"))
        {
            transType = 3;
            transStream.next();
        }
        else //if not a valid transaction type, following is set and returns a 4
        {
            System.out.println("Transaction type unknown. Checking next transaction.");
            while((transStream.hasNext())&&(!((transStream.hasNext("T"))||(transStream.hasNext("W"))||(transStream.hasNext("D")))))
            {
                transStream.next();
            }
            return 4;
        }
        return transType;
    }
    
    /* PRECONDITION: array is analyzed
     * POSTCONDITION: if elements of the array are not null, prints the data */
     
    public void printTransactions()
    {
        int i = 0;
        while(data[i] != null)
        {
            System.out.println(data[i]);
            i++;
        }
    }
    
    /* PRECONDITION: array is created
     * POSTCONDITION: array is sorted by transaction type */
    
    private void sortArray() //selection sorting
    {
        int i;
        int j;
        int k;
        Transaction temp;
        
        for(i = 0; i < numAccts; i++)
        {
            k = i;
            for(j = i + 1; j < numAccts; j++)
            {
                if(data[j].getTransNum() < data[k].getTransNum())
                {
                    k = j;
                }
                if(k != i)
                {
                    temp = data[i];
                    data[i] = data[k];
                    data[k] = temp;
                }
            }
        }
    }
    
    /* PRECONDITION: array is filled with transaction information
     * POSTCONDITION: clone is returned */
    
    public Transaction findTrans(int transID) throws CloneNotSupportedException
    {
        int bot = 0;
        int top = 0;
        int mid = (bot + top) / 2;
        
        while((bot <= top) && (data[mid].getTransNum() != transID))
        {
            if(data[mid].getTransNum() < transID)
            {
                bot = mid + 1;
            }
            else
            {
                top = mid - 1;
            }
            mid = (bot + top) / 2;
        }
        if(bot>top)
        {
            System.out.println("Unable to find transaction.");
            return null;
        }
        return data[mid].clone();
    }
    
    /* PRECONDITION: array is filled with contents and output file exists
     * POSTCONDITION: transaction contents fill output file */
    
    
    public void backUpDatabase(String filename)
    {
        try
        {
            File outputFile = new File(filename);
            outputStream = new PrintWriter(outputFile);
        }
        catch(FileNotFoundException tempException)
        {
            System.out.println("FileNotFoundException: " + tempException.getMessage());
        }
        
        int i = 0;
        double tempBalance = 0.0;
        
        while(i < numAccts)
        {
            if(data[i] instanceof Withdrawal)
            {
                outputStream.print('W' + " ");
                tempBalance = data[i].getBalance() + data[i].getAmount() + data[i].getFee();
            }
            else if(data[i] instanceof Deposit)
            {
                outputStream.print('D' + " ");
                tempBalance = data[i].getBalance() - data[i].getAmount();
            }
            else
            {
                outputStream.print('T' + " ");
            }
            
            outputStream.print(data[i].getAccountId() + " " + data[i].getPin() + " ");
            outputStream.print(tempBalance + " ");
            outputStream.print(data[i].getTransNum() + " " + data[i].getTransType() + " ");
            
            if((data[i] instanceof Withdrawal) || (data[i] instanceof Deposit))
            {
                outputStream.println(data[i].getAmount());
            }
            else
            {
                outputStream.println();
            }
            i++;
        }
        outputStream.flush();
        outputStream.close();
    }
    
    public void recoverBackUp(String filename) throws InvalidTransTypeException
    {
        for(int i = 0; i < numAccts; i++)
        {
            data[i] = null;
        }
        readTransaction(filename);
    }
    
    //getter methods
    private int getAccountId()
    {
        if(transStream.hasNextInt())
        {
            return transStream.nextInt();
        }
        else
        {
            System.out.println("Account ID is not an integer. Set to default value of 0.");
            transStream.next();
            return 0;
        }
    }
    
   
    public int getFee()
    {
        return 0;
    }
    
    public int getPin2()
    {
        if(transStream.hasNextInt())
        {
            return transStream.nextInt();
        }
        else
        {
            System.out.println("Pin is not an integer. Set to default value of 0.");
            transStream.next();
            return 0;
        }
    }
    
    private double getBalance()
    {
        if(transStream.hasNextDouble())
        {
            return transStream.nextDouble();
        }
        else
        {
            System.out.println("Balance is not a double. Set to default value of 0.");
            transStream.next();
            return 0.0;
        }
    }
    
    private int getTransNum()
    {
        if(transStream.hasNextInt())
        {
            return transStream.nextInt();
        }
        else
        {
            System.out.println("Transaction number is not an integer. Set to default value of 0.");
            transStream.next();
            return 0;
        }
    }
    
    private int getTransType()
    {
        if(transStream.hasNextInt())
        {
            return transStream.nextInt();
        }
        else
        {
            System.out.println("Transaction type is not an integer. Set to default value of 0.");
            transStream.next();
            return 0;
        }
    }
    
    public double getAmount()
    {
        if(transStream.hasNextDouble())
        {
            return transStream.nextDouble();
        }
        else
        {
            System.out.println("Amount is not a double. Set to default value of 0.");
            transStream.next();
            return 0.0;
        }
    }

 }

