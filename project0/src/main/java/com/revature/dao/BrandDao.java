package com.revature.dao;

import java.util.List;

import com.revature.models.Brand;

public interface BrandDao {

	public int createBrand(Brand brand);
	public List<Brand> getAllBrands();
	public Brand getBandById(int id);
	public boolean updateBrand(Brand brand);
	public boolean deleteBrandById(int id);
	
}
