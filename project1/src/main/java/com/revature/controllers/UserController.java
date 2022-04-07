package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dtos.UserDTO;
import com.revature.models.User;
import com.revature.services.AuthService;
import com.revature.services.UserService;

import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService us;
	private AuthService as;
	private static Logger LOG =LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public UserController(UserService us, AuthService as) {
		super();
		this.us = us;
		this.as = as;
	}//end
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> getAllUsers(@RequestHeader(value = "Authorization", required = false) String token){

		/*if(token == null) {
			LOG.warn("Not authorized");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}//end
		*/
		Claims claims = as.verify(token);		
		if(!claims.get("role").equals("ADMIN")){
			LOG.warn(claims.getSubject() + " is not authorizd for get get:/users");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		LOG.info("Users retrieved");
		return new ResponseEntity<>(us.getAllUsers(), HttpStatus.OK);
	}//end 
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody User user, @RequestHeader(value = "Authorization", required = false) String token) {
		
		/*if(token == null) {
			LOG.warn("Not authorized");
			return new ResponseEntity<>("Needs authorization to create a new user", HttpStatus.FORBIDDEN);
		}//end
		*/
		Claims claims = as.verify(token);
		if(!claims.get("role").equals("ADMIN")){
			LOG.warn(claims.getSubject() + " is not authorizd to create a new user");
			return new ResponseEntity<>(claims.getSubject() + " is not authorizd to create a new user", HttpStatus.FORBIDDEN);
		}//end
		
		
		User u = us.createUser(user);
		LOG.info(claims.getSubject() + " create a new user");
		return new ResponseEntity<>("User " + u.getUsername() + " has been created.", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){

		Claims claim = as.verify(token);
		if(claim.get("id").equals(id) || claim.get("role").equals("ADMIN")) {
			
			return new ResponseEntity<>(us.getUserById(id), HttpStatus.OK);
			
		}else {
			LOG.warn(claim.getSubject() + " does not have the authorization to view users/" + id.intValue());
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}//end 
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){
				
		
		Claims claim = as.verify(token);
		if(claim.get("id").equals(id) || claim.get("role").equals("ADMIN")) {
			
			LOG.info("User was updated: {}", user.getUsername());
			return new ResponseEntity(us.updateUser(id, user), HttpStatus.OK);
			
		}else {
			LOG.warn(claim.getSubject() + " does not have the authorization to update users/" + id.intValue());
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id, @RequestHeader(value = "Authorization", required = false) String token){
		
		Claims claim = as.verify(token);

		if(claim.get("id").equals(id) || claim.get("role").equals("ADMIN")) {
			
			us.deleteUser(id);
			LOG.info("Deleted user at id: " + id.intValue());
			return new ResponseEntity<>("User was deleted", HttpStatus.OK);
			
		}else {
			LOG.warn(claim.getSubject() + " does not have the authorization to delete users/" + id.intValue());
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

	}
	
}//end
