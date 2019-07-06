import java.util.*;
import java.awt.*;
import java.io.*;
/**
 * This class defines a reservation system object.
 *
 * @author Fabio Viana Queiroz
 * @00336760 - Group 1
 * @module Programming 2 - Final Project
 * @date 29/04/2019
 */
public class ReservationSystem
{
   //private List<Vehicle> vehicleList;
   //private List<Customer> customerList;
   private Map<String, Vehicle> vehicleMap;
   private Map<String, Customer> customerMap;
   private Map<String, VehicleReservation> vehicleReservationMap;
   private Random randomGenerator;
   private HashSet<String> custUniqueIDs;
   public static ArrayList<String> idsInFile; // stores the customers' IDs
   private boolean firstCallToGenerateReservationNo;
   private int lastReservationNumber;
   private String lastNumberGeneratedFileName;
   private String dumpCustomerDataFileName;
   private String dumpItemReservationDataFileName;
   private Diary diary; 
        
   /**
   * Parametized constructor for objects of class ReservationSystem
   */
   public ReservationSystem(String reservationName)
   {
     //vehicleList = new ArrayList<Vehicle>();
     //customerList = new ArrayList<Customer>();
     vehicleMap = new HashMap<String, Vehicle>();
     customerMap = new HashMap<String, Customer>();
     vehicleReservationMap = new HashMap<String, VehicleReservation>();
     randomGenerator = new Random();
     custUniqueIDs = new HashSet<String>();
     idsInFile = new ArrayList<String>();
     firstCallToGenerateReservationNo = true;
     lastReservationNumber = 0;
     lastNumberGeneratedFileName = "LastNumberGenerated.txt";
     readVehicleData("vehicle_data_2.txt"); 
     dumpCustomerDataFileName = reservationName + " customer dump.txt";
     dumpItemReservationDataFileName = reservationName + " reservation system dump.txt";
     diary = new Diary();
     reloadSystem();       
   }
        
   public Vehicle getVehicle(String id) 
   {
       //System.out.println(vehicleMap.get(id).getModel());
       return vehicleMap.get(id);
   }
        
   public Customer getCustomer(String id)  
   {
       //System.out.println(customerMap.get(id).getFirstName());
       return customerMap.get(id);
   }
        
   public VehicleReservation getVehicleReservation(String id) 
   {
       return vehicleReservationMap.get(id);
   }
        
   public boolean getFirstCallToGenerateReservationNo()
   {
      return firstCallToGenerateReservationNo;
   }
        
   public int getLastReservationNumber()
   {
      return lastReservationNumber;
   }
        
   public String getLastNumberGeneratedFileName()
   {
      return lastNumberGeneratedFileName; 
   }
            
   public String getDumpCustomerDataFileName()
   {
      return dumpCustomerDataFileName;
   }
        
   public String getDumpItemReservationDataFileName()
   {
      return dumpItemReservationDataFileName;
   }
                
   public int getNumberOfVehicles()
   {
      int vehicleCounter = 0;
                
      Iterator<String> it = vehicleMap.keySet().iterator();
            
      while (it.hasNext()) 
      {
         it.next();
         vehicleCounter++;
                
      }
            
     //System.out.println(vehicleCounter);
            
     return vehicleCounter;
   }
        
   public int getNumberOfCustomers() 
   {
      int customerCounter = 0;
            
      Iterator<String> it = customerMap.keySet().iterator();
            
      while (it.hasNext()) 
      {
          it.next();
          customerCounter++;          
      }
            
      //System.out.println(customerCounter);
            
      return customerCounter;
   }
        
   public int getNumberOfVehicleReservations()
   {
      int vrCounter = 0;
            
      Iterator<String> it = vehicleReservationMap.keySet().iterator();
            
      while (it.hasNext()) 
      {
          it.next();
                vrCounter++;            
      }
            
      //System.out.println(vrCounter);
            
      return vrCounter;
   }
                
   // ***** TEST: confirms that each ID is unique ****** 
   // public void showIDs()
   // {
   //   System.out.println("** IDs in the HashSet **");
   //   for (String id : custUniqueIDs) 
   //   {
   //       System.out.println(id);
   //   }
   // }
      
