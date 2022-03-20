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
		
		String name = ctx.queryParam("name");
		String ounces = ctx.queryParam("ounces");
		String price = ctx.queryParam("price");
		String brand = ctx.queryParam("brand");
		
		List<Flavor> f;
		
		if(name == null && ounces == null && price == null && brand == null) {
			ctx.json(fs.getAllFlavors());
		}
		else if (name != null && ounces == null && price == null && brand == null) {
			
			try {
				f = fs.getFlavorByName(name);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavor with name: " + name);
			}//end try catch
		}//end 
		else if (name == null && ounces != null && price == null && brand == null) {
			int o = Integer.parseInt(ounces);
			try {
				f = fs.getFlavorByOunces(o);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavor with ounce: " + name);
			}//end try catch
		}//end
		else if (name == null && ounces == null && price != null && brand == null) {
			float p = Float.parseFloat(price);
			try {
				f = fs.getFlavorByPrice(p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavor with price: " + name);
			}//end try catch
		}//end
		else if (name != null && ounces != null && price == null && brand == null) {
			int o = Integer.parseInt(ounces);
			
			try {
				f = fs.getFlavorByNameAndOunces(name, o);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		else if (name != null && ounces == null && price != null && brand == null) {
			float p = Float.parseFloat(price);
			try {
				f = fs.getFlavorByNameAndPrice(name, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		
		else if (name == null && ounces != null && price != null && brand == null) {
			int o = Integer.parseInt(ounces);
			float p = Float.parseFloat(price);
			try {
				f = fs.getFlavorByOuncesAndPrice(o, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		else  if (name != null && ounces != null && price != null && brand == null){
			int o = Integer.parseInt(ounces);
			float p = Float.parseFloat(price);
			try {
				f = fs.getFlavorByNameOuncesAndPrice(name, o, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		else  if (name != null && ounces == null && price == null && brand != null) {
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByNameWithBrandId(name, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end	
		else if (name == null && ounces != null && price == null && brand != null) {
			int o = Integer.parseInt(ounces);
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByOuncesWithBrandId(o, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavor with ounce: " + name);
			}//end try catch
		}//end
		
		else if (name == null && ounces == null && price != null && brand != null) {
			float p = Float.parseFloat(price);
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByPriceWithBrandId(p,b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavor with price: " + name);
			}//end try catch
		}//end
		
		else if (name != null && ounces != null && price == null && brand != null) {
			int o = Integer.parseInt(ounces);
			int b = Integer.parseInt(brand);
			
			try {
				f = fs.getFlavorByNameAndOuncesWithBrandId(name, o, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		
		else if (name != null && ounces == null && price != null && brand != null) {
			float p = Float.parseFloat(price);
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByNameAndPriceWithBrandId(name, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		
		else if (name == null && ounces != null && price != null && brand != null) {
			int o = Integer.parseInt(ounces);
			float p = Float.parseFloat(price);
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByOuncesAndPriceWithBrandId(o, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}//end
		
		else {
			int o = Integer.parseInt(ounces);
			float p = Float.parseFloat(price);
			int b = Integer.parseInt(brand);
			try {
				f = fs.getFlavorByNameOuncesAndPriceWithBrandId(name, o, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(DatabaseException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result("Unable to find flavors");
			}//end try catch
		}
		
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
			ctx.result("Unable to find flavor of id: " + flavorId);
			
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
			ctx.result("Unable to find brand of id: " + brandId);
			
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
			ctx.result("Unable to find flavor to update");
			
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
