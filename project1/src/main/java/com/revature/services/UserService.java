package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.dtos.UserDTO;
import com.revature.exceptions.UserAlreadyExistException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;

@Service
public class UserService{
	
	private UserRepository ur;
	
	@Autowired
	public UserService(UserRepository ur) {
		this.ur = ur;
	}//end constructor
		
	//@Transactional annotation is the metadata that specifies the semantics of the transactions on a method
	//Look up isolation levels
	@Transactional
	public User createUser(User newUser){
		
		//check if user exits
		User u = ur.findUserByUsername(newUser.getUsername());
		if(u != null) {
			throw new UserAlreadyExistException();
		}//end

		return ur.save(newUser);
	}//end createUser
	
	@Transactional
	public User updateUser(int id, User user){

		User u = ur.findById(id).orElseThrow(UserNotFoundException:: new);
		
		user.setId(u.getId());
		
		return ur.save(user);
	}
	
	public List<User> getAllUsers(){
		return ur.findAll();
	}//end getAll
	
	
	public UserDTO getUserById(int id){
		
		User user = ur.findById(id).orElseThrow(UserNotFoundException::new);
		
		return new UserDTO(user);
	}//end
	
	@Transactional
	public void deleteUser(int id){
		
		ur.findById(id).orElseThrow(UserNotFoundException:: new);
		ur.deleteById(id);
		
	}


}//end UserSerevice
