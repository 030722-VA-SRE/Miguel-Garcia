package com.revature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.models.Flavor;

public interface FlavorRepository extends JpaRepository<Flavor, Integer>{

}
