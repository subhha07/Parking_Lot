package com.parkingLot;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ParkingConsole {
	//Insurance Object
	//Insurance Id - Unique Id
	//Parking details
	
	//Slot Object
	//slot no
	//parking details - owner name, vehicle no, token_id, parked time
	//Insurance Object  - parking details
	
	//Floor
	//Floor no
	//Filled - Boolean
	//slot list - List<Slots>
	
	//Parking lot
	//lot type - car, bike, minibus, cargo - enum
	//lot capacity - int
	//filled slots count - int
	//List<Floor> 
	//park(lot type, parking details)
	//unpark(lot type, parking details)
		
    private static SlotInformation slot;
    private int gateNumber;
    
    protected String adminPassword = "password";
    public int adminVehicleChoice;
    
    public static int userVehicleChoice;
    static String username;
    static String vehicleNumber;
    
    private boolean isAdmin = false;
    private boolean isUser = false;
    
    public static int cycleOccupiedSlots;
    public static int carOccupiedSlots;
    public static int bikeOccupiedSlots;
    public static int minibusOccupiedSlots;
    public static int cargoOccupiedSlots;

    Scanner scanner = new Scanner(System.in);
    
    ParkingApplication parkingApplication = new ParkingApplication();
    
    
    
   	private void adminLogin(){
		int operationChoice = 0;
	    System.out.print("Enter password:");
	    String password = scanner.nextLine();
	    if (password.equals(adminPassword)){
	        isAdmin = true;
	        System.out.println("Admin login successful!");
	        while (isAdmin){
	        	try{
	        		int vehicleFloorCapacity = 0;
	      
	                	System.out.print("\n"+"Enter 1 to view all parking lot information(all facilities) " +"\n"+ "Enter 2 to view particular parking lot status "+"\n"+ "Enter 3 to view user details " +"\n" +"Enter 4 to exit ");
		                try { 
		                	operationChoice = scanner.nextInt();
		                } catch (InputMismatchException e) {
		                    System.out.println("Invalid input! Please enter a valid number!");
		                    scanner.nextLine();
		                    continue; 
		                }
		                scanner.nextLine();
		                if (operationChoice == 1){
		                	AdminTools admin_tools = new AdminTools(parkingApplication.getParkingLots());
		                	System.out.println("\n");
		                	System.out.println("Slot information for all lots");
		                	System.out.println("\n"+"Total slots: " + admin_tools.getTotalSlots());
		                    System.out.println("Available slots: " + admin_tools.getAvailableSlots());
		                    System.out.println("Occupied slots: " + admin_tools.getFilledSlots());
		                    System.out.println("\n");
		                    
		                } 
		                else if(operationChoice == 2)
		                {
		                	
		                	    System.out.println("\n"+"Choose parking lot: "+"\n"+"1. Cycle"+"\n"+"2. Bike"+"\n"+"3. Car"+"\n"+"4. Minibus"+"\n"+"5. Cargo"+"\n"+"6. Exit");
			        	     	adminVehicleChoice = scanner.nextInt();
			        	     	scanner.nextLine();
			        	     	if(adminVehicleChoice>=1 && adminVehicleChoice<=4)
			                	{
								vehicleFloorCapacity = parkingApplication.getParkingLot(getLotType(adminVehicleChoice)).getLotCapacity();
								System.out.println("Total slots in all floor: " + vehicleFloorCapacity);
								parkingApplication.getParkingLot(getLotType(adminVehicleChoice)).getVehicleFloorCapacity();
								
		                	   }
			        	     else
			        	     {
			        	    	 System.out.println("Enter valid choice");
			        	     }
		                  }
		                else if(operationChoice == 3)
		                {
		                	System.out.println("Enter vehicle number");
		                	String vehicleNumber = scanner.next();
		                	System.out.println("\n"+"Choose parking lot: "+"\n"+"1. Cycle"+"\n"+"2. Bike"+"\n"+"3. Car"+"\n"+"4. Minibus"+"\n"+"5. Cargo"+"\n"+"6. Exit");
		        	     	adminVehicleChoice = scanner.nextInt();
		        	     	scanner.nextLine();
		        	     	ParkingDetails parkingDetails = new ParkingDetails(vehicleNumber);
		        	     	if (parkingApplication.isVehicleParked(getLotType(adminVehicleChoice),parkingDetails))
		        	     	{
		        	     		ParkingLot parkingLot = parkingApplication.getParkingLot(getLotType(adminVehicleChoice));
		        	     		SlotInformation slotInfo = parkingLot.findParkedSlot(parkingDetails,false);
		        	     		System.out.println("vehicle Number: " + slotInfo.getParkingDetails().getVehicleNumber());
		        	     		System.out.println("Toke Id: " + slotInfo.getParkingDetails().getTokenId());
		        	     		System.out.println("Parked Time: " + slotInfo.getParkingDetails().getParkingTime());
		        	     		
		        	     	}
		        	     	else
		        	     	{
		        	     		System.out.println("No vehicle Found");
		        	     	}
		                }
		                else if(operationChoice == 4)
		                {
		                	System.out.println("Exiting program...");
	        			 	break;
		                }
		                else
		                {
		                	System.out.println("Invalid choice! Please enter a number between 1 and 5.");
			   	            continue;
		                }
		           	        	
	        	} catch (InputMismatchException e) {
	        		System.out.println("Invalid input! Please enter a valid number!");
	        		scanner.nextLine();
		                continue;
				}
	        }
	    } 
   	else{
	        System.out.println("Invalid password.  Try Again!");
	    }
   	}
    public LOT_TYPE getLotType(int vehicleChoice) {
        switch (vehicleChoice) {
            case 1:
                return LOT_TYPE.BIKE;
            case 2:
                return LOT_TYPE.CAR;
            case 3:
                return LOT_TYPE.MINIBUS;
            case 4:
                return LOT_TYPE.CARGO;
        }
		return null;
       
    }
    
    private void userAction() {
    	boolean isUserAction = true;
    	boolean valetChoosed = false;
    	while(isUserAction) {
    		
            try {
            	System.out.println("\nChoose parking or unparking Type:\n1. Self\n2. Valet");
        		int parkingType =scanner.nextInt();
        		 scanner.nextLine();
        		if(parkingType == 1)
        		{
        		System.out.println("\nChoose which vehicle type you are parking or Unparking:\n1. Cycle\n2. Bike\n3. Car\n4. Minibus\n5.Cargo");
                userVehicleChoice = scanner.nextInt();
                scanner.nextLine();
        		}
        		else if(parkingType == 2)
        		{
        			valetChoosed = true;
        			System.out.println("\nChoose which vehicle type you are parking or Unparking:\n1. Cycle\n2. Bike\n3. Car\n4. Minibus\n5.Cargo");
                    userVehicleChoice = scanner.nextInt();
                    scanner.nextLine();
        		}
                LOT_TYPE lot_type = getLotType(userVehicleChoice);
                
                
                if (lot_type==null) 
                {
                    System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                    continue;
                }
                
                if(valetChoosed)
                {
                	Valet valet = parkingApplication.getValets().getAvailableValet(lot_type);
                	if(valet!=null)
                	{
                		valet.setLastParkedTime(System.currentTimeMillis());
                	}
                	else if(valet == null)
                	{
                		valet= parkingApplication.getValets().getAvailableValetInAllSlots(lot_type);
                		
                	}
                	if(valet!=null)
                	{
                		valet.setLastParkedTime(System.currentTimeMillis());
                	}
                	else if(valet == null)
                	{
                		System.out.println("Valets are not available.Please park by yourselves");
                	}
                	System.out.println(valet.getValetId());
                	
                }
                System.out.print("Enter vehicle number:");
                vehicleNumber = scanner.nextLine();

                if (vehicleNumber.isEmpty()) {
                    System.out.println("Vehicle number cannot be empty! Try again with a valid vehicle number.");
                    continue;
                }
                
                ParkingDetails parkingDetails = new ParkingDetails(vehicleNumber);
                
                if (!parkingApplication.isVehicleParked(getLotType(userVehicleChoice), parkingDetails)) 
                {
                	 parkingDetails = new ParkingDetails(vehicleNumber, null, true);  //vehicle no., token id., boolean isPark
                   // System.out.println("Vehicle present in parking lot!");
//                    getVehicleUsernameMap(userVehicleChoice).put(vehicleNumber, username);
                    isUser = true;
                    while (isUser)
                    {
                    	
                    	
                    	try
                    	{
                    		if(!valetChoosed)
                    		{
                    		System.out.print("\n"+"Enter 1 to park or 2 to exit: ");
                    		}
                        	int userchoice = 0;
                        	if(valetChoosed)
                        	{
                        		userchoice =1;
                        	}
                        	if(!valetChoosed)
                    		{
                    		userchoice = scanner.nextInt();
                    		scanner.nextLine();
                    		}
                    		if(userchoice==1)
                    		{
                    			try
                    			{
   		    	    		     	System.out.print("\n"+"Enter the gate number where you want to park (1-3): ");
   		    	    		     	gateNumber = scanner.nextInt();
   		    	    		     	scanner.nextLine();
   		    	    		     	if (gateNumber < 1 || gateNumber > 3) 
   		    	    		     	{
   		    	    		     		System.out.println("Invalid gate number! Please enter a number between 1 and 3.");
   		    	    		     		continue;
   		    	    		     	}
   		    	    		     	
   		    	    		     	slot = parkingApplication.parkVehicle(lot_type, parkingDetails, gateNumber);
   		    	    		     	if (slot==null) {
										System.out.println("Parking lot is full!");
										break;
									}
   		    	    		     	
   		    	    		     	if(slot.getGateNo()!= gateNumber)
   		    	    		     	{
   		    	    		     		System.out.println("Your Preferred Gate No: "+ gateNumber+" is not available");
   		    	    		     		System.out.println("Gate Number "+slot.getGateNo()+" is allocated");
   		    	    		     	}
   		    	    		     	String floorId;
   		    	    		     	if(slot.getFloorNo() ==0 ) {
   		    	    		     		floorId = "G";
   		    	    		     	}
   		    	    		     	else
   		    	    		     	{
   		    	    		     		floorId = slot.getFloorNo()+"";
   		    	    		     	}
   		    	    		     	
   		    	    		     	System.out.println("\nParking vehicle at gate " + slot.getGateNo() + " in slot " + slot.getSlotNo() + " on floor " + floorId );
   		    	    		     	System.out.println("Your vehcile token id is "+ slot.getParkingDetails().getTokenId() + ". Please provide this during unparking vehicle.");
   		    	    		     	System.out.println("Parked time: "+parkingDetails.getParkingTime());
   		    	    		     	isUser = false;
   		    	    		     	isUserAction = false;
                    			}
                    			catch (Exception e)
   	    		        	 	{
                    				System.out.println(e.getMessage());
   	    		        	 	}
   		        		 	}
                    		else if(userchoice==2)
                    		{
                    			System.out.println("Exiting program...");
                    			isUserAction = false;
   		        			 	break;
   		                 	}
                    		else
                    		{
                    			System.out.println("Invalid input! Please enter a valid number!");
          		                continue;
          		                 
                    		}
   		        	  }
                      catch (InputMismatchException e) 
   		        	  {
   		        		 System.out.println("Invalid input! Please enter a valid number!");
   		                 scanner.nextLine();
   		                 continue;
   		        	 }
                    }
   		        }
		         
	    		 else
	    		 {
	    			 isUser = true;
   		         while (isUser) 
   		         {
   		        	 
   		        	 
   		        	 int userchoice = 0;
   		        	 try 
   		        	 {
   		        		
   	        		    if(parkingType ==1)
   	        		    {
   		        		 System.out.print("\n"+"Enter 1 to unpark or 2 to exit: ");
   		        		 userchoice = scanner.nextInt();
   		        		 scanner.nextLine();
   	        		    }
   	        		    
		        		
   	        		    if(parkingType == 2)
   	        		    {
   	        		    	valetChoosed = true;
   	        		    	userchoice =1;
   	        		    }
   	        		    if(valetChoosed)
   	        		    {
   	        		    	Valet valet = parkingApplication.getValets().getAvailableValet(lot_type);
	   	                 	if(valet!=null)
	   	                 	{
	   	                 		valet.setLastParkedTime(System.currentTimeMillis());
	   	                 	}
	   	                 	else if(valet == null)
	   	                 	{
	   	                 		valet= parkingApplication.getValets().getAvailableValetInAllSlots(lot_type);
	   	                 		
	   	                 	}
	   	                 	if(valet!=null)
	   	                 	{
	   	                 		valet.setLastParkedTime(System.currentTimeMillis());
	   	                 	}
	   	                 	else if(valet == null)
	   	                 	{
	   	                 		System.out.println("Valets are not available.Please Unpark by yourselves");
	   	                 	}
	   	                 	System.out.println(valet);
   	        		    }
   		        		 
   	        		    if(userchoice==1)
   	        		    {
   	        		    	System.out.print("Enter your token id: ");
   	        		    	String tokenId = scanner.next();
   	        		    	parkingDetails = new ParkingDetails(vehicleNumber, tokenId, false);
   	        		    	isUser = false;
	                        isUserAction = false;
	                        
	                        if(parkingApplication.unparkVehicle(lot_type, parkingDetails)==true) {
	                        	System.out.println("Vehicle unparked!");
	                        }
	                        else {
								System.out.println("Unable to unpark the vehicle due to token id mismatch");
							}
   		        		 }
   		        		 else if (userchoice == 2)
   		        		 {
   		        			 System.out.println("Exiting program...");
   		        			 isUser = false;
   		        			 isUserAction = false;
   		        			 break;
   		        		 }
						 }
   		        	 catch (InputMismatchException e)
   		        	 {
   		        		 System.out.println("Invalid input! Please enter a valid number!");
   		                 scanner.nextLine();
   		                 continue;
						}
   		         }
				 }
           }
            catch (InputMismatchException e) {
            	System.out.println("Invalid input! Please enter a valid number!");
                scanner.nextLine();
                continue;
			}
    	}
    }
    
    public void claimInsurance(){
		System.out.println("\nChoose vehicle type:\n1. Cycle\n2. Bike\n3. Car\n4. Minibus\n5. Cargo");
        int insuranceVehicleChoice = scanner.nextInt();
		System.out.print("Enter vehicle number which you parked: ");
    	String vehicleNumberToClaimInsurance = scanner.next();
		ParkingDetails parkingDetails = new ParkingDetails(vehicleNumberToClaimInsurance);
		ParkingConsole parkingConsole = new ParkingConsole();
		if(parkingApplication.isVehicleParked(parkingConsole.getLotType(insuranceVehicleChoice), parkingDetails)) {
			System.out.println("Vehicle is parked safely! Insurance cannot be claimed");
		}else if(!parkingApplication.isVehicleParked(parkingConsole.getLotType(insuranceVehicleChoice), parkingDetails)) {
			System.out.println("No vehicle is parked with this vehicle number!");
		}else {
			System.out.println("Insurance Claimed!");
		}
    }
    
    private int getInputInt() {
	    int input = 0;
	    try {
	        input = scanner.nextInt();
	    } catch (InputMismatchException e) {
	        System.out.println("Invalid input! Please enter a valid number!");
	    }
	    scanner.nextLine();
	    return input;
	}
    
    private void run() {
        while (true) {
            System.out.println("\nEnter 1 for admin login, 2 for user login, 3 for insurance or 4 to exit");
            int choice = getInputInt();
            
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userAction();
                    break;
                case 3:
                	claimInsurance();
                    break;
                case 4:
                    System.out.println("Exiting program...");
                    return;
                default:
                    System.out.println("Invalid input!");
            }
        }
    }

    public static void main(String[] args){
    	ParkingConsole parkingConsole = new ParkingConsole();
    	parkingConsole.run();
    }
}