package com.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class FloorInformation {
	
	private int floorNumber;
	private boolean isFilled = false;
	private int totalSlots;
	private int availableSlotsCount;
	private List<SlotInformation> slotList;

	public int getFloorNumber() {
		return floorNumber;
	}
	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}
	public boolean get_isFilled() {
		return isFilled;
	}
	public void set_isFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
	public int getTotalSlots() {
		return totalSlots;
	}
	public void setTotalSlots(int totalSlots) {
		this.totalSlots = totalSlots;
	}
	public int getAvailableSlotsCount() {
		return availableSlotsCount;
	}
	public void setAvailableSlotsCount(int availableSlotsCount) {
		this.availableSlotsCount = availableSlotsCount;
	}
	public List<SlotInformation> getSlotList() {
		return slotList;
	}
	public FloorInformation(int floorNumber, int totalSlots) {
		this.floorNumber = floorNumber;
		this.totalSlots = totalSlots;
		this.availableSlotsCount = totalSlots;
		initializeSlots();
	}
	
	private void initializeSlots() {
		slotList = new ArrayList<>();
		for(int slotNumber=1; slotNumber<=totalSlots; slotNumber++) {
			
			double ratio = (double) slotNumber / (double) totalSlots;
			int gateNumber = getGateNoByRatio(ratio);
			slotList.add(new SlotInformation(floorNumber,gateNumber, slotNumber, null));//(floor no., gate no., slot no., 
		}
	}
	
	//call the method after unparking
	public void makeSlotAvailable() {
		availableSlotsCount +=1;
	}
	
	//call the method after parking
	public void makeSlotUnAvailable() {
		availableSlotsCount -=1;
		if(availableSlotsCount == 0) {
			isFilled = true;
		}
	}
	
	public int getGateNoByRatio(double ratio) {
		if(ratio <= 0.334) {
			return 1;
		}else if(ratio <= 0.667) {
			return 2;
		}else {
			return 3;
		}
	}
}
