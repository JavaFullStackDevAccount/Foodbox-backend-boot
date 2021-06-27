package com.foodbox.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.dao.FoodItemDao;
import com.foodbox.entities.FoodItem;

@Service
public class FoodItemService {

	@Autowired
	private FoodItemDao productDao;

	public List<FoodItem> getAllFoodItems() {

		try {

			return productDao.findAll();

		} catch (Exception e) {

			return new ArrayList<FoodItem>();
		}

	}

	public FoodItem getFooItemById(long foodItemId) {

		try {

			return productDao.findById(foodItemId).get();

		} catch (Exception e) {

			return null;
		}

	}

	public FoodItem addFoodItem(FoodItem foodItemToAdd) {

		try {

			return productDao.save(foodItemToAdd);

		} catch (Exception e) {

			return null;
		}

	}

	public void deleteFoodItem(long idOfFoodItemtoDelete) {

		if (productDao.existsById(idOfFoodItemtoDelete)) {

			productDao.deleteById(idOfFoodItemtoDelete);

		}

	}

	public FoodItem updateFoodItem(FoodItem foodItemToUpdate) {

		try {

			if (productDao.existsById(foodItemToUpdate.getId()))

				return addFoodItem(foodItemToUpdate);

			else

				throw new Exception("Unable to update, record dosent exists");

		} catch (Exception e) {

			return null;
		}

	}

	public List<FoodItem> getFoodItemByCategory(int categoryNumber) {

		List<FoodItem> foodItemByCategory = new ArrayList<FoodItem>();

		try {

			switch (categoryNumber) {

			case 1:
				foodItemByCategory.addAll(this.productDao.findByCategory("Veg"));
				break;
			case 2:
				foodItemByCategory.addAll(this.productDao.findByCategory("Chicken"));
				break;
			case 3:
				foodItemByCategory.addAll(this.productDao.findByCategory("Pizza"));
				break;
			case 4:
				foodItemByCategory.addAll(this.productDao.findByCategory("Indian"));
				break;
			default:
				return this.getAllFoodItems();

			}

			if (foodItemByCategory.size() > 0)

				return foodItemByCategory;

			else
				throw new Exception("No item in category ");

		} catch (Exception e) {

			return foodItemByCategory;
		}

	}

}
