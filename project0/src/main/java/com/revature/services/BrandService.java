package com.revature.services;

import java.util.List;

import com.revature.dao.BrandPostgres;
import com.revature.exceptions.*;
import com.revature.models.Brand;

public class BrandService {
	
	BrandPostgres bp;
	
	public BrandService(){
		bp = new BrandPostgres();
		
	}//end BrandService
	
	public List<Brand> getAllBrands(){
		
		return bp.getAllBands();
		
	}//end getAllBrands
	
	public Brand getBrandById(int id) throws BrandNotFoundException{
		
		Brand brand = bp.getBandById(id);
		
		if(brand == null) {
			throw new BrandNotFoundException("Invalid brand id: " + id);
		}
		
		return brand;
	}//end getBrandById
	
	public boolean createBrand(Brand brand) throws DatabaseException{
		
		int genInt = bp.createBrand(brand);
		
		if(genInt == -1){
			
			throw new DatabaseException("Could not create brand");
			
		}//end if
		
		return true;
	}//end createBrand
	
	public boolean updateBrand(Brand brand) throws DatabaseException{
		
		boolean update = bp.updateBrand(brand);
		
		if(update == false) {
			
			throw new DatabaseException("Could not update brand: " + brand.getBrand());
		}
		
		return update;
	}
	
	public boolean deleteBrandById(int id) throws DatabaseException{
		
		boolean delete = bp.deleteBrandById(id);
		
		if(delete == false) {
			
			throw new DatabaseException("Invalid id: " + id);
		}
		
		return delete;
	}
}//BrandService
