package com.foodbox.dao;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.foodbox.entities.FoodboxOrders;

@Repository
public interface FoodBoxOrdersDao extends JpaRepository<FoodboxOrders, Long> {

	public List<FoodboxOrders> findOrdersByfoodBoxUserId(long foodBoxUserId, Sort sort);

}
