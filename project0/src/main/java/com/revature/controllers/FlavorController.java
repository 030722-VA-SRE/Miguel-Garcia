package com.revature.controllers;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.exceptions.DatabaseException;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Flavor;
import com.revature.services.FlavorService;

import io.javalin.http.Context;

public class FlavorController {

	private static FlavorService fs = new FlavorService();
	
	public static void createFlavor(Context ctx) {
		
		Flavor newFlavor = ctx.bodyAsClass(Flavor.class);
		
		try {
			
			fs.createFlavor(newFlavor);
			ctx.status(HttpStatus.CREATED_201);
			
		}catch(DatabaseException e) {
			
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("Unable to create flavor");
			
		}//end try catch
		
	}//end createFlavor
	
	public static void getFlavor(Context ctx){
		
		ctx.json(fs.getAllFlavors());
		
	}//end getFlavors
	
	public static void getFlavorById(Context ctx){
		
		String pathParamId = ctx.pathParam("flavorId");
		int flavorId = Integer.parseInt(pathParamId);
		
		Flavor f;
		
		try {
			f = fs.getFlavorById(flavorId);
			ctx.json(f);
			ctx.status(HttpStatus.ACCEPTED_202);
			
		}catch(FlavorNotFoundException e){
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result("Unable to find flavor of id " + flavorId);
			
			//logging
			e.printStackTrace();
		}
		
	}//end getFlavorById
	
	public static void getFlavorByBrandId(Context ctx) {
		
		String pathParam = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParam);
		
		List<Flavor> f;
		
		try {
			
			f = fs.getFlavorByBrandId(brandId);
			ctx.json(f);
			ctx.status(HttpStatus.ACCEPTED_202);
			
		}catch(DatabaseException e) {
			
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result("Unable to find brand of id " + brandId);
			
			//logging
			e.printStackTrace();
		}//end try catch
		
	}//end getFlavorByBrandId
	
	
	public static void updateFlavorPrice(Context ctx) {
		
		String pathParamId = ctx.pathParam("flavorId");
		int flavorId = Integer.parseInt(pathParamId);
		
		Flavor f = ctx.bodyAsClass(Flavor.class);
		
		f.setId(flavorId);
		
		try {
			
			fs.updateFlavorPrice(f);
			ctx.status(HttpStatus.ACCEPTED_202);
			
		}catch(DatabaseException e) {
			
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result("Unable to find brand to update");
			
		}//end catch
		
	}//end updateFlavorPrice
	
	public static void deleteFlavorById(Context ctx) {
		
		String pathParam = ctx.pathParam("flavorId");
		int flavorId = Integer.parseInt(pathParam);
		
		try {
			fs.deleteFlavorById(flavorId);
			ctx.status(HttpStatus.ACCEPTED_202);
			
		}catch(DatabaseException e) {
			ctx.status(HttpStatus.NOT_IMPLEMENTED_501);
			ctx.result("Delete flavor has not been implemented");
		}
		
	}//end deleteFlavorById
	
	
	
}//end FlavorController
