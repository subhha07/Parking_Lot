package com.parkingLot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Valets {
	
	private int valetCount;
	private List<Valet> valetsList = new ArrayList<>();
	
	public int getValetCount() {
		return valetCount;
	}
	public void setValetCount(int valetCount) {
		this.valetCount = valetCount;
	}
	public List<Valet> getValetsList() {
		return valetsList;
	}
	
	public Valet getAvailableValet(LOT_TYPE lotType) {
		Long currentTime = System.currentTimeMillis();
		for(Valet valet : valetsList) {
			if(valet.getLotType() == lotType && valet.isAvailable(currentTime)) {
				return valet;
			}
		}
		return null;
	}
	
	public Valet getAvailableValetInAllSlots(LOT_TYPE skill) {
		Long currentTime = System.currentTimeMillis();
		for(Valet valet : valetsList) {
			if(valet.hasSkill(skill) && valet.isAvailable(currentTime)) {
				return valet;
			}
		}
		return null;
	}
	
	public void addValets(LOT_TYPE lotType, int count) {
		for(int valetId=1; valetId<=count; valetId++) {
			valetsList.add(createValet(lotType, valetId));
		}
	}
	
	public Valet createValet(LOT_TYPE givenSkill, int valetId) {
		Valet valet = new Valet();
		valet.setValetId(valetId);
		valet.setSkill(givenSkill);
		valet.setLotType(givenSkill);
		
		for(int skillValue= 0; skillValue<=4; skillValue++) {
			Boolean canProvideSkill = new Random().nextBoolean();
			if(givenSkill.getType() == skillValue) {
				continue;
			}else if(canProvideSkill) {
				valet.setSkill(valet.getSkillType(skillValue));
			}else if(skillValue > givenSkill.getType()){
				break;
			}
		}
		return valet;
	}
}
