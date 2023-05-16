package com.parkingLot;

public class Valet {
	
	private static final int PARKING_DURATION_MILLISECONDS = 60000;
	
	private int valetId;
	private boolean driveCycle;
	private boolean driveBike;
	private boolean driveCar;
	private boolean driveMiniBus;
	private boolean driveCargo;
	private Long lastParkedTime;
	private LOT_TYPE lotType;

	public int getValetId() {
		return valetId;
	}
	public void setValetId(int valetId) {
		this.valetId = valetId;
	}
	public boolean isDriveCycle() {
		return driveCycle;
	}
	public void isDriveCycle(boolean driveCycle) {
		this.driveCycle = driveCycle;
	}
	public boolean isDriveBike() {
		return driveBike;
	}
	public void setDriveBike(boolean driveBike) {
		this.driveBike = driveBike;
	}
	public boolean isDriveCar() {
		return driveCar;
	}
	public void setDriveCar(boolean driveCar) {
		this.driveCar = driveCar;
	}
	public boolean isDriveMiniBus() {
		return driveMiniBus;
	}
	public void setDriveMiniBus(boolean driveMiniBus) {
		this.driveMiniBus = driveMiniBus;
	}
	public boolean isDriveCargo() {
		return driveCargo;
	}
	public void setDriveCargo(boolean driveCargo) {
		this.driveCargo = driveCargo;
	}
	public Long getLastParkedTime() {
		return lastParkedTime;
	}
	public void setLastParkedTime(Long lastParkedTime) {
		this.lastParkedTime = lastParkedTime;
	}
	public LOT_TYPE getLotType() {
		return lotType;
	}
	public void setLotType(LOT_TYPE lotType) {
		this.lotType = lotType;
	}
	
	public boolean hasSkill(LOT_TYPE skill) {
		switch(skill.getType()) {
			case 0:
				return driveCycle;
			case 1:
				return driveBike;
			case 2:
				return driveCar;
			case 3:
				return driveMiniBus;
			case 4:
				return driveCargo;
		}
		return false;
	}
	
	public void setSkill(LOT_TYPE skill) {
		switch(skill.getType()) {
			case 0:
				driveCycle = true;
			case 1:
				driveBike = true;
				break;
			case 2:
				driveCar = true;
				break;
			case 3:
				driveMiniBus = true;
				break;
			case 4:
				driveCargo = true;
				break;
		}
	}
	
	public LOT_TYPE getSkillType(int skillValue) {
        switch (skillValue) {
        	case 0:
        		return LOT_TYPE.CYCLE;
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
	
	public boolean isAvailable(Long currentTime) {
		if(lastParkedTime == null || (lastParkedTime <= (currentTime - PARKING_DURATION_MILLISECONDS)) ) {
			return true;
		}
		return false;
	}
}
