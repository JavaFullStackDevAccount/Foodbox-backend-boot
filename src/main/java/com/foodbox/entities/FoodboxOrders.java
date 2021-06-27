package com.foodbox.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class FoodboxOrders extends FoodItem {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String orderUuid;

	private long foodBoxUserId;

	private String orderedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderUuid() {
		return orderUuid;
	}

	public void setOrderUuid(String orderUuid) {
		this.orderUuid = orderUuid;
	}

	public long getFoodBoxUserId() {
		return foodBoxUserId;
	}

	public void setFoodBoxUserId(long foodBoxUserId) {
		this.foodBoxUserId = foodBoxUserId;
	}

	public String getOrderedOn() {
		return orderedOn;
	}

	public void setOrderedOn(String orderedOn) {
		this.orderedOn = orderedOn;
	}

}
