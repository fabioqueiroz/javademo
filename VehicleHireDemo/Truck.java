import java.util.Scanner;

/**
 * This class defines a truck object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class Truck extends Commercial
{
    private String attributes;

    /**
     * Constructor for objects of class Truck
     */
    public Truck()
    {
        super();
        attributes = null;
    }

    public String getAttributes()
    {
        return attributes;
    }
    
    public void setAttributes(String attributes)
    {
        this.attributes = attributes;
    }
    
    // print out the specific details of the class
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Features: " + attributes);
        System.out.println();
    }
    
    // read the specific details that define the class
    public void readData(Scanner strScanner) 
    {
       super.readData(strScanner);
       
       // search for a regular pattern when reading the lines with different formats
       String features = strScanner.findInLine("[A-Za-z | \\(A-Za-z\\)]*\\s*,\\s*.*"); 
       attributes = features.substring(2);
       
    }
}