   // create a unique ID for the customer
   private String generateCustomerID(String prefix, int noOfDigits)
   {
      // Used for testing with a smaller number to confirm the IDs will be unique
      //int numbers = randomGenerator.nextInt(noOfDigits);
            
      //int numbers = 100000 + randomGenerator.nextInt(noOfDigits * 100000);      
                    
      // Based on https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
      // Defines the number of digits to be generated
      int size = (int)Math.pow(10, (noOfDigits - 1));
      // Gets the size and ensures the number will be within the boundaries
      int numbers = size + (randomGenerator.nextInt(size) * 9); 
            
      String customerID = prefix + numbers;       
            
      return customerID;
                
   }
            
    // only store customers with unique IDs
   public void storeCustomer(Customer customer) 
   {            
      int i = 0;        
      while (i < idsInFile.size()) 
      {
        //System.out.println(ReservationSystem.idsInFile.get(i) + " xxx");
            
         if (customer.getCustomerID().contains("unknown")) 
         {
           String newCustomerID = generateCustomerID("AB-", 6);

           // Checks if the ID is already taken
           if (customerMap.containsKey(newCustomerID) || custUniqueIDs.contains(newCustomerID) || idsInFile.contains(newCustomerID)) 
           {
              System.out.println("ID already taken");
              newCustomerID = generateCustomerID("AB-", 6);
           }

           else 
           {
              try 
              {
                  // Adds the new ID to a collection of IDs with no repetitions
                  custUniqueIDs.add(newCustomerID);
                  //ReservationSystem.custUniqueIDs.add(newCustomerID);

              } 
              catch (Exception e) 
              {
                  System.err.println(e.getMessage());
                  System.out.println("This ID already exists");
              }
           }

           customer.setCustomerID(newCustomerID);

         } 
            
          i++;
      }
      //customerList.add(customer); 
      customerMap.put(customer.getCustomerID(), customer);
   }
    
   // store new vehicles
   public void storeVehicle(Vehicle vehicle)
   {
       vehicleMap.put(vehicle.getVehicleID(), vehicle);
   }
    
   // store a new reservation of the vehicle
   public void storeVehicleReservation(VehicleReservation vehicleReservation) 
   {
       vehicleReservationMap.put(vehicleReservation.getReservationNo(), vehicleReservation); 
        
       diary.addReservation(vehicleReservation); 
   }
        
   // create unique reservation numbers 
   public String generateReservationNo() throws FileNotFoundException
   {
       String reservationNo = null;
       lastReservationNumber++;
        
       //read the existing file
       //readVehicleReservationData();
       readVehicleReservationData(dumpItemReservationDataFileName);
        
       // get a list of regNos (keys) from the file
       vehicleReservationMap.keySet();
       //System.out.println(vehicleReservationMap.keySet());
        
       // loop through the set of keys and find the biggest number (max)
       Iterator<String> it = vehicleReservationMap.keySet().iterator(); 
        
       int max = 0;
       while (it.hasNext()) 
       {
           String currentValue = (String)it.next();
            
           int currentNo = Integer.parseInt(currentValue);
            
           if (currentNo > max) 
           {
               max = currentNo;
           }           
       }
        
       //System.out.println(max);      
        
       // increment the value of the last reservation number to create a bigger one
       lastReservationNumber = max + 1;
        
       // create reservationNo 
       if (firstCallToGenerateReservationNo) 
       {   
           // limit the amount of left padding zeros
           reservationNo = String.format("%06d", lastReservationNumber);
           //System.out.println(reservationNo);
             
           firstCallToGenerateReservationNo = false;
       }
                
       // create file with specific name
       PrintWriter pWriter = new PrintWriter(lastNumberGeneratedFileName);
       // print the last generated value
       pWriter.println("// Reservation numbers");
       pWriter.println(reservationNo);
       pWriter.close();
        
       return reservationNo;
   }
    
   
    // create a new reservation if the vehicle exists
    public boolean makeVehicleReservation(String vehID, String customerID, String startDate, int noOfDays)
    {   
        boolean isResValid = false;
        
        String reservationNo = "";      
        
        Date newDate = DateUtil.convertStringToDate(startDate); 
        VehicleReservation[] reservations = diary.getReservations(newDate);     
        
        try 
        {
            if (reservations.clone() != null) 
            {
                for (int i = 0; i < reservations.length; i++) 
                {
                    if (reservations[i].getStartDate().equals(newDate)) 
                    {
                        System.out.println("Unavailable date");
                        System.out.println();
                    }
                } 
            }
        } 
        catch (NullPointerException ex) 
        {
            //ex.printStackTrace();
            System.out.println("Date " + startDate +" available in the system");
            System.out.println();
            
            try 
            {
                
                reservationNo = generateReservationNo();
                
                if (noOfDays > 0) 
                {                   
                    VehicleReservation vs = new VehicleReservation(reservationNo, vehID, customerID, startDate, noOfDays);              
                    
                                                        
                    if (!checkIdAvailibility(vehicleReservationMap, vehID)) 
                    {
                        System.out.println("Vehicle " + vehID + " taken");
                        System.out.println();
                    }
                    
                    else if (!checkIdAvailibility(vehicleReservationMap, customerID)) 
                    {
                        System.out.println("Customer " + customerID + " can only rent one vehicle for the period under their name");
                        System.out.println();
                    }

                    else 
                    {
                        isResValid = true;
                        storeVehicleReservation(vs);
                    } 
                }                               
                
                else
                {
                    System.out.println("The number of days cannot be zero");
                }
            } 
            catch (FileNotFoundException e) 
            {
                System.err.println(e.getMessage());
            }
            
        }
                                
        return isResValid; 
    } 
    
