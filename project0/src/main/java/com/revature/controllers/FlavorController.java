package com.revature.controllers;

import java.util.List;

import org.eclipse.jetty.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.FlavorNotFoundException;
import com.revature.exceptions.InsertionFailureException;
import com.revature.models.Flavor;
import com.revature.services.FlavorService;

import io.javalin.http.Context;

public class FlavorController {

	private static FlavorService fs = new FlavorService();
	private static Logger log = LogManager.getRootLogger();
	
	public static void createFlavor(Context ctx) {
		
		Flavor newFlavor = ctx.bodyAsClass(Flavor.class);
		
		try {
			
			fs.createFlavor(newFlavor);
			ctx.status(HttpStatus.CREATED_201);
			ctx.result("New flavor added to the database: " + newFlavor.getFlavor());
			log.info("Added flavor to the database:" + newFlavor.getFlavor());
		}catch(InsertionFailureException e) {
			
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("Unable to create flavor");
			log.error("Exception was thrown when trying to create flavor" + newFlavor.getFlavor());
		}//end try catch
		
	}//end createFlavor
	
	public static void createFlavorAtBrand(Context ctx){
		
		Flavor newFlavor = ctx.bodyAsClass(Flavor.class);
		
		String pathParamId = ctx.pathParam("id");
		int id = Integer.parseInt(pathParamId);
		newFlavor.getBrand().setId(id);
		
		try {
			
			fs.createFlavor(newFlavor);
			ctx.status(HttpStatus.CREATED_201);
			ctx.result("New flavor added to the database: " + newFlavor.toString());
			log.info("Added flavor to the database:" + newFlavor.toString());
			
		}catch(InsertionFailureException e) {
			
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("Unable to create flavor at brand " + id);
			log.error("Exception was thrown when trying to create flavor" + e.fillInStackTrace());
		}//end try catch
	}//end 
	
	public static void getFlavor(Context ctx){
		
		String name = ctx.queryParam("name");
		String ounces = ctx.queryParam("ounces");
		String price = ctx.queryParam("price");
		String brand = ctx.queryParam("brandId");
		
		List<Flavor> f;
		
		if(name == null && ounces == null && price == null && brand == null) {
			ctx.json(fs.getAllFlavors());
		}
		else if (name != null && ounces == null && price == null && brand == null) {
			
			try {
				f = fs.getFlavorByName(name);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor: " + name);
			}//end try catch
		}//end 
		else if (name == null && ounces != null && price == null && brand == null) {
			try {
				int o = Integer.parseInt(ounces);
				f = fs.getFlavorByOunces(o);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}
			catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find ounces: " +  ounces);
			}//end try catch
		}//end
		else if (name == null && ounces == null && price != null && brand == null) {
			try {
				float p = Float.parseFloat(price);	
				f = fs.getFlavorByPrice(p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find price: " + price);
			}//end try catch
		}//end
		else if (name != null && ounces != null && price == null && brand == null) {
			
			try {
				int o = Integer.parseInt(ounces);
				f = fs.getFlavorByNameAndOunces(name, o);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " ounces: " + ounces);
			}//end try catch
		}//end
		else if (name != null && ounces == null && price != null && brand == null) {
		
			try {
				float p = Float.parseFloat(price);
				f = fs.getFlavorByNameAndPrice(name, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " price: " + price);
			}//end try catch
		}//end
		
