package com.revature.services;

import java.util.List;

import com.revature.dao.*;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.exceptions.InsertionFailureException;
import com.revature.models.Flavor;

public class FlavorService {

	private FlavorDao fp;
	
	public FlavorService(){
		fp = new FlavorPostgres();
	}//end constuctor
	
	//used for testing
	public FlavorService(FlavorDao fp){
		this.fp = fp;
	}
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
	
	public List<Flavor> getFlavorByBrandId(int brand_id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByBrandId(brand_id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(brand_id + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByBrandId
	
	public boolean createFlavor(Flavor flavor) throws InsertionFailureException{
		
		int genInt = fp.createFlavor(flavor);
		
		if(genInt == -1) {
			throw new InsertionFailureException("Unable to create flavor");
		}
		
		return true;
	}//end createFlavor
		
	public boolean updateFlavorPrice(Flavor flavor) throws FlavorNotFoundException{
		
		boolean update = fp.updateFlavorPrice(flavor);
		
		if(update == false) {
			throw new FlavorNotFoundException("Could not update price for id: " + flavor.getId());
		}
		
		return true;
	}//end updateFlavorPrice
	
	public boolean deleteFlavorById(int id) throws FlavorNotFoundException{
		
		boolean delete = fp.deleteFlavorById(id);
		
		if(delete == false){
			throw new FlavorNotFoundException("Invalid id: " + id);
		}
		
		return true;
	}// end deleteFlavorById
	
	public List<Flavor> getFlavorByName(String name) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByName(name);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(name + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByName

	public List<Flavor> getFlavorByOunces(int ounces) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByOunces(ounces);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(ounces + " was not found in the database");
		}
		
		return flavorList;
	}//end getFlavorByOunces
	
	public List<Flavor> getFlavorByPrice(float price) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByPrice(price);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(price + " was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByNameAndOunces(String name, int ounces) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndOunces(name, ounces);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByNameAndPrice(String name, float price) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndPrice(name, price);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByOuncesAndPrice(int ounces, float price) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByOuncesAndPrice(ounces, price);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
	public List<Flavor> getFlavorByNameOuncesAndPrice(String name, int ounces, float price) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameOuncesAndPrice(name, ounces, price);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
	public List<Flavor> getFlavorByNameWithBrandId(String name, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameWithBrandId(name, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(name + " with id " + id +" was not found in the database");
		}
		
		return flavorList;
	}//end 
	
	public List<Flavor> getFlavorByOuncesWithBrandId(int ounces, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByOuncesWithBrandId(ounces, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException(ounces + " with id " + id +" was not found in the database");
		}
		
		return flavorList;
	}//end
	
	public List<Flavor> getFlavorByPriceWithBrandId(float price, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByPriceWithBrandId(price, id);
		
		if(flavorList == null) {
			throw new FlavorNotFoundException(price + " was not found in the database");
		}
		
		return flavorList;
	}//end
	
	public List<Flavor> getFlavorByNameAndOuncesWithBrandId(String name, int ounces, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndOuncesWithBrandId(name, ounces, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end
	
	public List<Flavor> getFlavorByNameAndPriceWithBrandId(String name, float price, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameAndPriceWithBrandId(name, price, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
	}//end
	
	public List<Flavor> getFlavorByOuncesAndPriceWithBrandId(int ounces, float price, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByOuncesAndPriceWithBrandId(ounces, price, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
	public List<Flavor> getFlavorByNameOuncesAndPriceWithBrandId(String name, int ounces, float price, int id) throws FlavorNotFoundException{
		
		List<Flavor> flavorList = fp.getFlavorByNameOuncesAndPriceWithBrandId(name, ounces, price, id);
		
		if(flavorList == null || flavorList.isEmpty()) {
			throw new FlavorNotFoundException("flavors was not found in the database");
		}
		
		return flavorList;
		
	}//end
	
}//end FlavorService
