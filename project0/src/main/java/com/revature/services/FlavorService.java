package com.revature.services;

import java.util.List;

import com.revature.dao.*;
import com.revature.exceptions.DatabaseException;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Flavor;

public class FlavorService {

	private FlavorDao fp;
	
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
	
	public List<Flavor> getFlavorByBrandId(int brand_id) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByBrandId(brand_id);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException(brand_id + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByBrandId
	
	public boolean createFlavor(Flavor flavor) throws DatabaseException{
		
		int genInt = fp.createFlavor(flavor);
		
		if(genInt == -1) {
			throw new DatabaseException("Unable to create flavor");
		}
		
		return true;
	}//end createFlavor
	
	public boolean updateFlavorPrice(Flavor flavor) throws DatabaseException{
		
		boolean update = fp.updateFlavorPrice(flavor);
		
		if(update == false) {
			throw new DatabaseException("Could not update price for id: " + flavor.getId());
		}
		
		return true;
	}//end updateFlavorPrice
	
	public boolean deleteFlavorById(int id) throws DatabaseException{
		
		boolean delete = fp.deleteFlavorById(id);
		
		if(delete == false){
			throw new DatabaseException("Invalid id: " + id);
		}
		
		return true;
	}// end deleteFlavorById
	
	public List<Flavor> getFlavorByName(String name) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByName(name);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException(name + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByName

	public List<Flavor> getFlavorByOunces(int ounces) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByOunces(ounces);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException(ounces + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByOunces
	
	public List<Flavor> getFlavorByPrice(float price) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByPrice(price);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException(price + " was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByNameAndOunces(String name, int ounces) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndOunces(name, ounces);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByNameAndPrice(String name, float price) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndPrice(name, price);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByOuncesAndPrice(int ounces, float price) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByOuncesAndPrice(ounces, price);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
	public List<Flavor> getFlavorByNameOuncesAndPrice(String name, int ounces, float price) throws DatabaseException{
		
		List<Flavor> flavorList = fp.getFlavorByNameOuncesAndPrice(name, ounces, price);
		
		if(flavorList.isEmpty()) {
			throw new DatabaseException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
	
}//end FlavorService