		else if (name == null && ounces != null && price != null && brand == null) {
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				f = fs.getFlavorByOuncesAndPrice(o, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor ounces: " + ounces + " price: " + price);
			}//end try catch
		}//end
		else  if (name != null && ounces != null && price != null && brand == null){
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				f = fs.getFlavorByNameOuncesAndPrice(name, o, p);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " ounces: " + ounces + " price: " + price);
			}//end try catch
		}//end
		else  if (name != null && ounces == null && price == null && brand != null) {
			try {
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByNameWithBrandId(name, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " brand: " + brand);
			}//end try catch
		}//end	
		else if (name == null && ounces != null && price == null && brand != null) {
			try {
				int o = Integer.parseInt(ounces);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByOuncesWithBrandId(o, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor ounces: " + ounces + " brand: " + brand);
			}//end try catch
		}//end
		
		else if (name == null && ounces == null && price != null && brand != null) {
			try {
				float p = Float.parseFloat(price);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByPriceWithBrandId(p,b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor price: " + price + " brand: " + brand);
			}//end try catch
		}//end
		
		else if (name != null && ounces != null && price == null && brand != null) {
			try {
				int o = Integer.parseInt(ounces);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByNameAndOuncesWithBrandId(name, o, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " ounces: " + ounces + " brand: " + brand);
			}//end try catch
		}//end
		
		else if (name != null && ounces == null && price != null && brand != null) {
			try {
				float p = Float.parseFloat(price);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByNameAndPriceWithBrandId(name, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when trying to find flavor name: " + name + " ounces: " + ounces + " brand: " + brand);
			}//end try catch
		}//end
		
		else if (name == null && ounces != null && price != null && brand != null) {
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByOuncesAndPriceWithBrandId(o, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception thrown when searching for the ounces, price and brand id");
			}//end try catch
		}//end
		
		else {
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				int b = Integer.parseInt(brand);
				f = fs.getFlavorByNameOuncesAndPriceWithBrandId(name, o, p, b);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error("Exception is thrown when searching with the name, ounces, price and brandid");
			}//end try catch
			
		}//end else
		
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
			ctx.result(e.getMessage());
			
			//logging
			log.error("Exception was thrown when searching for flavor by id: " + flavorId);
		}
		
	}//end getFlavorById
	
	public static void getFlavorByBrandId(Context ctx) {
		
		String pathParam = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParam);
		
		String name = ctx.queryParam("name");
		String ounces = ctx.queryParam("ounces");
		String price = ctx.queryParam("price");
		
		List<Flavor> f;
		
		if(name == null && ounces == null && price == null) {
			try {
				
				f = fs.getFlavorByBrandId(brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
				
			}catch(FlavorNotFoundException e) {
				
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				
				log.error("Exception was thrown when searching for flavors with brandid: " + brandId);
				
			}//end try catch
		}//end
		else  if (name != null && ounces == null && price == null) {
			try {
				f = fs.getFlavorByNameWithBrandId(name, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(String.format("Unable to find &s flavor at brand id: %d", name, brandId));
				String s = String.format("Exception was thrown when searching for %s with brand id %d", name, brandId);
				log.error(s);
			}//end try catch
		}//end	
		else if (name == null && ounces != null && price == null) {
			try {
				int o = Integer.parseInt(ounces);
				f = fs.getFlavorByOuncesWithBrandId(o, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for ounces: %d with brand id %d", ounces, brandId));
			}//end try catch
		}//end
		
		else if (name == null && ounces == null && price != null) {
			try {
				float p = Float.parseFloat(price);
				f = fs.getFlavorByPriceWithBrandId(p,brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for price: %a with brand id %d", price, brandId));
			}//end try catch
		}//end
		
		else if (name != null && ounces != null && price == null) {
			
			try {
				int o = Integer.parseInt(ounces);
				f = fs.getFlavorByNameAndOuncesWithBrandId(name, o, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for %s and ounces: %s with brand id %d", name, ounces, brandId));
			}//end try catch
		}//end
		
		else if (name != null && ounces == null && price != null) {
			try {
				float p = Float.parseFloat(price);
				f = fs.getFlavorByNameAndPriceWithBrandId(name, p, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for %s and price: %s with brand id %d", name, price, brandId));
			}//end try catch
		}//end
		
		else if (name == null && ounces != null && price != null) {
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				f = fs.getFlavorByOuncesAndPriceWithBrandId(o, p, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for price: %s and ounces: %s with brand id %d", price, ounces, brandId));
			}//end try catch
		}//end
		
		else {
			try {
				int o = Integer.parseInt(ounces);
				float p = Float.parseFloat(price);
				f = fs.getFlavorByNameOuncesAndPriceWithBrandId(name, o, p, brandId);
				ctx.json(f);
				ctx.status(HttpStatus.ACCEPTED_202);
			}catch(NumberFormatException e){
				ctx.result("Invalid value: " + e.getMessage());
				log.error(e.getMessage());
			}catch(FlavorNotFoundException e ) {
				ctx.status(HttpStatus.NOT_FOUND_404);
				ctx.result(e.getMessage());
				log.error(String.format("Exception was thrown when searching for %s, price: %s and ounces: %s with brand id %d", name, price, ounces, brandId));
			}//end try catch
			
		}//end else
		
	}//end getFlavorByBrandId
	
	
	public static void updateFlavorPrice(Context ctx) {
		
		String pathParamId = ctx.pathParam("flavorId");
		int flavorId = Integer.parseInt(pathParamId);
		
		Flavor f = ctx.bodyAsClass(Flavor.class);
		
		f.setId(flavorId);
		
		try {
			
			fs.updateFlavorPrice(f);
			ctx.status(HttpStatus.ACCEPTED_202);
			ctx.result("Updated price at flavor id" + f.getId() + " to " + f.getPrice());
			log.info("Updated price at flavor id" + f.getId() + " to " + f.getPrice());
			
		}catch(FlavorNotFoundException e) {
			
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result("Unable to update flavor price");
			log.error("Exception was thrown when trying to update price for id: " + f.getId());
			
		}//end catch
		
	}//end updateFlavorPrice
	
	public static void deleteFlavorById(Context ctx) {
		
		String pathParam = ctx.pathParam("flavorId");
		int flavorId = Integer.parseInt(pathParam);
		
		try {
			fs.deleteFlavorById(flavorId);
			ctx.status(HttpStatus.ACCEPTED_202);
			ctx.result("Deleted flavor at id: " + flavorId);
			log.info("Delete flavor from the database:" + flavorId);
			
		}catch(FlavorNotFoundException e) {
			ctx.status(HttpStatus.NOT_IMPLEMENTED_501);
			ctx.result("Delete flavor has not been implemented");
			log.error("Exception was thrown when trying to delete flavor" + flavorId);
		}
		
	}//end deleteFlavorById
	
	
	
}//end FlavorController
