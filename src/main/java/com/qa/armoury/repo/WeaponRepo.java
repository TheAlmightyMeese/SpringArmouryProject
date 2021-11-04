package com.qa.armoury.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.armoury.domain.Weapon;

@Repository
public interface WeaponRepo extends JpaRepository<Weapon, Integer> {

}
