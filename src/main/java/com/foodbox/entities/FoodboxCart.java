package com.foodbox.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class FoodboxCart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "food_box_user_id", referencedColumnName = "id")
	private FoodboxUser foodBoxUser;

	//@OneToMany(cascade=CascadeType.ALL)
//	private Set<FoodboxCartItems> foodboxCartItems;

	
	@OneToMany(cascade=CascadeType.ALL)
	private Set<FoodItem> cartItems;

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public FoodboxUser getFoodBoxUser() {
		return foodBoxUser;
	}

	public void setFoodBoxUser(FoodboxUser foodBoxUser) {
		this.foodBoxUser = foodBoxUser;
	}

	/*public Set<FoodboxCartItems> getFoodboxCartItems() {
		return foodboxCartItems;
	}

	public void setFoodboxCartItems(Set<FoodboxCartItems> foodboxCartItems) {
		this.foodboxCartItems = foodboxCartItems;
	}*/
	
	

	@Override
	public String toString() {
		return "FoodboxCart [id=" + id + ", foodBoxUser=" + foodBoxUser + ", foodboxCartItems=" + cartItems
				+ "]";
	}

	public Set<FoodItem> getFoodboxCartItems() {
		return cartItems;
	}

	public void setFoodboxCartItems(Set<FoodItem> foodboxCartItems) {
		this.cartItems = foodboxCartItems;
	}
	
	

}
