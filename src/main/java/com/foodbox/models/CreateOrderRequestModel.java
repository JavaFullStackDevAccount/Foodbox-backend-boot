package com.foodbox.models;

import java.util.List;

import com.foodbox.entities.FoodboxOrders;

public class CreateOrderRequestModel {

	private long userId;

	private long userCartId;

	private List<FoodboxOrders> foodItemsToAddInOrder;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getUserCartId() {
		return userCartId;
	}

	public void setUserCartId(long userCartId) {
		this.userCartId = userCartId;
	}

	public List<FoodboxOrders> getFoodItemsToAddInOrder() {
		return foodItemsToAddInOrder;
	}

	public void setFoodItemsToAddInOrder(List<FoodboxOrders> foodItemsToAddInOrder) {
		this.foodItemsToAddInOrder = foodItemsToAddInOrder;
	}

}
