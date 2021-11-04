package com.qa.armoury.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.armoury.domain.Weapon;
import com.qa.armoury.service.WeaponService;

@RestController
@RequestMapping("/weapon")
public class WeaponController {

	private WeaponService service;

	public WeaponController(WeaponService service) {
		super();
		this.service = service;
	}

	// creates a new object from Weapon class in this instance
	@PostMapping("/forge")
	public ResponseEntity<Weapon> forge(@RequestBody Weapon newWeapon) {
		Weapon body = this.service.forge(newWeapon);
		return new ResponseEntity<Weapon>(body, HttpStatus.CREATED);
	}

	// Retrieves All weapons that have been created and prints them in console
	@GetMapping("/weaponRack")
	public List<Weapon> getWeapons() {
		return this.service.getWeapons();
	}

	// Retrieves a single weapon by its index number in the List
	@GetMapping("/weapon/{id}")
	public Weapon getWeapon(@PathVariable Integer id) {
		return this.service.getWeapon(id);
	}

	// Overwrites a weapon with a whole new set of attributes
	@PutMapping("/reforge/{id}")
	public ResponseEntity<Weapon> reforgeWeapon(@PathVariable Integer id, @RequestBody Weapon newWeapon) {
		Weapon reForge = this.service.reforge(id, newWeapon);
		return new ResponseEntity<Weapon>(reForge, HttpStatus.ACCEPTED);
	}

	// Removes the weapon in this instance via index
	@DeleteMapping("/smelt/{id}")
	public ResponseEntity<Weapon> smelt(@PathVariable Integer id) {
		boolean removed = this.service.smelt(id);
		if (removed) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
