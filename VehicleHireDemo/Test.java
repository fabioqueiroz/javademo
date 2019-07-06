import java.util.*;
import java.awt.*;
import java.io.*;

/**
 * This class is used as a test class.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class Test
{
    /**
     * Constructor for objects of class Test
     */
    public Test() throws FileNotFoundException 
    {
        //Vehicle vehicle = new Vehicle("A3", "JY-5623", "GF09RTY", "Ford");
        //ReservationSystem rs = new ReservationSystem();
        
        //rs.addVehicle(vehicle);
        //rs.printAllVehicles();
        
        //rs.readVehicleData();
        //rs.printAllVehicles();
        
        //Customer c1 = new Customer("surname 1", "first name 1", "T 1","Mr");
        //Customer c2 = new Customer("surname 2", "first name 2", "T 2","Dr");
        //Customer c3 = new Customer("surname 3", "first name 3", "T 3","Mrs");
        //Customer c4 = new Customer("surname 4", "first name 4", "T 4","Ms");
        
        //rs.storeCustomer(c1);
        //rs.storeCustomer(c2);
        //rs.storeCustomer(c3);
        //rs.storeCustomer(c4);
        
        //rs.writeCustomerData("S7.txt");
        
        //Date today = DateUtil.convertStringToDate("02-04-2019");
        //Date deadline = DateUtil.convertStringToDate("05-04-2019");
        
        //System.out.println(today);
        //System.out.println(DateUtil.daysBetween(today, deadline)); 
              
        //VehicleReservation vs = new VehicleReservation("00001", "ID-01", "Cust-01", "02-02-2019", 4);
        //vs.printDetails();
        
        //ReservationSystem rs = new ReservationSystem();
		 
	// Reads and outputs vehicles
	//rs.readVehicleData();  
	//rs.printAllVehicles();
		 
	// Reads and outputs customers
	//rs.readCustomerData();
	//rs.printAllCustomers();
	//rs.showIDs();
		  
	// rs.generateCustomerID("AB-", 6);
		  
	//DateUtil du = new DateUtil();
	//du.setshortDatePattern("03/04/2019");		  
	//System.out.println(du.setshortDatePattern("dd/MM/yyyy"));
		  
	//rs.generateReservationNo();		  
		  
	//rs.readVehicleReservationData();
	//rs.printAllVehicleReservations();
        
        ReservationSystem rs = new ReservationSystem("testfile2");	
	//rs.readCustomerData(); 
	//rs.readVehicleReservationData();
		  
	//rs.printAllCustomers();
	//System.out.println();
	//rs.printAllVehicleReservations();
		  
	//rs.reloadSystem();
	//rs.closeDownSystem();
		  		  
	//rs.getNumberOfVehicles();
	//rs.getNumberOfCustomers();
	//rs.getNumberOfVehicleReservations();
		  
	// **** STEP 3 ****
	//rs.printDiaryEntries("23-03-2019", "08-04-2019");
	//rs.deleteVehicleReservation("000008");
	//rs.closeDownSystem();
		  
	// **** STEP 4 ****
	// test for a date already taken
	//rs.makeVehicleReservation("V999", "V999", "01-03-2019", 9);
	// test for a new reservation
	rs.makeVehicleReservation("V999", "C008", "09-04-2019", 9);
	// test for a vehicle already taken
	//rs.makeVehicleReservation("V333", "C999", "09-04-2019", 9);
	// test for a customer with an ongoing reservation
	//rs.makeVehicleReservation("V999", "C003", "09-04-2019", 9);
	// test for both a vehicle already rented out and a customer with an ongoing reservation
	//rs.makeVehicleReservation("V333", "C003", "09-04-2019", 9);
	rs.printAllVehicleReservations();
		  
	//rs.printAllVehicles();
		  
	//rs.printAllCustomers();
    }
}


