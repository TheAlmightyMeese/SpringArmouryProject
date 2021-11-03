package com.qa.armoury.domain;


public class Weapon {

	private String name;
	private String type;
	private int goldValue;
	private String damage;
	
	public Weapon(String name,String type, int goldValue,String damage) {
		super();
		this.name = name;
		this.type = type;
		this.goldValue = goldValue;
		this.damage = damage;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGoldValue() {
		return goldValue;
	}

	public void setGoldValue(int goldValue) {
		this.goldValue = goldValue;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	@Override
	public String toString() {
		return "Weapon = " + name + ". It is a " + type + " Weapon."+ " It is worth " + goldValue + " Gold," + " This Weapon deals " + damage + ".";
	}

	
	
	
	
	
}
