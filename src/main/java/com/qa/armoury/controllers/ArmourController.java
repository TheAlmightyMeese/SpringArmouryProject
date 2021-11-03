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

import com.qa.armoury.domain.Armour;
import com.qa.armoury.service.ArmourService;

@RestController
@RequestMapping("/Armour")
public class ArmourController {

	private ArmourService service;

	public ArmourController(ArmourService service) {
		super();
		this.service = service;
	}

	// creates a new object from Armour class in this instance
	@PostMapping("/forge")
	public ResponseEntity<Armour> forge(@RequestBody Armour newArmour) {
		Armour body = this.service.forge(newArmour);
		return new ResponseEntity<Armour>(body, HttpStatus.CREATED);
	}

	// Retrieves All armours that have been created and prints them in console
	@GetMapping("/armourRack")
	public List<Armour> getArmours() {
		return this.service.getArmours();
	}

	// Retrieves a single armour by its index number in the List
	@GetMapping("/armour/{id}")
	public Armour getArmour(@PathVariable Integer id) {
		return this.service.getArmour(id);
	}

	// Overwrites a armour with a whole new set of attributes
	@PutMapping("/reforge/{id}")
	public ResponseEntity<Armour> reforgeArmour(@PathVariable Integer id, @RequestBody Armour newArmour) {
		Armour reForge = this.service.reforge(id, newArmour);
		return new ResponseEntity<Armour>(reForge, HttpStatus.ACCEPTED);
	}

	// Removes the armour in this instance via index
	@DeleteMapping("/smelt/{id}")
	public ResponseEntity<Armour> smelt(@PathVariable Integer id) {
		boolean removed = this.service.smelt(id);
		if (removed) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
