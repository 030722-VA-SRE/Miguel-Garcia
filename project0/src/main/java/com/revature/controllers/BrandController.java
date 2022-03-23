package com.revature.controllers;

import org.eclipse.jetty.http.HttpStatus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.BrandNotFoundException;
import com.revature.exceptions.InsertionFailureException;
import com.revature.models.Brand;
import com.revature.services.BrandService;

import io.javalin.http.Context;

public class BrandController {

	private static BrandService bs = new BrandService();
	private static Logger log = LogManager.getRootLogger();
	
	public static void createBrand(Context ctx) {
		
		Brand newBrand = ctx.bodyAsClass(Brand.class);
		
		try {
			bs.createBrand(newBrand);
			ctx.status(HttpStatus.CREATED_201);
			ctx.result("Added brand to the database: " + newBrand.getBrand());
			log.info("Added brand to the database" + newBrand.getBrand());
			
		}catch(InsertionFailureException e){
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("Unable to create brand " + newBrand.getBrand());
			log.error("Exception thrown when trying to create a brand: " + newBrand.getBrand());
		}//end try catch
		
	}//end CreateBrand
	
	public static void getBrand(Context ctx) {
		
		ctx.json(bs.getAllBrands());
		
	}//end getBrand
	
	public static void getBrandById(Context ctx) {
		
		String pathParamId = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParamId);
		
		Brand b;
		
		try {
			b = bs.getBrandById(brandId);
			ctx.json(b);
			ctx.status(HttpStatus.ACCEPTED_202);
			
		}catch(BrandNotFoundException e){
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result("Unable to find brand of id " + brandId);
			
			//logging
			//e.printStackTrace();
		}//end try catch
		
	}//end getBranchById
	
	public static void updateBrand(Context ctx){
		
		String pathParamId = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParamId);
		
		Brand b = ctx.bodyAsClass(Brand.class);
		
		b.setId(brandId);
		
		try {
			bs.updateBrand(b);
			ctx.status(HttpStatus.ACCEPTED_202);
			ctx.result("Updated brand to " + b.getBrand());
			log.info("Updated brand in database" + b.getBrand());
			
		}catch(BrandNotFoundException e) {
			ctx.status(HttpStatus.NOT_FOUND_404);
			ctx.result(e.getMessage());
			log.error("Exception thrown when trying to update brand");
			
		}//end try catch
		
	}//end updateBrand
	
	public static void deleteBrand(Context ctx){
		
		String pathParamId = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParamId);
		
		try {
			
			bs.deleteBrandById(brandId);
			ctx.status(HttpStatus.ACCEPTED_202);
			ctx.result("Remove brand: " + brandId);
			log.info("Remove brand at id:" + brandId);
		}catch(BrandNotFoundException e) {
			ctx.status(HttpStatus.NOT_IMPLEMENTED_501);
			ctx.result("delete task has not been implemented yet.");
			log.error("Exception was thrown" + e.getMessage());
		}
		
	}//end of deleteBrand
	
}//end BrandController
