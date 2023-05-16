package com.parkingLot;

public enum LOT_TYPE {

	CYCLE(0),
	BIKE(1),
	CAR(2) ,
	MINIBUS(3),
	CARGO(4);
	
	private int type;
	
	LOT_TYPE(int type) {
		this.type = type;
	}
	
	public int getType() {
		return type;
	}

}
