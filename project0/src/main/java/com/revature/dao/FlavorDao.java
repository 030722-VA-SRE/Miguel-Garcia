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
	
}//end FlavorDao
