package com.foodbox.models;

import com.foodbox.entities.FoodItem;

public class AddItemToCartRequestModel {
	
	private Long userId;
	
	private FoodItem foodItemToAddToCart;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public FoodItem getFoodItemToAddToCart() {
		return foodItemToAddToCart;
	}

	public void setFoodItemToAddToCart(FoodItem foodItemToAddToCart) {
		this.foodItemToAddToCart = foodItemToAddToCart;
	}
	
	
	

}
