import java.util.Scanner;

/**
 * This class defines a car object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class Car extends Vehicle
{
    private String bodyType;
    private int noOfDoors;
    private int noOfSeats;

    /**
     * Constructor for objects of class Car
     */
    public Car()
    {
    	super();
        bodyType = null;
        noOfDoors = 0;
        noOfSeats = 0;
    }

    public String getBodyType()
    {
        return bodyType;
    }
    
    public int getNoOfDoors()
    {
    	return noOfDoors;
    }
    
    public int getNoOfSeats()
    {
    	return noOfSeats;
    }
    
    // print out the specific details of the class
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Body type: " + bodyType + "  Number of doors: " + noOfDoors + "  Number of seats: " + noOfSeats);
        System.out.println();
    }
    
    // read the specific details that define the class
    public void readData(Scanner strScanner) 
    {
       super.readData(strScanner);
       
       bodyType = strScanner.next();
       
       noOfDoors = strScanner.nextInt();
       noOfSeats = strScanner.nextInt();
    }
    
}
