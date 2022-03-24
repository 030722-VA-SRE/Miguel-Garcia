package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.Brand;
import com.revature.repositories.BrandRepository;

//@Service is used with classes that provide some business functionalities
//It is used to mark the class as a service provider
@Service
public class BrandService {
	
	private BrandRepository br;
	
	/*@Autowired annotation indicates that the constructor should be autowired when creating the bean
	 * It is used for dependency injection - providing the objects that an object needs
	 * instead of having it construct them itself
	*/
	@Autowired
	public BrandService(BrandRepository br) {
		super();
		this.br = br;
	}//end
	
	//return all of the brands
	public List<Brand> getBrands(){
		return br.findAll();
	}//end
	
	//create and adds brand to db
	public Brand createBrand(Brand brand){
		//save returns the saved entity
		return br.save(brand);
	}//end
	
	public Brand getBrandById(int id){
		return br.getById(id);
		
	}//end
	
	/*
	public Brand updateBrand(Brand brand) {
		//Can use the save method to update brand
	}
	*/
	
	public void deleteBrandById(int id){
		//Throw IllegalArgumentException if the given id is null
		br.deleteById(id);
	}
}//end 
