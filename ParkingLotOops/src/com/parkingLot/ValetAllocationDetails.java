package com.parkingLot;

import java.util.List;

public class ValetAllocationDetails {

	private int valetsAllocated;
	private int valetsAvailable;

	public int getValetsAllocated() {
		return valetsAllocated;
	}
	public void setValetsAllocated(int valetsAllocated) {
		this.valetsAllocated = valetsAllocated;
	}
	public int getValetsAvailable() {
		return valetsAvailable;
	}
	public void setValetsAvailable(int valetsAvailable) {
		this.valetsAvailable = valetsAvailable;
	}
	
	public ValetAllocationDetails(List<Valet> valetList, LOT_TYPE lotType) {
		Long currentTime = System.currentTimeMillis();
		boolean isAllocatedValet = true;
		for(Valet valet : valetList) {
			if(lotType == null) {
				valetsAllocated+=1;
			}else if(valet.getLotType().getType() == lotType.getType()) {
				valetsAllocated+=1;
			}else {
				isAllocatedValet = false;
			}
			
			if(isAllocatedValet && valet.isAvailable(currentTime)) {
				valetsAvailable+=1;
			}
		}	
	}
	
	
}
