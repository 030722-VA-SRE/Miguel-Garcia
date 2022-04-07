package com.revature.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.AuthorizationException;
import com.revature.models.Role;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

import io.jsonwebtoken.Claims;

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
	
	public String register(User user){
		
		if(ur.findUserByUsername(user.getUsername()) != null) {
			throw new AuthenticationException("Username is not available");
		}
		else if(user.getUsername() == null) {
			throw new AuthenticationException("Username cannot be empty");
		}
		else if(user.getPassword() == null) {
			throw new AuthenticationException("Password cannot be empty");
		}
		else if(user.getUsername().length() < 6) {
			throw new AuthenticationException("Password must be at least 6 characters");
		}//end
		
		user.setRole(Role.USER);
		
		ur.save(user);
		LOG.info("New user was registered");
		
		return generateToken(new UserDTO(user));
		
	}//end
	
	public String generateToken(UserDTO principal) {
		
		return jwt.generateToken(principal);
	}
	
	public Claims verify(String token) {
		
		if(token == null) {
			LOG.warn("Not authorized");
			throw new AuthenticationException("Not authorized");
		}
		
		return jwt.extractAllClaims(token);
		
	}//end
	
		

}
