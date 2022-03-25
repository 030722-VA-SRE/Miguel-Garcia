package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Brand;
import com.revature.services.BrandService;


//HTTp requests are handled by a controller
//@RestController allows to handle all REST APis such as GET, POST, Delete, PUT requests
@RestController
//@RequestMapping is used to map web requests to Spring Controller methods
@RequestMapping("/brands")
public class BrandController {
	
	private BrandService bs;
	
	@Autowired
	public BrandController(BrandService bs) {
		super();
		this.bs = bs;
	}//
	
	/*
	 * There is no difference in semantic between @GetMapping and @RequestMapping
	 * @GetMappin is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET)
	 */
	
	@GetMapping
	public ResponseEntity<List<Brand>> getAllBrands(){
		/*
		 * ResponseEntity represents the whole HTTP response: status code, headers, and body
		 */
		return new ResponseEntity<>(bs.getAllBrands(), HttpStatus.OK);
	}//end 
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody Brand brand) {
		Brand b = bs.createBrand(brand);
		return new ResponseEntity<>("Brand " + b.getName() + " has been created.", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Brand> getBrandById (@PathVariable("id") int id){
		
		return new ResponseEntity<>(bs.getBrandById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Brand> updateUser(@RequestBody Brand brand, @PathVariable("id") int id) {
		return new ResponseEntity<>(bs.updateBrand(id, brand), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> DeleteById(@PathVariable("id") int id) {
		bs.deleteBrandById(id);
		return new ResponseEntity<>("Brand was deleted", HttpStatus.OK);
	}
	
	
}//end class
