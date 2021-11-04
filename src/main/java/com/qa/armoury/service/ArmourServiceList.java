package com.qa.armoury.service;

import java.util.ArrayList;
import java.util.List;

import com.qa.armoury.domain.Armour;

public class ArmourServiceList implements ArmourService {

	private List<Armour> armour = new ArrayList<>();

	@Override
	public Armour forge(Armour newArmour) {
		this.armour.add(newArmour);
		System.out.println("Congratulations you have Forged a " + newArmour.getName());
		System.out.println("It is worth " + newArmour.getValue() + " Gold");
		System.out.println("It provides " + newArmour.getAc() + " potentially with a Dex Bonus");
		System.out.println("It weighs " + newArmour.getWeight() + " lbs");
		System.out.println("It give Disadvantage to Stealth " + newArmour.isStealthDisAdv());
		Armour body = this.armour.get(this.armour.size() - 1);
		return body;
	}

	@Override
	public List<Armour> getArmours() {
		System.out.println(" Here is a list of your armours: ");
		armour.stream().forEach(e -> System.out.println(e));
		return this.armour;
	}

	@Override
	public Armour getArmour(Integer id) {
		System.out.println(this.armour.get(id));
		return this.armour.get(id);
	}

	@Override
	public Armour reforge(Integer id, Armour newArmour) {
		System.out.println("Reforging " + id + " into " + newArmour);
		Armour reForge = this.armour.set(id, newArmour);
		return reForge;
	}

	@Override
	public boolean smelt(Integer id) {
		System.out.println("Armour number " + id + " Will be destroyed");
		Armour toRemove = this.armour.get(id);
		this.armour.remove(id.intValue());
		return !this.armour.contains(toRemove);
	}

}
