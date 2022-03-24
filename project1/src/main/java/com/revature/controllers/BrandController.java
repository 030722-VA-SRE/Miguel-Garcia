package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<List<Brand>> getBrands(){
		/*
		 * ResponseEntity represents the whole HTTP response: status code, headers, and body
		 */
		return new ResponseEntity<>(bs.getBrands(), HttpStatus.OK);
	}//end 
	
	
	
}//end class
