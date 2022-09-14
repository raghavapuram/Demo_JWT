package com.demo.doman.car.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.doman.car.dto.LoginResponse;
import com.demo.doman.car.dto.UserRequest;
import com.demo.doman.car.entity.User;
import com.demo.doman.car.service.UserService;
import com.demo.doman.car.util.JWTUtility;
/**
 * The User Controller for User API requests
 * @author pavan
 *
 */

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private JWTUtility jwtUtility;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/save")
	public User saveUser(@RequestBody User user) {

		return userService.saveUser(user);

	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@RequestBody UserRequest userRequest) {
		
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getName(), userRequest.getPass()));
		String token = jwtUtility.generateToken(userRequest);

		return ResponseEntity.ok(new LoginResponse(token, "Success!"));

	}
	

	
	
	
		
	
}