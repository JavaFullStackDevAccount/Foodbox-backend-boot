package com.foodbox.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.FoodboxUser;



@Repository
public interface FoodBoxUserDao extends JpaRepository<FoodboxUser, Long> {

	public FoodboxUser findByEmail(String email);
	
	public boolean existsFoodboxUserByEmail(String email);
}
