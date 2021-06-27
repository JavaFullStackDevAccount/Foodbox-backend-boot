package com.foodbox.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.foodbox.models.CreateOrderRequestModel;
import com.foodbox.models.OrdersResponseModel;
import com.foodbox.services.FoodBoxOrdersService;

@RestController
@CrossOrigin
@RequestMapping("/food/item/orders")
public class FoodBoxOrdersController {

	@Autowired
	private FoodBoxOrdersService  foodBoxOrdersService;
	
	@GetMapping
	public List<OrdersResponseModel> getUsersOrders(@RequestParam("id") long usersId){
		
		return this.foodBoxOrdersService.getAllOrdersOfUser(usersId);
		
	}
	
	
	@PostMapping
	public boolean createUsersOrders(@RequestBody CreateOrderRequestModel newOrderToAdd){
		
		return this.foodBoxOrdersService.createNewUserOrder(newOrderToAdd.getUserId(), newOrderToAdd.getFoodItemsToAddInOrder());
		
		
		
	}
}
