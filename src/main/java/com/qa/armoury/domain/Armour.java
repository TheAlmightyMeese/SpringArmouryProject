package com.qa.armoury.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Armour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private int value;
	private int ac;
	private boolean stealthDisAdv;
	private int weight;

	public Armour(Integer id, String name, int value, int ac, boolean stealthDisAdv, int weight) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.ac = ac;
		this.stealthDisAdv = stealthDisAdv;
		this.weight = weight;
	}

	public Armour(String name, int value, int ac, boolean stealthDisAdv, int weight) {
		super();
		this.name = name;
		this.value = value;
		this.ac = ac;
		this.stealthDisAdv = stealthDisAdv;
		this.weight = weight;
	}

	public Armour() {
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getAc() {
		return ac;
	}

	public void setAc(int ac) {
		this.ac = ac;
	}

	public boolean isStealthDisAdv() {
		return stealthDisAdv;
	}

	public void setStealthDisAdv(boolean stealthDisAdv) {
		this.stealthDisAdv = stealthDisAdv;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Armour is " + name + " It's worth " + value + " Gold." + " It provides an Armour Class of " + ac
				+ " not including Dex bonuses." + " Wearing this gives Disadvantage to Stealth: " + stealthDisAdv
				+ ". It wieghs " + weight + "lbs.";
	}

}
