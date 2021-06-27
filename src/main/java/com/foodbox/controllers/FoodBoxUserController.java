package com.foodbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entities.FoodboxUser;
import com.foodbox.services.FoodBoxUserService;

@RestController
@CrossOrigin
@RequestMapping("/foodbox/user")
public class FoodBoxUserController {

	@Autowired
	private FoodBoxUserService foodBoxService;
	
	
	
	@GetMapping
	public FoodboxUser getFoodboxUserWithId(@RequestParam(name = "id", required = true) long idOfUserToGet) {
	
		return foodBoxService.getFoodboxUserById(idOfUserToGet);
		
	}
	
	@GetMapping("/info")
	public FoodboxUser getFoodboxUsersAddressWithId(@RequestParam(name = "id", required = true) long idOfUserToGet) {
		
		return this.foodBoxService.getFoodboxUsersOrderInfoById(idOfUserToGet);
		
	}
	
	
	
}
