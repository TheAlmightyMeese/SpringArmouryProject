package com.qa.armoury.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.qa.armoury.domain.Weapon;

@Service
public class WeaponMethods implements WeaponService {

	
	private List<Weapon> weapons = new ArrayList<>();
	
	@Override
	public Weapon forge(Weapon newWeapon) {
		this.weapons.add(newWeapon);
		System.out.println("Congratulations you have Forged a " + newWeapon.getName() );
		System.out.println("It is a " + newWeapon.getType() + " Weapon");
		System.out.println("It is worth " + newWeapon.getGoldValue() + " Gold");
		System.out.println("It rolls " + newWeapon.getDamage() + " damage");
		Weapon body = this.weapons.get(this.weapons.size()-1);
		return body;
	}	
	
	@Override
	public List<Weapon> getWeapons(){
		System.out.println(" Here is a list of your weapons: ");
		weapons.stream().forEach(e -> System.out.println(e));
		return this.weapons;
	}
	
	@Override
	public Weapon getWeapon(Integer id) {
		System.out.println(this.weapons.get(id));
		return this.weapons.get(id);
	}
	
	@Override
	public Weapon reforge(Integer id, Weapon newWeapon) {
		System.out.println("Reforging " + id + " into " + newWeapon);
		Weapon reForge = this.weapons.set(id, newWeapon);
		return reForge;
	}
	
	@Override
	public boolean smelt(Integer id) {
		System.out.println("Weapon number " + id + " Will be destroyed");
		Weapon toRemove = this.weapons.get(id);
		this.weapons.remove(id.intValue());
		return !this.weapons.contains(toRemove);
	}

		
}
