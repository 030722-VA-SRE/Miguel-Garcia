package com.revature.services;

import java.util.List;

import com.revature.dao.FlavorPostgres;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Flavor;

public class FlavorService {

	private FlavorPostgres fp;
	
	public FlavorService(){
		fp = new FlavorPostgres();
	}//end constuctor
	
	public List<Flavor> getAllFlavors(){
		return fp.getAllFlavors();
	}//end getAllFlavors
	
	public Flavor getFlavorById(int id) throws FlavorNotFoundException{
		
		Flavor flavor = fp.getFlavorById(id);
		
		if(flavor == null) {
			throw new FlavorNotFoundException("Invalid flavor id: " + id);
		}//end if
		
		return flavor;
		
	}//end getFlavorById
	
	
}//end FlavorService
