package com.foodbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entities.FoodItem;
import com.foodbox.entities.FoodboxCart;
import com.foodbox.models.AddItemToCartRequestModel;
import com.foodbox.models.FoodBoxCartResponseModel;
import com.foodbox.services.FoodBoxCartService;
import com.foodbox.services.FoodItemService;

@RestController
@CrossOrigin
@RequestMapping("/food/item/cart")
public class FoodBoxCartController {

	@Autowired
	private FoodBoxCartService foodBoxCartService;

	@Autowired
	private FoodItemService foodItemService;

	@GetMapping
	public FoodBoxCartResponseModel getCartForUserWith(@RequestParam("id") Long idOfUserToGetCartOff) {

		try {

			FoodboxCart usersCart = foodBoxCartService.getCart(idOfUserToGetCartOff);

			if (usersCart != null) {

				return new FoodBoxCartResponseModel(usersCart.getId(), usersCart.getFoodboxCartItems());

			} else {

				throw new Exception("No cart Found for user !!!");

			}

		} catch (Exception e) {

			return null;
		}

	}

	@PutMapping
	public FoodBoxCartResponseModel addItemToUserCartWith(@RequestParam("id") Long usersId,
			@RequestParam("fiid") Long idOfItemToAdd) {

		try {

			FoodItem itemToAddToCart = foodItemService.getFooItemById(idOfItemToAdd);

			FoodboxCart usersCart = this.foodBoxCartService.addItemToCart(usersId, itemToAddToCart);

			return new FoodBoxCartResponseModel(usersCart.getId(), usersCart.getFoodboxCartItems());

		} catch (Exception e) {

			return null;

		}
	}

	@DeleteMapping
	public FoodBoxCartResponseModel removeItemFromCartWith(@RequestParam("id") Long usersId,
			@RequestParam("fiid") Long idOfItemToAdd) {

		try {

			FoodItem itemToAddToCart = foodItemService.getFooItemById(idOfItemToAdd);

			FoodboxCart usersCart = this.foodBoxCartService.removeItemfromCart(usersId, itemToAddToCart);

			return new FoodBoxCartResponseModel(usersCart.getId(), usersCart.getFoodboxCartItems());

		} catch (Exception e) {

			return null;
		}

	}

}
