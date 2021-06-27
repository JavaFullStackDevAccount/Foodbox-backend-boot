package com.foodbox.models;

import java.util.List;

import com.foodbox.entities.FoodItem;
import com.foodbox.entities.FoodboxOrders;

public class OrdersResponseModel {

	private String orderId;

	private double total;

	private int discount;

	private double deliveryCharges;

	private double totalPayable;

	private List<FoodItem> itemsInOrder;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
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

	public List<FoodItem> getItemsInOrder() {
		return itemsInOrder;
	}

	public void setItemsInOrder(List<FoodItem> itemsInOrder) {
		this.itemsInOrder = itemsInOrder;
	}

	

}
