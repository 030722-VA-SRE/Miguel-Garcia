package com.revature;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.controllers.BrandController;
import com.revature.controllers.FlavorController;
import com.revature.dao.BrandPostgres;
import com.revature.dao.FlavorDao;
import com.revature.dao.FlavorPostgres;
import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.util.ConnectionUtil;

import static io.javalin.apibuilder.ApiBuilder.*;
import io.javalin.*;

public class Driver {

	public static void main(String[] args) {
				
		Javalin app = Javalin.create((config) ->{
			//pass any configuration associated with Javalin
			config.defaultContentType = "application/json";
		});
		
		app.start(8080);
		
		//route() creates a temporary static instance of Javalin
		app.routes(() -> {
			
			//handles url starting with brands
			path("brands", () -> {
				get(BrandController::getBrand);
				post(BrandController::createBrand);
				
				//looking up brand by id
				path("{id}", () -> {
					
					get(BrandController::getBrandById);
					delete(BrandController::deleteBrand);
					put(BrandController::updateBrand);
					
				});//end path id
				
				//looking up flavors of the brand
				path("{id}/flavors", () -> {
					
					get(FlavorController::getFlavorByBrandId);
					post(FlavorController:: createFlavorAtBrand);
					
					path("{flavorId}", () -> {
						
						get(FlavorController:: getFlavorById);
						put(FlavorController:: updateFlavorPrice);
						delete(FlavorController:: deleteFlavorById);
						
					});
					
				});
								
			});//end path "brand"
			
			//look up flavors
			path("flavors", () ->{
				get(FlavorController::getFlavor);
				post(FlavorController::createFlavor);
				
				path("{flavorId}", () -> {
					
					get(FlavorController:: getFlavorById);
					put(FlavorController:: updateFlavorPrice);
					delete(FlavorController:: deleteFlavorById);
					
				});
				
			});//end path flavors
			
		});//end routes


	}//end main

	
}//end Driver