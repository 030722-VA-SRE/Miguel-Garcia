package com.revature.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	public Flavor createFlavorWithBrandId(Flavor newFlavor, int brandId) {
		
		Brand b = br.findById(brandId).orElseThrow(BrandNotFoundException::new);
		newFlavor.setBrandId(b);
		
		List<Flavor> fL = getAllFlavors();
		for(Flavor f: fL) {
			if(f.equals(newFlavor)){
				throw new FlavorAlreadyExistException();
			}
		}//end for
		
		return fr.save(newFlavor);
		
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
	
	private List<Flavor> getFlavorsByName(String name){
		
		List<Flavor> flavors = getAllFlavors();
		
		List<Flavor> filteredList = new ArrayList<>();
		
		for(Flavor f: flavors){
			if(f.getName().toLowerCase().contains(name.toLowerCase())){
				filteredList.add(f);
			}//end if
			
		}//end for
		
		if(filteredList.isEmpty()){
			throw new FlavorNotFoundException();
		}//end
		
		return filteredList;
		
	}//end getFlavorsByName
	
	private List<Flavor> getFlavorsByOunces(int ounces){
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
	
	private List<Flavor> getFlavorsByPrice(float price){
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
	
	private List<Flavor> getFlavorsByBrand(int id){
		
		Brand brand = br.findById(id).orElseThrow(BrandNotFoundException::new);
		
		return fr.findFlavorByBrand(brand);
		
	}//end
	

	public List<Flavor> getFlavorsWithQueryParams(String name, Integer ounces, Float price, Integer brandId){
		
		List<List<Flavor>> listOfResults = new ArrayList<>();
		List<Flavor> result;
		
		if(name != null){
			
			List<Flavor> nameList = getFlavorsByName(name);
			listOfResults.add(nameList);
			
		}//end
		if(ounces != null){
			
			List<Flavor> ouncesList = getFlavorsByOunces(ounces);
			listOfResults.add(ouncesList);
			
		}//end
		if(price != null){
			
			List<Flavor> priceList = getFlavorsByPrice(price);	
			listOfResults.add(priceList);
			
		}//end
		
		if(brandId != null) {
			
			List<Flavor> brandList = getFlavorsByBrand(brandId);
			listOfResults.add(brandList);
			
		}//end
		
		if(listOfResults.isEmpty()){
			throw new FlavorNotFoundException();
		}
		
		else if (listOfResults.size() == 1) {
			result = listOfResults.get(0);
		}
		else {
			result = listOfResults.get(0);
			for(int i = 1; i < listOfResults.size(); i++){
				result = result.stream().distinct().filter(listOfResults.get(i)::contains).toList();
			}//end
		}
		
		if(result.isEmpty()){
			throw new FlavorNotFoundException();
		}
		
		return result;
		
	}//end
	
	@Transactional
	public void deleteFlavor(int id){
		
		fr.findById(id).orElseThrow(FlavorNotFoundException::new);
		fr.deleteById(id);
		
	}//end
	
}//end
