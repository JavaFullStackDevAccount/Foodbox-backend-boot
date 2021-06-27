package com.foodbox.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.dao.FoodBoxCartDao;
import com.foodbox.entities.FoodItem;
import com.foodbox.entities.FoodboxCart;
import com.foodbox.entities.FoodboxUser;

@Service
public class FoodBoxCartService {

	@Autowired
	private FoodBoxCartDao foodBoxCartDao;

	@Autowired
	private FoodBoxUserService foodBoxUserService;

	public FoodboxCart getCart(Long idOfTheUserToGetCartOff) {

		try {

			return foodBoxCartDao.findByFoodBoxUserId(idOfTheUserToGetCartOff);

		} catch (Exception e) {

			return null;
		}

	}

	public Long getCartId(Long idOfTheUserToGetCartOff) {

		try {

			return foodBoxCartDao.findByFoodBoxUserId(idOfTheUserToGetCartOff).getId();

		} catch (Exception e) {

			return null;
		}
	}

	public FoodboxCart createCart(FoodboxUser newlyAddedUser) {

		try {

			FoodboxCart newCartForNewUser = new FoodboxCart();

			newCartForNewUser.setFoodBoxUser(newlyAddedUser);

			return this.foodBoxCartDao.save(newCartForNewUser);

		} catch (Exception e) {

			return null;

		}

	}

	public FoodboxCart addItemToCart(Long idOfTheUserToGetCartOff, FoodItem foodItemToAddToCart) {

		try {

			FoodboxCart existingCartOfTheUser = this.getCart(idOfTheUserToGetCartOff);

			if (existingCartOfTheUser != null) {

				Set<FoodItem> existingItemsInCart = existingCartOfTheUser.getFoodboxCartItems();

				existingItemsInCart.add(foodItemToAddToCart);

				return this.foodBoxCartDao.save(existingCartOfTheUser);

			} else {

				throw new Exception("NoExisting cart found for the user !!!!");
			}

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	public FoodboxCart removeItemfromCart(Long idOfTheUserToGetCartOff,  FoodItem foodItemToRemove) {

		try {

			FoodboxCart existingCartOfTheUser = this.getCart(idOfTheUserToGetCartOff);

			if (existingCartOfTheUser != null) {

				Set<FoodItem> existingItemsInCart = existingCartOfTheUser.getFoodboxCartItems();

				existingCartOfTheUser.setFoodboxCartItems(existingItemsInCart.stream().filter(itemInCart -> {
					return itemInCart.getId() != foodItemToRemove.getId();
				}).collect(Collectors.toSet()));

				return this.foodBoxCartDao.save(existingCartOfTheUser);

			} else {

				throw new Exception("NoExisting cart found for the user !!!!");
			}

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	/*
	 * public void removeFromCart(Long itemId) {
	 * 
	 * 
	 * FoodboxCart usersCart = getCart(1L);
	 * 
	 * 
	 * Set<FoodboxCartItems> itemsdInCart = usersCart.getFoodboxCartItems();
	 * 
	 * Set<FoodboxCartItems> removedItems = itemsdInCart.stream().filter(item ->
	 * {return item.getId() != itemId;}).collect(Collectors.toSet());
	 * 
	 * usersCart.setFoodboxCartItems(removedItems);
	 * 
	 * this.foodBoxCartDao.save(usersCart);
	 * 
	 * 
	 * //System.out.println(removedItems); }
	 */

}
