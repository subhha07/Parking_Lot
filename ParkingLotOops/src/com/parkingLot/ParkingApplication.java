package com.parkingLot;

import java.util.ArrayList;
import java.util.List;

public class ParkingApplication {
	
	private static final int FLOOR_COUNT = 13;
	private List<ParkingLot> parkingLots;
	private Valets valets = new Valets();
	public ParkingApplication() {
		initializeParkingLot();
		
	}
	
	public void initializeParkingLot() {
		parkingLots = new ArrayList<>();
		parkingLots.add(new ParkingLot(LOT_TYPE.CYCLE, valets, FLOOR_COUNT, 4998, 1625));
		parkingLots.add(new ParkingLot(LOT_TYPE.BIKE, valets, FLOOR_COUNT, 1248, 406));//Bike ParkingLot 2.5%
		parkingLots.add(new ParkingLot(LOT_TYPE.CAR, valets, FLOOR_COUNT, 498, 647));//Car ParkingLot 10%
		parkingLots.add(new ParkingLot(LOT_TYPE.MINIBUS, valets, FLOOR_COUNT, 150, 195));//Minibus ParkingLot 10%
		parkingLots.add(new ParkingLot(LOT_TYPE.CARGO, valets, FLOOR_COUNT, 60, 78));//Cargo ParkingclearInformationLot 10%
//		parkingLots.add(new ParkingLot(LOT_TYPE.BIKE, valets, FLOOR_COUNT, 16, 3));//Bike ParkingLot
//		parkingLots.add(new ParkingLot(LOT_TYPE.CAR, valets, FLOOR_COUNT, 6, 2));//Car ParkingLot
//		parkingLots.add(new ParkingLot(LOT_TYPE.MINIBUS, valets, FLOOR_COUNT, 5, 2));//Minibus ParkingLot
//		parkingLots.add(new ParkingLot(LOT_TYPE.CARGO, valets, FLOOR_COUNT, 3, 1));//Cargo ParkingLot
	}
	
	public SlotInformation parkVehicle(LOT_TYPE lot, ParkingDetails parkingDetails, int gateNo) {
		ParkingLot parkingLot = parkingLots.get(lot.getType());
		
		SlotInformation availableSlot = parkingLot.findAvailableSlot(parkingDetails, gateNo);
		if(availableSlot != null) {
			availableSlot.setParkingDetails(parkingDetails);
			parkingLot.getFloorsList().get(availableSlot.getFloorNo()).makeSlotUnAvailable();
			parkingLot.incrementFilledCount();
		}
		return availableSlot;
	}
	
	public boolean unparkVehicle(LOT_TYPE lot, ParkingDetails parkingDetails) {
		ParkingLot parkingLot = parkingLots.get(lot.getType());
		SlotInformation matchedSlot = parkingLot.findParkedSlot(parkingDetails, true);
		if(matchedSlot != null) {
			matchedSlot.clearInformation();
			parkingLot.getFloorsList().get(matchedSlot.getFloorNo()).makeSlotAvailable();
			parkingLot.decrementFilledCount();
			return true;
		}
		return false;
	}
	
	public boolean isVehicleParked(LOT_TYPE lot, ParkingDetails parkingDetails) {
		ParkingLot parkingLot = parkingLots.get(lot.getType());
		SlotInformation slotInformation = parkingLot.findParkedSlot(parkingDetails, false);
		if(slotInformation != null && slotInformation.getParkingDetails() != null && (slotInformation.getParkingDetails().getVehicleNumber().equals(parkingDetails.getVehicleNumber())) ) {
			return true;
		}
		return false;
	}

	public List<ParkingLot> getParkingLots() 
	{
		return parkingLots;
	}

	public ParkingLot getParkingLot(LOT_TYPE lot) {
		return parkingLots.get(lot.getType());
	}

	public Valets getValets() {
		return valets;
	}
}
