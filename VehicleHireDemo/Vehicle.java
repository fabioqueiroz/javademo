import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * This class defines a vehicle object and is
 * designed to be a base class for other classes
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public abstract class Vehicle
{
    private String group, vehID, regNo, make;
    private String model, fuelType, gearbox, transmission, dateFirstRegistered;
    private boolean airCon;
    private double engineSize;
    private int mileage;

    /**
     * Constructor for objects of class Vehicle
     */
    public Vehicle()
    {
        group = null;
        vehID = null;
        regNo = null;
        make = null;
        model = null;
        airCon = false;
        engineSize = 0.0;
        fuelType = null;
        gearbox = null;
        transmission = null;
        mileage = 0;
        dateFirstRegistered = null;      
    }
    
    public String getGroup()
    {
        return group;
    }
    
    public void setGroup(String newGroup)
    {
        group = newGroup;
    }
    
    public String getVehicleID()
    {
        return vehID;
    }
    
    public void setVehicleID(String newVehID)
    {
        vehID = newVehID;
    }
    
    public String getRegistrationNo()
    {
        return regNo;
    }
    
    public void setRegistrationNo(String newRegNo)
    {
        regNo = newRegNo;
    }
    
    public String getMake()
    {
        return make;
    }
    
    public void setMake(String newMake)
    {
        make = newMake;
    }
    
    public String getModel()
    {
        return model;
    }
    
    public void setModel(String newModel)
    {
        model = newModel;
    }
    
    public boolean getAirCon()
    {
        return airCon;
    }
    
    // output the result of the boolean check in words
    private String checkAirCon()
    {
        String output = "";              
        return output = getAirCon() == true ? "Yes" : "No";
    }
    
    // print out the specific details of the class
    public void printDetails()
    {
        System.out.println(make + " " + model + "  Group: " + group + "  Vehicle Id: " + vehID);
        System.out.println("Air conditioning / Climate control: " + checkAirCon());
        System.out.println("Engine Size: " + engineSize + "    Fuel: " + fuelType);
        System.out.println("Gearbox: " + gearbox + "  Transmission: " + transmission);
        System.out.println("Mileage: " + mileage + "  Date first registered: " + dateFirstRegistered);
        //System.out.println();
    }
     
    // read the specific details that define the class
    public void readData(Scanner strScanner) 
    {
       group = strScanner.next();
       vehID = strScanner.next(); 
       regNo = strScanner.next();
       make = strScanner.next();    
       model = strScanner.next();    
       //System.out.println(model); 
       
       String strAirCon = strScanner.next();                          
       airCon = strAirCon.equalsIgnoreCase("Yes") == true ? true : false; 

       engineSize = strScanner.nextDouble();
       //System.out.println(engineSize);    
       
       fuelType = strScanner.next(); 
       gearbox = strScanner.next();
       transmission = strScanner.next();

       mileage = strScanner.nextInt();
       //System.out.println(mileage); 
       
       dateFirstRegistered = strScanner.next();
    }
}
