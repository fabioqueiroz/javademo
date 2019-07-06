import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class defines a customer object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class Customer
{
    private String customerID, surname, firstName, otherInitials, title;

    /**
     * Default constructor for objects of class Customer
     */
    public Customer()
    {
       customerID = null;
       surname = null;
       firstName = null;
       otherInitials = null;
       title = null;
    }

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String surname, String firstName, String otherInitials, String title)
    {    	
        customerID = "unknown";
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
    }
    
    public String getCustomerID()
    {
    	return customerID;
    }
    
    public void setCustomerID(String customerID)
    {
    	this.customerID = customerID;
    }
    
    public String getSurname()
    {
    	return surname;
    }
    
    public void setSurname(String surname)
    {
    	this.surname = surname;
    }
    
    public String getFirstName()
    {
    	return firstName;
    }
    
    public void setFirstName(String firstName)
    {
    	this.firstName = firstName;
    }
    
    public String getOtherInitials()
    {
    	return otherInitials;
    }
    
    public void setOtherInitials(String otherInitials)
    {
    	this.otherInitials = otherInitials;
    }
    
    public String getTitle()
    {
    	return title;
    }
    
    public void setTitle(String title)
    {
    	this.title = title;
    }
    
    // print out the specific details of the class
    public void printDetails()
    {
        System.out.println("Customer ID: " + customerID + "  Title: " + title + "  First Name: " + firstName 
        		+ "  Initials: " + otherInitials + "  Surname: " + surname);
        System.out.println();
    }
    
    // read the specific details that define the class
    public void readData(Scanner strScanner) 
    {    	    	
    	customerID = strScanner.next();
    	// store each ID in a list so they can be compared with a HashSet 
    	// to ensure that only unique IDs are generated
    	ReservationSystem.idsInFile.add(customerID);
    	surname = strScanner.next();
    	firstName = strScanner.next();
    	otherInitials = strScanner.next();
    	title = strScanner.next();
    }
    
    // define the format for the details of the customer to be written on a file
    public void writeData(PrintWriter pWriter)
    {
    	pWriter.println(customerID + ", " + surname + ", " + firstName + ", " + otherInitials 
		+ ", " + title);
    	
    }     
}




















