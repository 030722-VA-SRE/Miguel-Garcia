package com.revature.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.services.AuthService;
import com.revature.services.BrandService;
import com.revature.services.FlavorService;


//HTTp requests are handled by a controller
//@RestController allows to handle all REST APis such as GET, POST, Delete, PUT requests
@RestController
//@RequestMapping is used to map web requests to Spring Controller methods
@RequestMapping("/brands")
public class BrandController {
	
	private BrandService bs;
	private FlavorService fs;
	private AuthService as;
	private static Logger LOG =LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	public BrandController(BrandService bs, FlavorService fs, AuthService as) {
		super();
		this.bs = bs;
		this.fs = fs;
		this.as = as;
	}//
	
	/*
	 * There is no difference in semantic between @GetMapping and @RequestMapping
	 * @GetMappin is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET)
	 */
	
	@GetMapping
	public ResponseEntity<List<Brand>> getAllBrands(){

		return new ResponseEntity<>(bs.getAllBrands(), HttpStatus.OK);
	}//end 
	
	@PostMapping
	public ResponseEntity<String> createBrand(@RequestBody Brand brand, @RequestHeader(value = "Authorization", required = false) String token) {
		
		as.verify(token, 0);
		
		Brand b = bs.createBrand(brand);
		LOG.info("Brand created: {}", b.getName());
		return new ResponseEntity<>("Brand " + b.getName() + " has been created.", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrandById (@PathVariable("id") int id){
		
		return new ResponseEntity<>(bs.getBrandById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand, @PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token) {
		
		as.verify(token, 0);
		
		LOG.info("Updated brand at id: {}", id);
		
		return new ResponseEntity<>(bs.updateBrand(id, brand), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token) {
		
		as.verify(token, 0);
		
		bs.deleteBrandById(id);
		LOG.info("Deleted brand at id: {}", id);
		return new ResponseEntity<>("Brand was deleted", HttpStatus.OK);
	}
	
	@GetMapping("/{id}/flavors")
	public ResponseEntity<List<Flavor>> findFlavorsByBrand(@PathVariable("id") Integer id, @RequestParam(required = false) String flavor, @RequestParam(required = false) Integer ounces, 
			@RequestParam(required = false) Float price){
		return new ResponseEntity<>(fs.getFlavorsWithQueryParams(flavor, ounces, price, id), HttpStatus.OK);
	}//end
	
	
	@PostMapping("/{id}/flavors")
	public ResponseEntity<String> createFlavorWithBrandId(@RequestBody Flavor flavor, @PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token) {
		Flavor f = fs.createFlavorWithBrandId(flavor, id);
		
		as.verify(token, 0);
		return new ResponseEntity<>("Brand " + f.getName() + " has been created.", HttpStatus.CREATED);
	}//end
	
	@GetMapping("/{brand}/flavors/{id}")
	public ResponseEntity<Flavor> getFlavorById(@PathVariable("id") int id){
		return new ResponseEntity<>(fs.getFlavorById(id), HttpStatus.OK);
	}//end
	
	@PutMapping("/{brand}/flavors/{id}")
	public ResponseEntity<Flavor> updateFlavor(@RequestBody Flavor flavor, @PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token){
		as.verify(token, 0);
		
		return new ResponseEntity<>(fs.flavorUpdate(id, flavor), HttpStatus.ACCEPTED);
	}//end
	
	@DeleteMapping("/{brand}/flavors/{id}")
	public ResponseEntity<String> deleteFlavor(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token){
		as.verify(token, 0);
		
		fs.deleteFlavor(id);
		return new ResponseEntity<>("Flavor was deleted", HttpStatus.OK);
	}//end
	
}//end class