    private boolean checkIdAvailibility(Map<String, VehicleReservation> reservations, String id)
    {
        for (VehicleReservation vr : reservations.values())
        {
            if(vr.getVehID().equals(id) || vr.getCustomerID().equals(id))
            {
                return false; 
            }
        }
        
        return true; 
    }
    
    
    // prints out the list of all reservations
    public void printAllVehicleReservations() 
    {
        for (VehicleReservation vs : vehicleReservationMap.values()) 
        {
            vs.printDetails();
        }
    }
    
    // shows a list of all customers
    public void printAllCustomers()
    {
      //for (Customer c : customerList)
      //{
      //    c.printDetails();
      //}
        
        for (Customer c : customerMap.values())
        {
            c.printDetails();           
        }
    }
    
    // shows a list of all vehicles
    public void printAllVehicles()  
    {
        //for(Vehicle v : vehicleList)
        //{
        //    v.printDetails();
        //}
        
        for(Vehicle v : vehicleMap.values())
        {
            v.printDetails();
        }
    }
    
    // reads the customers' information from a dialog box 
    public void readCustomerData() 
    {
        Frame frame = null;
        FileDialog fileBox = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileBox.setDirectory(".");
        fileBox.setVisible(true);
        
        //String fileName = fileBox.getDirectory()+fileBox.getFile(); 
        String filePath = fileBox.getDirectory()+fileBox.getFile(); 
        readCustomerData(filePath);
       }
    
    // reads the customers' information from a file
    public void readCustomerData(String fileName) 
    {
         try
         {
             File dataFile = new File(fileName);  
             Scanner scanner = new Scanner(dataFile);

             while(scanner.hasNextLine())
             {
                 String lineOfText = scanner.nextLine().trim(); 
                 //System.out.println(lineOfText); 

                 if (!lineOfText.startsWith("//") && !lineOfText.isEmpty())
                 {
                     Scanner strScanner = new Scanner(lineOfText).useDelimiter("\\s*[,\n]\\s*");
                 
                     Customer customer = new Customer(); 
                     
                     if(strScanner.hasNext())
                     {
                          customer.readData(strScanner);
                          //customerList.add(customer);
                          //customerMap.put(customer.getCustomerID(), customer);
                          storeCustomer(customer);
                          //storeCustomer2(customer);
                         
                                                   
                          if(strScanner.hasNextLine())
                          {
                             strScanner.nextLine();
                          }   
                     }
                    
                     strScanner.close();  
                 }                                
             }
             
             if(scanner.hasNextLine())
             {
                 scanner.nextLine();
             }   
             
             scanner.close();
         }
                 
         catch(FileNotFoundException ex)
         {
             System.err.println(ex.getMessage());
             System.err.println(ex.getCause());
             ex.printStackTrace();
             System.err.println("File " + fileName + " not found"); 
             fileName = null;
         }
     
    }
          
