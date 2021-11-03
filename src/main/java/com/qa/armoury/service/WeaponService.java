package com.qa.armoury.service;

import java.util.List;

import com.qa.armoury.domain.Weapon;

public interface WeaponService {
	
	Weapon forge(Weapon newWeapon);

	List<Weapon> getWeapons();
	
	Weapon getWeapon(Integer id);
	
	Weapon reforge(Integer id, Weapon newWeapon);
	
	boolean smelt(Integer id);
		
}
