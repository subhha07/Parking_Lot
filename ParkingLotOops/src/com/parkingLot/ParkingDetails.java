package com.parkingLot;

public class ParkingDetails {
	
	private String vehicleNumber;
	private String tokenId;
	private Long parkingTime;

	public String getVehicleNumber() {
		return vehicleNumber;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public Long getParkingTime() {
		return parkingTime;
	}
	public void setParkingTime(Long parkingTime) {
		this.parkingTime = parkingTime;
	}
	
	public ParkingDetails(String vehicleNumber) {
		this.vehicleNumber=vehicleNumber;
	}
	
	public ParkingDetails(String vehicleNumber, String tokenId, Boolean isPark) {
		this.vehicleNumber = vehicleNumber;
		if(isPark) {
			this.tokenId = String.valueOf(vehicleNumber.hashCode());
			this.parkingTime = System.currentTimeMillis();
		}else {
			this.tokenId = tokenId;
		}
	}
}