    // reads all the information on vehicles from a dialog box
    public void readVehicleData()
    {
        Frame frame = null;
        FileDialog fileBox = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileBox.setDirectory(".");
        fileBox.setVisible(true);
        
        //String fileName = fileBox.getDirectory()+fileBox.getFile(); 
        //System.out.println(fileName); 
        String filePath = fileBox.getDirectory()+fileBox.getFile(); 
        readVehicleData(filePath);
        
    }
    
    // reads all the information on vehicles from a file
    public void readVehicleData(String fileName)
    {
        try
        {
            File dataFile = new File(fileName);  
            Scanner scanner = new Scanner(dataFile);
            
            
            String typeOfData = "";
            Vehicle vehicle = null;

            while(scanner.hasNextLine())
            {
                String lineOfText = scanner.nextLine().trim(); 
                //System.out.println(lineOfText); 
               
                
                if(lineOfText.startsWith("["))
                {                   
                    typeOfData = lineOfText; // data inside square brackets
                    //System.out.println(typeOfData); 

                }
                
                else if (!lineOfText.startsWith("//") && !lineOfText.isEmpty())
                {
                    Scanner strScanner = new Scanner(lineOfText).useDelimiter("\\s*[,\n]\\s*");
                    
                             
                    if(strScanner.hasNext())
                    {
                        if (typeOfData.equalsIgnoreCase("[Car data]") == true)
                        {
                             vehicle = new Car();
                             
                        }
                        
                        else if (typeOfData.equalsIgnoreCase("[van data]") == true)
                        {
                             vehicle = new Van();
                        }
                        
                        else if (typeOfData.equalsIgnoreCase("[Truck data]") == true)
                        {
                    
                             vehicle = new Truck(); 

                        }
                        
                        vehicle.readData(strScanner);
                        //vehicleList.add(vehicle);
                        vehicleMap.put(vehicle.getVehicleID(), vehicle);
                                                  
                        if(strScanner.hasNextLine())
                        {
                            strScanner.nextLine();
                        }   
                    }
                   
                    strScanner.close();  
                }                                
            }
            
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }   
            
            scanner.close();
        }
                
