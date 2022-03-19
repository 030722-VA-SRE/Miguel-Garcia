package com.revature.controllers;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.exceptions.BrandNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.models.Brand;
import com.revature.services.BrandService;

import io.javalin.http.Context;

public class BrandController {

	private static BrandService bs = new BrandService();
	
	public static void createBrand(Context ctx) {
		
		Brand newBrand = ctx.bodyAsClass(Brand.class);
		
		try {
			bs.createBrand(newBrand);
			ctx.status(HttpStatus.CREATED_201);
			
		}catch(DatabaseException e){
			ctx.status(HttpStatus.BAD_REQUEST_400);
			ctx.result("Unable to create brand");
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
			e.printStackTrace();
		}//end try catch
		
	}//end getBranchById
	
	public static void deleteBrand(Context ctx){
		
		String pathParamId = ctx.pathParam("id");
		int brandId = Integer.parseInt(pathParamId);
		
		try {
			
			bs.deleteBrandById(brandId);
			ctx.status(HttpStatus.ACCEPTED_202);
		
		}catch(DatabaseException e) {
			ctx.status(HttpStatus.NOT_IMPLEMENTED_501);
			ctx.result("delete task has not been implemented yet.");
		}
		
	}//end of deleteBrand
	
}//end BrandController
