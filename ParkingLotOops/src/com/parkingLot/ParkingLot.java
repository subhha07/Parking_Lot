package com.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

	private int lotCapacity = 0;
	private int filledCount = 0;
	private List<FloorInformation> floorsList;
	
	public int getLotCapacity() {
		return lotCapacity;
	}
	public void setLotCapacity(int lotCapacity) {
		this.lotCapacity = lotCapacity;
	}
	public int getFilledCount() {
		return filledCount;
	}
	public void setFilledCount(int filledCount) {
		this.filledCount = filledCount;
	}
	public List<FloorInformation> getFloorsList() {
		return floorsList;
	}
	public void setFloorsList(List<FloorInformation> floorsList) {
		this.floorsList = floorsList;
	}

	public ParkingLot(LOT_TYPE lotType, Valets valets, int floorCount, int slotsPerFloor, int valetsAllocated) {
		valets.addValets(lotType, valetsAllocated);
		initializeFloors(floorCount, slotsPerFloor);
	}
//	private int valetsAllocated;

	private void initializeFloors(int floorCount, int slotsPerFloor) {
		floorsList = new ArrayList<>();
		for(int floorNo=0; floorNo<floorCount; floorNo++) {
			int finalSlots = slotsPerFloor;
			
			//place to park security personnel cycles -- 12th floor
			if(floorNo == floorCount-1) {
				finalSlots = finalSlots/2;
			}
			lotCapacity += finalSlots;
			floorsList.add(new FloorInformation(floorNo, finalSlots));
		}
	}
	
	public SlotInformation findAvailableSlot(ParkingDetails parkingDetails, int gateNo) {
		if(filledCount != lotCapacity) {
			for(FloorInformation floorInfo : floorsList) {
				if(floorInfo.get_isFilled() != true) {
					//Checking in exact gate
					for(SlotInformation slotInfo : floorInfo.getSlotList()) {
						if(gateNo == slotInfo.getGateNo() && slotInfo.getParkingDetails() == null) {
							return slotInfo;
						}
					}
					//Checking in different gates for availability
					for(SlotInformation slotInfo : floorInfo.getSlotList()) {
						if(gateNo != slotInfo.getGateNo() && slotInfo.getParkingDetails() == null) {
							return slotInfo;
						}
					}
				}
			}
		}
		return null;
	}
	
	public SlotInformation findParkedSlot(ParkingDetails parkingDetails, Boolean tokenValidationNeeded) {
		if(filledCount != 0) {
			for(FloorInformation floorInfo : floorsList) {
				if(floorInfo.getTotalSlots() != floorInfo.getAvailableSlotsCount()) {
					for(SlotInformation slotInfo : floorInfo.getSlotList()) {
						if(slotInfo.getParkingDetails() != null && slotInfo.isSlotMatched(parkingDetails, tokenValidationNeeded)){
							return slotInfo;
						}
					}
				}
			}
		}
		return null;
	}
	
	public void incrementFilledCount() {
		filledCount +=1;
	}
	
	public void decrementFilledCount() {
		filledCount -=1;
	}
	public void getVehicleFloorCapacity() 
	{
		System.out.println("\nSlot information for each floor");
		for(FloorInformation floors :floorsList)
		{
			 System.out.println("\nFloor " + formatFloorNumber(floors.getFloorNumber()) + ":");
             System.out.println("Total slots: " + floors.getTotalSlots());
             System.out.println("Available slots: " + floors.getAvailableSlotsCount());
             System.out.println("Occupied slots: " + (floors.getTotalSlots() - floors.getAvailableSlotsCount()));
		}
	}
	private String formatFloorNumber(int floorNumber) 
	{
		if(floorNumber == 0)
		{
			return "G";
		}
		return ""+floorNumber;
	}
}
