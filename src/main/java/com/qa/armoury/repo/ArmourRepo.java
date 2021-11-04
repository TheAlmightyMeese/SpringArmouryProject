package com.qa.armoury.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.armoury.domain.Armour;

@Repository
public interface ArmourRepo extends JpaRepository<Armour, Integer> {

}