        catch(FileNotFoundException ex)
        {
            System.err.println(ex.getMessage());
            System.err.println(ex.getCause());
            ex.printStackTrace();
            System.err.println("File " + fileName + " not found"); 
            fileName = null;
        }

    }
    
  
    // reads all the information on the reservations from a dialog box
    public void readVehicleReservationData() 
    {
        Frame frame = null;
        FileDialog fileBox = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileBox.setDirectory(".");
        fileBox.setVisible(true);
        
        //String fileName = fileBox.getDirectory()+fileBox.getFile(); 
        String filePath = fileBox.getDirectory()+fileBox.getFile(); 
        readVehicleReservationData(filePath);
    }

    // reads all the information on the reservations from a file
    public void readVehicleReservationData(String fileName) 
    {
        try
        {
            File dataFile = new File(fileName);  
            Scanner scanner = new Scanner(dataFile);

            while(scanner.hasNextLine())
            {
                String lineOfText = scanner.nextLine().trim(); 
                //System.out.println(lineOfText); 

                if (!lineOfText.startsWith("//") && !lineOfText.isEmpty())
                {
                    Scanner strScanner = new Scanner(lineOfText).useDelimiter("\\s*[,\n]\\s*");
                
                    VehicleReservation vr = new VehicleReservation();
                    
                    if(strScanner.hasNext())
                    {
                         vr.readData(strScanner);
                         storeVehicleReservation(vr);
                        
                         
                         if(strScanner.hasNextLine())
                         {
                             strScanner.nextLine();
                         }   
                    }
                   
                    strScanner.close();  
                }                                
            }
            
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }   
            
            scanner.close();
        }
                
        catch(FileNotFoundException ex)
        {
            System.err.println(ex.getMessage());
            System.err.println(ex.getCause());
            ex.printStackTrace();
            System.err.println("File " + fileName + " not found"); 
            fileName = null;
        }

    }
    
    
    // export the customer information to a file for which a name was given
    public void writeCustomerData() throws FileNotFoundException
    {
        Frame frame = null;
        FileDialog fileBox = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileBox.setDirectory(".");
        fileBox.setVisible(true);
        
        String customerFilePath = fileBox.getDirectory()+fileBox.getFile(); 
        
        //File customerFile = new File(System.getProperty("user.dir") + "/" + dumpCustomerDataFileName);
        
        //String customerFilePath = customerFile.getAbsolutePath();
        
        if (!customerMap.isEmpty())
        {
            writeCustomerData(customerFilePath);
        }
        
    }
    
    // method overload to export the customer information to a file for which a name was given
    public void writeCustomerData(String fileName) throws FileNotFoundException 
    {
        if (!customerMap.isEmpty())
        {
            PrintWriter pWriter = new PrintWriter(fileName);
            
            pWriter.println("// New customer data");
            pWriter.println("// data is customerID, surname, firstName, otherInitials, title");
            
            //for (Customer c : customerList)
            //{
              
            //    c.writeData(pWriter);
            //}
            
            for (Customer c : customerMap.values())
            {
                
                c.writeData(pWriter);
            }
            
            pWriter.close();
        }
    }
    
    // record the vehicle reservation details in a file
    public void writeVehicleReservationData() throws FileNotFoundException 
    {
        Frame frame = null;
        FileDialog fileBox = new FileDialog(frame, "Open", FileDialog.LOAD);
        fileBox.setDirectory(".");
        fileBox.setVisible(true);
        
        String vrFilePath = fileBox.getDirectory()+fileBox.getFile(); 
        
        //File vehicleReservationFile = new File(System.getProperty("user.dir") + "/" + dumpItemReservationDataFileName);
      
        //String vrFilePath = vehicleReservationFile.getAbsolutePath();
        
        if (!vehicleReservationMap.isEmpty()) 
        {
            writeVehicleReservationData(vrFilePath);
        }
    } 
    
    // method overload to record the vehicle reservation details in a file
    public void writeVehicleReservationData(String fileName) throws FileNotFoundException 
    {
        if (!vehicleReservationMap.isEmpty()) 
        {
            PrintWriter pWriter = new PrintWriter(fileName);
            pWriter.println("// New reservation data");
            pWriter.println("// data is reservationNo, vehID, customerID, startDate, noOfDays");
            
            for (VehicleReservation vr : vehicleReservationMap.values()) 
            {
                vr.writeData(pWriter);
            }
            pWriter.close();
        }
    } 
       
    // if the file already exists, load the system with both customers 
    // and vehicle reservations data
    public void reloadSystem()
    {       
        // check if the file already exists
        File customerFile = new File(System.getProperty("user.dir") + "/" + dumpCustomerDataFileName);
                
        if (!customerFile.isFile() && !customerFile.isDirectory()) 
        {           
            System.out.println("Customer file not yet in the system");          
        }
        
        else 
        {
            readCustomerData(dumpCustomerDataFileName);
            System.out.println("Customer data in the file: " + customerFile.getAbsolutePath());
        }
            
        
        File vehicleReservationFile = new File(System.getProperty("user.dir") + "/" + dumpItemReservationDataFileName);
        
        if (!vehicleReservationFile.isFile() && !vehicleReservationFile.isDirectory()) 
        {
            System.out.println("Reservation file not yet in the system");
            System.out.println();
        }
        
        else 
        {
            readVehicleReservationData(dumpItemReservationDataFileName);
            System.out.println("Reservation data in the file: " + vehicleReservationFile.getAbsolutePath());
            System.out.println();
        }
    }
    
    // write customer and vehicle reservation data in the dump files
    public void closeDownSystem() 
    {
         try 
         {
            writeCustomerData(dumpCustomerDataFileName);
         } 
         catch (FileNotFoundException e) 
         {

            e.printStackTrace();
         } 
        
         try 
         {
            writeVehicleReservationData(dumpItemReservationDataFileName);
         } 
         catch (FileNotFoundException e) 
         {
            e.printStackTrace();
         } 
    }
   
    public void printDiaryEntries(String startDate, String endDate)
    {
        Date start = DateUtil.convertStringToDate(startDate);
        Date end = DateUtil.convertStringToDate(endDate);
        
        diary.printEntries(start, end);
    }
    
    public void deleteVehicleReservation(String reservationNo)
    {       
        vehicleReservationMap.remove(reservationNo, getVehicleReservation(reservationNo));
        
        diary.deleteReservation(getVehicleReservation(reservationNo)); 
    }    
}






















