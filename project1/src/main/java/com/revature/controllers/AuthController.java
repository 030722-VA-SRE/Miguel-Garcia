package com.revature.controllers;

import org.jboss.logging.MDC;
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

	@Autowired
	public AuthController(AuthService as, JWTUtil jwt) {
		super();
		this.as = as;
	}//end

	@PostMapping
	public ResponseEntity<String> login(@RequestParam("username") String username, @RequestParam("password") String password){
		
		UserDTO principle = as.login(username, password);
		
		if(principle == null){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}//end
		
		//generate token
		HttpHeaders hh = new HttpHeaders();
		
		String token = as.generateToken(principle);
		
		MDC.put("userAuth", token);
	
		hh.set("Authorization", token);
		
		return new ResponseEntity<>("Login successful\nAuth token: " + token, HttpStatus.ACCEPTED);
	}//end
	
	
	
	
}
