package com.foodbox.models;

import java.text.DecimalFormat;
import java.util.Set;
import java.util.stream.Collectors;

import com.foodbox.entities.FoodItem;

public class FoodBoxCartResponseModel {

	private long cartId;

	private Set<FoodItem> itemsInCart;

	private double total;

	private double discount;

	private double deliveryCharges;

	private double totalPayable;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public Set<FoodItem> getItemsInCart() {
		return itemsInCart;
	}

	public void setItemsInCart(Set<FoodItem> itemsInCart) {
		this.itemsInCart = itemsInCart;
		this.discount = 10;
		this.total = this.getunDiscountedTotal();
		this.totalPayable = this.getDiscountedTotal();
		this.deliveryCharges = 12;
	}

	private Set<FoodItem> getVisibleFoodItemsInCart() {
		return this.itemsInCart.stream().filter(itemInCart -> {
			return itemInCart.isPublicVisiblity();
		}).collect(Collectors.toSet());
	}

	private double getunDiscountedTotal() {

		Set<FoodItem> allPublicallyVisibleItemsInCart = getVisibleFoodItemsInCart();

		return allPublicallyVisibleItemsInCart.stream().map(itemInCart -> {
			return itemInCart.getPrice();
		}).reduce((double) 0, (priceOfItemInCart1, priceOfItemInCart2) -> priceOfItemInCart1 + priceOfItemInCart2);

	}

	private double getDiscountedTotal() {

		double unDiscountedTotal = getunDiscountedTotal() + discount;

		double discountToSubtractFromunDiscountedTotal = (unDiscountedTotal / 100) * 10;
		
		DecimalFormat decimalFormat = new DecimalFormat(".##");
		
		return Double.parseDouble(decimalFormat.format(Math.abs(discountToSubtractFromunDiscountedTotal - unDiscountedTotal)));

		//return Math.abs(discountToSubtractFromunDiscountedTotal - unDiscountedTotal);

	}

	public FoodBoxCartResponseModel(long cartId, Set<FoodItem> itemsInCart) {
		super();
		this.cartId = cartId;
		this.itemsInCart = itemsInCart;
		this.deliveryCharges = 12;
		this.discount = 10;
		this.total = this.getunDiscountedTotal();
		this.totalPayable = this.getDiscountedTotal();

	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(double deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	public double getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(double totalPayable) {
		this.totalPayable = totalPayable;
	}
	
	

}
