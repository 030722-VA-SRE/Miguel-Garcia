package com.revature.dao;

import java.util.List;

import com.revature.models.Flavor;

public interface FlavorDao {
	
	public List<Flavor> getAllFlavors();
	public Flavor getFlavorById(int id);
	public List<Flavor> getFlavorByBrandId(int brandId);
	public int createFlavor(Flavor flavor);
	public boolean updateFlavorPrice(Flavor flavor);
	public boolean deleteFlavorById(int id);
	public List<Flavor> getFlavorByName(String name);
	public List<Flavor> getFlavorByOunces(int ounces);
	public List<Flavor> getFlavorByPrice(float price);
	public List<Flavor> getFlavorByNameAndOunces(String name, int ounces);
	public List<Flavor> getFlavorByNameAndPrice(String name, float price);
	public List<Flavor> getFlavorByOuncesAndPrice(int ounces, float price);
	public List<Flavor> getFlavorByNameOuncesAndPrice(String name, int ounces, float price);
	
}//end FlavorDao
