package com.foodbox.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodbox.entities.FoodboxUser;
import com.foodbox.models.AuthenticationRequestModel;
import com.foodbox.models.AuthenticationResponseModel;
import com.foodbox.services.FoodBoxUserService;
import com.foodbox.services.FoodboxUserDetailsService;
import com.foodbox.utils.JwtUtil;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private FoodboxUserDetailsService userDetailsService;

	@Autowired
	private FoodBoxUserService foodBoxUserService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private FoodBoxUserService foodBoxService;
	
	
	@PostMapping
	public ResponseEntity<?> createAuthenticationToken(
			@RequestBody(required = true) AuthenticationRequestModel authRequest) {

		try {

			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

			final String jwt = jwtUtil.generateToken(userDetails);

			FoodboxUser registeredUser = foodBoxUserService.getFoodboxUserByEmail(authRequest.getUsername());

			return ResponseEntity.ok().body(new AuthenticationResponseModel(jwt, registeredUser.isUserTypeAdmin(),
					registeredUser.getEmail(), registeredUser.getId() + 376));

		} catch (Exception e) {

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

	}
	
	
	@PostMapping("/register")
	public FoodboxUser registerFoodboxUser(@RequestBody(required = true) FoodboxUser foodBoxUserToRegister) {
		
		return this.foodBoxService.addFoodboxUser(foodBoxUserToRegister);
		
	}

}
