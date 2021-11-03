package com.qa.armoury.service;

import java.util.List;

import com.qa.armoury.domain.Armour;

public interface ArmourService {

	Armour forge(Armour newArmour);

	List<Armour> getArmours();

	Armour getArmour(Integer id);

	Armour reforge(Integer id, Armour newArmour);

	boolean smelt(Integer id);
}
