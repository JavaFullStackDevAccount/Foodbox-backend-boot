package com.foodbox.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodbox.dao.FoodBoxUserDao;
import com.foodbox.entities.FoodboxUser;

@Service
public class FoodBoxUserService {

	@Autowired
	private FoodBoxUserDao foodBoxUserDao;

	@Autowired
	private FoodBoxCartService foodBoxCartService;

	public FoodboxUser addFoodboxUser(FoodboxUser userToAdd) {

		try {
			if (!this.foodBoxUserDao.existsFoodboxUserByEmail(userToAdd.getEmail())) {

				FoodboxUser regiteredUser = this.foodBoxUserDao.save(userToAdd);

				if (foodBoxCartService.createCart(regiteredUser) != null)

					return userToAdd;

				else

					throw new Exception("Unable to create cart for new user");
			} else

				throw new Exception("User with email " + userToAdd.getEmail() + " already exits");

		} catch (Exception e) {

			e.printStackTrace();

			return null;

		}

	}

	public FoodboxUser getFoodboxUserById(long idOfUserToGet) {

		try {

			return foodBoxUserDao.findById(idOfUserToGet).get();

		} catch (Exception e) {

			return null;
		}
	}

	public FoodboxUser getFoodboxUsersOrderInfoById(long idOfUserToGet) {

		try {

			FoodboxUser existingUser = foodBoxUserDao.findById(idOfUserToGet).get();
			
			existingUser.setId(-10);
			
			existingUser.setEmail("_na_");
			
			existingUser.setPassword("_na_");
			
			existingUser.setUserTypeAdmin(false);
			
			return existingUser;

		} catch (Exception e) {

			return null;
		}
	}

	public FoodboxUser getFoodboxUserByEmail(String emailOfUser) {

		try {

			return foodBoxUserDao.findByEmail(emailOfUser);

		} catch (Exception e) {

			return null;
		}
	}

}
