package com.parkingLot;

public class SlotInformation {
	
	private int floorNo;
	private int gateNo;
	private int slotNo;
	private ParkingDetails parkingDetails;
	private Insurance insurance;

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getGateNo() {
		return gateNo;
	}

	public void setGateNo(int gateNo) {
		this.gateNo = gateNo;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public ParkingDetails getParkingDetails() {
		return parkingDetails;
	}

	public void setParkingDetails(ParkingDetails parkingDetails) {
		this.parkingDetails = parkingDetails;
		this.insurance = new Insurance();
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public SlotInformation (int floorNo, int gateNo, int slotNo, ParkingDetails parkingDetails) {
		this.floorNo = floorNo;
		this.gateNo = gateNo;
		this.slotNo = slotNo;
		setParkingDetails(parkingDetails);
	}
	
	public boolean isSlotMatched(ParkingDetails parkingDetails, Boolean tokenValidationNeeded) {
		if(this.parkingDetails.getVehicleNumber().equals(parkingDetails.getVehicleNumber()) ) {
			if(tokenValidationNeeded) {
				if(this.parkingDetails.getTokenId().equals(parkingDetails.getTokenId())) {
					return true;
				}
				return false;
			}
			return true;
		}
		/*else if((parkingDetails.getOwnerName() == this.parkingDetails.getOwnerName()) && (parkingDetails.getVehicleName() == this.parkingDetails.getVehicleName()) ) {
			return true;
		}*/
		return false;
	}
	
	public void clearInformation() {
		parkingDetails = null;
		insurance = null;
	}
}
