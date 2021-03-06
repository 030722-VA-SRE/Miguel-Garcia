package com.revature.services;

import java.util.List;

import com.revature.dao.*;
import com.revature.exceptions.*;
import com.revature.models.Brand;

public class BrandService {
	
	private BrandDao bp;
	
	public BrandService(){
		bp = new BrandPostgres();
		
	}//end BrandService
	
	//use for testing
	public BrandService(BrandDao bp) {
		this.bp = bp;
	}//end BrandService
	
	public List<Brand> getAllBrands(){
		
		//if ArrayList size is empty
		
		return bp.getAllBrands();
		
	}//end getAllBrands
	
	public Brand getBrandById(int id) throws BrandNotFoundException{
		
		Brand brand = bp.getBandById(id);
		
		if(brand == null) {
			throw new BrandNotFoundException("Invalid brand id: " + id);
		}
		
		return brand;
	}//end getBrandById
	
	public boolean createBrand(Brand brand) throws InsertionFailureException{
		
		int genInt = bp.createBrand(brand);
		
		if(genInt == -1){
			
			throw new InsertionFailureException("Could not create brand");
			
		}//end if
		
		return true;
	}//end createBrand
	
	public boolean updateBrand(Brand brand) throws BrandNotFoundException{
		
		boolean update = bp.updateBrand(brand);
		
		if(update == false) {
			
			throw new BrandNotFoundException("Could not update brand: " + brand.getBrand());
		}
		
		return update;
	}
	
	public boolean deleteBrandById(int id) throws BrandNotFoundException{
		
		boolean delete = bp.deleteBrandById(id);
		
		if(delete == false) {
			
			throw new BrandNotFoundException("Invalid id: " + id);
		}
		
		return delete;
	}
}//BrandService
