package com.parkingLot;

import java.util.Random;

public class Insurance {
	
	private String insuranceId;

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public Insurance() {
		insuranceId = String.valueOf(new Random().nextLong());
		//100000000000l,999999999999l);
	}
}
