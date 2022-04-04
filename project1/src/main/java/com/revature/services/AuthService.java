package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class AuthService {
	
	private UserRepository ur;
	private JWTUtil jwt;
	private static Logger LOG = LoggerFactory.getLogger(AuthService.class);
	
	@Autowired
	public AuthService(UserRepository ur, JWTUtil jwt) {
		super();
		this.ur = ur;
		this.jwt = jwt;
	}
	
	public UserDTO login(String username, String password) {
		// retrieve user from db by username, returns null if does not exist
		User principal = ur.findUserByUsername(username);
		
		// check that the uname and pass sent in req match the ones retrieved from db
		if(principal == null || !password.equals(principal.getPassword())) {
			throw new AuthenticationException("Attempted to login with username: " + username);
		}
		LOG.info("User succesfully logged in: id" + principal.getId()+ " name: "+ principal.getUsername());
		return new UserDTO(principal);
	}//
	
	public String generateToken(UserDTO principal) {
		
		return jwt.generateToken(principal);
	}
	
	public void verify(String token, int id){
		
		if(token == null) {
			throw new AuthorizationException("null token");
		}
		
		User principal = ur.findUserByUsername(jwt.extractUsername(token));
		
		if(principal != null && principal.getId() == id || principal.getRole().toString().equals("ADMIN")) {
			LOG.info("Token verified successfully");
		}//
		else {
			throw new AuthorizationException(principal.getUsername() + " does not have permission");
		}//end
		
	}//end
	
	public boolean verifyAdmin(String token) {
		
		if(token == null) {
			//throw new error
		}//end 
		
		User principal = ur.findUserByUsername(jwt.extractUsername(token));
		
		if(principal == null || !principal.getRole().toString().equals("ADMIN")) {
			//Log error
			return false;
		}
		
		return true;
		
	}//end verify
	
	public boolean verifyUser(String token, int id){
		if(token == null) {
			//throw new error
		}//end 
		
		User principal = ur.findUserByUsername(jwt.extractUsername(token));
		
		if(principal == null || principal.getId() != id) {
			//log
			return false;
		}//end
		
		return true;
	}
	

}
