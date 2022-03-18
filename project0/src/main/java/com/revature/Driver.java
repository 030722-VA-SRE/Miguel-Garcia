package com.revature;


import java.sql.Connection;
import java.sql.SQLException;

import org.eclipse.jetty.http.HttpStatus;

import com.revature.dao.BrandPostgres;
import com.revature.models.Brand;
import com.revature.util.ConnectionUtil;

import io.javalin.Javalin;

public class Driver {

	public static void main(String[] args) {
		
		BrandPostgres bp = new BrandPostgres();
		Javalin app = Javalin.create((config) ->{
			//pass any configuration associated with Javalin
		});
		
		app.start();
		
		//displays all of the brands
		app.get("brand", ctx -> {
			
			ctx.json(bp.getAllBands());
			
		});
		
		//add a brand
		app.post("brand", ctx -> {
			
			bp.createBrand(ctx.bodyAsClass(Brand.class));
			ctx.status(HttpStatus.CREATED_201);
		});

	}//end main
	
}//end Driver