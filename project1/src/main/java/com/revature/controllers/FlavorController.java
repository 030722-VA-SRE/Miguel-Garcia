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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.Flavor;
import com.revature.services.AuthService;
import com.revature.services.FlavorService;

@RestController
@RequestMapping("/flavors")
public class FlavorController {
	
	private FlavorService fs;
	private AuthService as;
	@Autowired
	public FlavorController(FlavorService fs, AuthService as) {
		super();
		this.fs = fs;
		this.as = as;
	}//end
	
	@GetMapping
	public ResponseEntity<List<Flavor>> getAllFlavors(@RequestParam(required = false) String flavor, @RequestParam(required = false) Integer ounces, 
			@RequestParam(required = false) Float price, @RequestParam(required = false) Integer brandId){
		if(flavor != null || ounces != null || price !=null || brandId != null) {
			
			return new ResponseEntity<>(fs.getFlavorsWithQueryParams(flavor, ounces, price, brandId), HttpStatus.OK);
		}
		else {
			
			return new ResponseEntity<>(fs.getAllFlavors(), HttpStatus.OK);
			
		}//end
		
	}//end
	
	@PostMapping
	public ResponseEntity<String> createFlavor(@RequestBody Flavor flavor, @RequestHeader(value = "Authorization", required = false) String token) {
		as.verify(token, 0);
		Flavor f = fs.createFlavor(flavor);
		return new ResponseEntity<>("Brand " + f.getName() + " has been created.", HttpStatus.CREATED);
	}//end
	
	@GetMapping("/{id}")
	public ResponseEntity<Flavor> getFlavorById(@PathVariable("id") int id){
		return new ResponseEntity<>(fs.getFlavorById(id), HttpStatus.OK);
	}//end
	
	@PutMapping("/{id}")
	public ResponseEntity<Flavor> updateFlavor(@RequestBody Flavor flavor, @PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token){
		as.verify(token, 0);
		return new ResponseEntity<>(fs.flavorUpdate(id, flavor), HttpStatus.ACCEPTED);
	}//end
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteFlavor(@PathVariable("id") int id, @RequestHeader(value = "Authorization", required = false) String token){
		as.verify(token, 0);
		fs.deleteFlavor(id);
		return new ResponseEntity<>("Flavor was deleted", HttpStatus.OK);
	}//end
	
	
}//end
