package com.revature.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BrandAlreadyExistException;
import com.revature.exceptions.BrandNotFoundException;
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
	public List<Brand> getAllBrands(){
		return br.findAll();
	}//end
	
	//create and adds brand to db
	@Transactional
	public Brand createBrand(Brand newBrand){
		
		Brand b = br.findBrandByName(newBrand.getName());
		
		if(b != null){
			throw new BrandAlreadyExistException(b.getName() + " already exist");
		}//end if
		
		//save returns the saved entity
		return br.save(newBrand);
	}//end
	
	public Brand getBrandById(int id){
		
		
		return br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		
	}//end
	
	@Transactional
	public Brand updateBrand(int id, Brand brand) {
		//Can use the save method to update brand
		
		Brand b = br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		brand.setId(b.getId());
		
		return br.save(brand); 
	}//end
	
	
	@Transactional
	public boolean deleteBrandById(int id){
		
		boolean brandDeleted = true;
		
		br.findById(id).orElseThrow(() -> new BrandNotFoundException("No brand of id: " + id));
		br.deleteById(id);
		
		return brandDeleted;
	}
}//end 
