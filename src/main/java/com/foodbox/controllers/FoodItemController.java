package com.foodbox.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entities.FoodItem;
import com.foodbox.services.FoodBoxCartService;
import com.foodbox.services.FoodItemService;

@RestController
@RequestMapping("/food/item")
@CrossOrigin
public class FoodItemController {

	@Autowired
	private FoodItemService foodItemService;

	@Autowired
	private FoodBoxCartService foodBoxCartService;

	private String getCategoryForReload(int categoryNumber) {

		String categories[] = { "Veg", "Chicken", "Pizza", "Indian" };

		return categories[categoryNumber];
	}

	@GetMapping("/reload")
	public void reloadFoodItems() {

		for (int i = 0; i < 30; i++) {

			FoodItem foodItem = new FoodItem();

			foodItem.setTitle("Food item " + i);

			foodItem.setPrice(543);

			foodItem.setRating(new Random().nextInt(4) + 1);

			foodItem.setCategory(this.getCategoryForReload(new Random().nextInt(4)));

			foodItem.setDiscount(10);

			foodItem.setPublicVisiblity((i % 2 == 0));

			foodItem.setImageUrl(
					"https://images2.minutemediacdn.com/image/upload/c_crop,h_1126,w_2000,x_0,y_181/f_auto,q_auto,w_1100/v1554932288/shape/mentalfloss/12531-istock-637790866.jpg");

			foodItem.setDescription("This is a sample food item for food item " + i
					+ " description for testing purposes only and will br removed from production");

			foodItemService.addFoodItem(foodItem);
		}

	}

	@GetMapping("/")
	public List<FoodItem> getAllFoodItems() {

		return foodItemService.getAllFoodItems();

	}

	@GetMapping("/category")

	public List<FoodItem> getAllFoodItems(@RequestParam("catnum") int foodItemCategoryNumber) {

		return foodItemService.getFoodItemByCategory(foodItemCategoryNumber);

	}

	@GetMapping("/getfooditem")
	public FoodItem getAllFoodItems(@RequestParam("foodItemId") long foodItemId) {

		return foodItemService.getFooItemById(foodItemId);

	}

	@PostMapping("/add")
	public FoodItem addFoodItem(@RequestBody FoodItem foodItemToAdd) {

		return foodItemService.addFoodItem(foodItemToAdd);

	}

	@DeleteMapping("/remove")
	public void deleteFoodItem(@RequestParam("foodItemId") long foodItemId) {

		foodItemService.deleteFoodItem(foodItemId);

	}

	@PutMapping("/update")
	public FoodItem updateFoodItem(@RequestBody FoodItem foodItemToUpdate) {

		return foodItemService.updateFoodItem(foodItemToUpdate);
	}

}
