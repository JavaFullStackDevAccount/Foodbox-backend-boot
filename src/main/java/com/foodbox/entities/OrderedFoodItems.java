package com.foodbox.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class OrderedFoodItems {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
	
	
}
