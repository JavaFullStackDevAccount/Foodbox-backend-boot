package com.foodbox.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.FoodboxCart;


@Repository
public interface FoodBoxCartDao extends JpaRepository<FoodboxCart, Long> {

	public FoodboxCart findByFoodBoxUserId(long FoodBoxUserId);
}
