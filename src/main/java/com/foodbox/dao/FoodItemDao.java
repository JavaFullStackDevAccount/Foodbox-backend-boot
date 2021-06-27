package com.foodbox.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.FoodItem;


@Repository
public interface FoodItemDao extends JpaRepository<FoodItem, Long> {

	public List<FoodItem> findByCategory(String category);
	
}
