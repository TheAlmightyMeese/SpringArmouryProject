package com.qa.armoury.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.armoury.domain.Weapon;
import com.qa.armoury.repo.WeaponRepo;

@Service
public class WeaponServiceDBLink implements WeaponService {

	private WeaponRepo repo;

	public WeaponServiceDBLink(WeaponRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Weapon forge(Weapon newWeapon) {
		System.out.println("Congratulations you have Forged a " + newWeapon.getName());
		System.out.println("It is a " + newWeapon.getType() + " Weapon");
		System.out.println("It is worth " + newWeapon.getGoldValue() + " Gold");
		System.out.println("It rolls " + newWeapon.getDamage() + " damage");
		return this.repo.save(newWeapon);
	}

	@Override
	public List<Weapon> getWeapons() {
		return this.repo.findAll();
	}

	@Override
	public Weapon getWeapon(Integer id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The Weapon " + id + " may have been stolen!"));
	}

	@Override
	public Weapon reforge(Integer id, Weapon newWeapon) {
		Weapon existing = this.getWeapon(id);
		System.out.println("Reforging " + id + " into " + newWeapon);

		existing.setName(newWeapon.getName());
		existing.setType(newWeapon.getType());
		existing.setGoldValue(newWeapon.getGoldValue());
		existing.setDamage(newWeapon.getDamage());

		return this.repo.save(existing);

	}

	@Override
	public boolean smelt(Integer id) {
		System.out.println("Weapon number " + id + " Will be destroyed");
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
