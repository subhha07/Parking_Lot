package com.parkingLot;

import java.util.List;

public class AdminTools 
{
	List<ParkingLot> parking_lots;
	int totalSlots;
	int availableSlots;
	int filledSlots;
	
	public AdminTools(List<ParkingLot> parking_lots)
	{
		this.parking_lots= parking_lots;
		
		for(int i=0;i<parking_lots.size();i++)
		{
			totalSlots += parking_lots.get(i).getLotCapacity();
			filledSlots += parking_lots.get(i).getFilledCount();
		}
		availableSlots= totalSlots - filledSlots;
	}
	
	public int getTotalSlots()
	{
		return totalSlots;
	}
	
	public int getAvailableSlots()
	{
		return availableSlots;
	}
	
	public int getFilledSlots()
	{	
		return filledSlots;
	}
	
}
