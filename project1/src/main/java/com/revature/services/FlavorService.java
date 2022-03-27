package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exceptions.BrandNotFoundException;
import com.revature.exceptions.FlavorAlreadyExistException;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.repositories.BrandRepository;
import com.revature.repositories.FlavorRepository;

@Service
public class FlavorService {
	
	private FlavorRepository fr;
	private BrandRepository br;
	
	@Autowired
	public FlavorService(FlavorRepository fr, BrandRepository br) {
		super();
		this.fr = fr;
		this.br = br;
	}//end 
	
	@Transactional
	public Flavor createFlavor(Flavor newflavor) {
		
		List<Flavor> fL = getAllFlavors();
		
		for(Flavor f: fL) {
			
			if(f.equals(newflavor)){
				throw new FlavorAlreadyExistException();
				
			}//end if
			
		}//end for
		
		return fr.save(newflavor);
	}//end 
	
	@Transactional
	public Flavor flavorUpdate(int id, Flavor flavor){
		
		Flavor f = fr.findById(id).orElseThrow(FlavorNotFoundException::new);
		
		flavor.setId(f.getId());
		
		return fr.save(flavor);
		
	}//end

	public Flavor getFlavorById(int id){
		return fr.findById(id).orElseThrow(FlavorNotFoundException::new);
	}//end
	
	public List<Flavor> getAllFlavors(){
		return fr.findAll();
	}//end
	
	public List<Flavor> getFlavorsByName(String name){
		
		List<Flavor> flavors = getAllFlavors();
		
		List<Flavor> filteredList = new ArrayList<>();
		
		for(Flavor f: flavors){
			if(f.getName().contains(name)){
				filteredList.add(f);
			}//end if
			
		}//end for
		
		if(filteredList.isEmpty()){
			throw new FlavorNotFoundException();
		}//end
		
		return filteredList;
		
	}//end getFlavorsByName
	
	public List<Flavor> getFlavorByOunces(int ounces){
		List<Flavor> flavors = getAllFlavors();
		
		List<Flavor> filteredList = new ArrayList<>();
		
		for(Flavor f: flavors){
			if(f.getOunces() <= ounces){
				filteredList.add(f);
			}//end if
			
		}//end for
		
		if(filteredList.isEmpty()){
			throw new FlavorNotFoundException();
		}//end
		
		return filteredList;
	}//end 
	
	public List<Flavor> getFlavorByPrice(float price){
		List<Flavor> flavors = getAllFlavors();
		
		List<Flavor> filteredList = new ArrayList<>();
		
		for(Flavor f: flavors){
			if(f.getPrice() <= price){
				filteredList.add(f);
			}//end if
			
		}//end for
		
		if(filteredList.isEmpty()){
			throw new FlavorNotFoundException();
		}//end
		
		return filteredList;
	}//end
	
	public List<Flavor> getFlavorByBrand(int id){
		
		Brand brand = br.findById(id).orElseThrow(BrandNotFoundException::new);
		
		return fr.findFlavorByBrand(brand);
		
	}//end
	
	@Transactional
	public void deleteFlavor(int id){
		
		fr.findById(id).orElseThrow(FlavorNotFoundException::new);
		fr.deleteById(id);
		
	}//end
	
}//end
