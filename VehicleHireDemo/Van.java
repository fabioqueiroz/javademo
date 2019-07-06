import java.util.Scanner;

/**
 * This class defines a van object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class Van extends Commercial
{
    private double loadVolume;
    private boolean slidingSideDoor;

    /**
     * Constructor for objects of class Van
     */
    public Van()
    {
        super();
        loadVolume = 0.0;
        slidingSideDoor = false;
    }

    public double getLoadVolume()
    {
        return loadVolume;
    }
    
    public void setLoadVolume(double loadVolume)
    {
        this.loadVolume = loadVolume;
    }
    
    public boolean getSlidingSideDoor()
    {
        return slidingSideDoor;
    }
    
    public void setslidingSideDoor(boolean slidingSideDoor)
    {
        this.slidingSideDoor = slidingSideDoor;
    }
    
    // output the result of the boolean check in words
    public String checkDoorType()
    {
    	String output;
    	return output = getSlidingSideDoor() ? "Yes" : "No";
    }
    
    // print out the specific details of the class
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Load Volume: " + loadVolume + "  Sliding Side Door: " + checkDoorType());
        System.out.println();
    }
    
    // read the specific details that define the class
    public void readData(Scanner strScanner) 
    {
       super.readData(strScanner);
       
       loadVolume = strScanner.nextDouble();
       
       String doorType = strScanner.next();
       slidingSideDoor = doorType.equalsIgnoreCase("Yes") == true ? true : false;
    }
}
