package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.JWTUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private AuthService as;
	private JWTUtil jwt;
	@Autowired
	public AuthController(AuthService as, JWTUtil jwt) {
		super();
		this.as = as;
		this.jwt = jwt;
	}//end

	@PostMapping
	public ResponseEntity<UserDTO> login(@RequestParam("username") String username, @RequestParam("password") String password){
		
		UserDTO principle = as.login(username, password);
		
		if(principle == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}//end
		
		//generate token
		HttpHeaders hh = new HttpHeaders();
		
		String token = jwt.generateToken(principle);
		hh.set("Authorization", token);
		
		return new ResponseEntity<>(principle, HttpStatus.ACCEPTED);
	}//end
	
	
	
}
