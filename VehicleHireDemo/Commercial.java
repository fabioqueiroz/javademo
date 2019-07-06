import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * This class defines a commercial object and is
 * designed to be a base class for other classes
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public abstract class Commercial extends Vehicle
{
    private int payLoad;

    /**
     * Constructor for objects of class Commercial
     */
    public Commercial()
    {
        super();
        payLoad = 0;
    }
    
    public int getPayLoad()
    {
        return payLoad;
    }
    
    public void setPayLoad(int payLoad)
    {
        this.payLoad = payLoad; 
    }
    
    // print out the specific detail of the class
    public void printDetails()
    {
        super.printDetails();
        System.out.println("Payload (kg): " + payLoad);
    }
    
    // read the specific detail that defines the class
    public void readData(Scanner strScanner) 
    {
       super.readData(strScanner);
       
       payLoad = strScanner.nextInt();
    }
}

