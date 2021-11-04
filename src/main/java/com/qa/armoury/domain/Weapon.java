package com.qa.armoury.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weapon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String type;
	private int goldValue;
	private String damage;

	public Weapon(Integer id, String name, String type, int goldValue, String damage) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.goldValue = goldValue;
		this.damage = damage;
	}

	public Weapon(String name, String type, int goldValue, String damage) {
		super();
		this.name = name;
		this.type = type;
		this.goldValue = goldValue;
		this.damage = damage;
	}

	public Weapon() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "Weapon = " + name + ". It is a " + type + " Weapon." + " It is worth " + goldValue + " Gold,"
				+ " This Weapon deals " + damage + ".";
	}

}
