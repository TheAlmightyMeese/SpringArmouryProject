package com.qa.armoury.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import com.qa.armoury.domain.Armour;
import com.qa.armoury.repo.ArmourRepo;

@Service
public class ArmourServiceDBLink implements ArmourService {

	private ArmourRepo repo;

	public ArmourServiceDBLink(ArmourRepo repo) {
		super();
		this.repo = repo;
	}

	@Override
	public Armour forge(Armour newArmour) {
		System.out.println("Congratulations you have Forged a " + newArmour.getName());
		System.out.println("It is worth " + newArmour.getValue() + " Gold");
		System.out.println("It provides " + newArmour.getAc() + " potentially with a Dex Bonus");
		System.out.println("It weighs " + newArmour.getWeight() + " lbs");
		System.out.println("It give Disadvantage to Stealth " + newArmour.isStealthDisAdv());
		return this.repo.save(newArmour);
	}

	@Override
	public List<Armour> getArmours() {
		return this.repo.findAll();
	}

	@Override
	public Armour getArmour(Integer id) {
		return this.repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("The Armour " + id + " may have been stolen!"));
	}

	@Override
	public Armour reforge(Integer id, Armour newArmour) {
		Armour existing = this.getArmour(id);
		System.out.println("Reforging " + id + " into " + newArmour);

		existing.setName(newArmour.getName());
		existing.setValue(newArmour.getValue());
		existing.setAc(newArmour.getAc());
		existing.setWeight(newArmour.getWeight());
		existing.setStealthDisAdv(newArmour.isStealthDisAdv());

		return this.repo.save(existing);

	}

	@Override
	public boolean smelt(Integer id) {
		System.out.println("Armour number " + id + " Will be destroyed");
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
