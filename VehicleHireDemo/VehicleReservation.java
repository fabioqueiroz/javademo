import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * This class defines a vehicle reservation object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class VehicleReservation 
{
    private String reservationNo, vehID, customerID;
    private Date startDate;
    private int noOfDays;
	
    /**
     * Default constructor for objects of class VehicleReservation
    */
    public VehicleReservation()
    {
        reservationNo = null;
	vehID = null;
	customerID = null;
	startDate = DateUtil.convertStringToDate("03-04-2019");
	noOfDays = 0;
    }
	
    /**
     * Constructor for objects of class VehicleReservation
    */
	
    public VehicleReservation(String reservationNo, String vehID, String customerID, String startDate, int noOfDays)
    {
 	this.reservationNo = reservationNo;
	this.vehID = vehID;
	this.customerID = customerID;
	this.startDate = DateUtil.convertStringToDate(startDate);
	this.noOfDays = noOfDays;
    }
	
    public String getReservationNo()
    {
	return reservationNo;
    }
	
    public String getVehID()
    {
	return vehID;
    }
	
    public String getCustomerID()
    {
	return customerID;
    }
	
    public Date getStartDate()
    {
	return startDate;
    }
	
    public int getNoOfDays()
    {
 	return noOfDays;
    }
	
    // print out the specific details of the class
    public void printDetails()
    {
	String formatedDate = DateUtil.convertDateToShortString(startDate);
		
	System.out.println("ReservationNo: " + reservationNo + ", VehID: " + vehID + ", CustomerID: " + customerID);
	System.out.println("StartDate: " + formatedDate + ", NoOfDays: " + noOfDays);
	System.out.println();
    }
	
    // read the specific details that define the class	
    public void readData(Scanner strScanner) 
    {   
	reservationNo = strScanner.next();
	vehID  = strScanner.next();
    	customerID = strScanner.next();
    	String strStartDate = strScanner.next();
    	startDate = DateUtil.convertStringToDate(strStartDate); 
    	noOfDays = strScanner.nextInt();
    }
	
    // define the format for the details of the vehicle reservation to be written on a file
    public void writeData(PrintWriter pWriter)
    {
	 String formatedDate = DateUtil.convertDateToShortString(startDate);
		 	
	 pWriter.println(reservationNo + ", " + vehID + ", " + customerID + ", " + 
    			formatedDate + ", " + noOfDays);
    }
	 
    // override the original toString() method
    public String toString()
    {
	 return "Reservation Number: " + reservationNo + ", Customer ID: " + customerID + ", Vehicle ID: " + vehID; 
    }		
}








