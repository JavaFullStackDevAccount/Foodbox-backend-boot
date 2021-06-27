package com.foodbox.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodbox.entities.FoodboxUser;

@Service
public class FoodboxUserDetailsService implements UserDetailsService {

	
	
	@Autowired
	private FoodBoxUserService foodBoxUserService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		FoodboxUser foodBoxUserFromDatabase = this.foodBoxUserService.getFoodboxUserByEmail(username);
		
		if(foodBoxUserFromDatabase != null )
		
		
			return new User(foodBoxUserFromDatabase.getEmail(),foodBoxUserFromDatabase.getPassword(),new ArrayList<>());
		
		else
			
			return new User("","",new ArrayList<>());
	
		
	}

}
