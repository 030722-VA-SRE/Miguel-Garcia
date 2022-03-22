package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.revature.dao.BrandDao;
import com.revature.dao.BrandPostgres;
import com.revature.exceptions.BrandNotFoundException;
import com.revature.exceptions.DatabaseException;
import com.revature.models.Brand;
import com.revature.services.BrandService;

@RunWith(MockitoJUnitRunner.class)
class BrandServiceTest {
	
	private static BrandDao bMock;
	private static BrandService bService;
	
	@BeforeAll
	public static void setup(){
		bMock = Mockito.mock(BrandDao.class);
		bService = new BrandService(bMock);
	}
	
	@Test
	public void getAllBrandsTest() {
			
		Brand a = new Brand(1, "Cheetos");
		Brand b = new Brand(2, "Fritos");
		Brand c = new Brand(3, "Doritos");
		
		List<Brand> brandList = Arrays.asList(a,b,c);
		
		when(bMock.getAllBrands()).thenReturn(brandList);
		assertEquals(bService.getAllBrands(), brandList);
		
		
		
	}//end test
	
	@Test
	public void getbrandByIdTestExist() throws BrandNotFoundException {
		
		Brand a = new Brand(1, "Cheetos");
				
		when(bMock.getBandById(1)).thenReturn(a);
		assertEquals(bService.getBrandById(1), a);
	}//end test
	
	@Test 
	public void getBrandByIdTestNotExist() {
				
		when(bMock.getBandById(1)).thenReturn(null);
		Assertions.assertThrows(BrandNotFoundException.class, ()-> bService.getBrandById(1));
		
	}//end test
	
	@Test
	public void createBrandTest() throws DatabaseException {
		
		Brand a = new Brand(1, "Doritos");
		
		when(bMock.createBrand(a)).thenReturn(1);
		assertEquals(bService.createBrand(a), true);
		
	}//end test
	
	@Test
	public void createBrandTestFail() {
		
		Brand a = new Brand(1, "Doritos");
		
		when(bMock.createBrand(a)).thenReturn(-1);
		Assertions.assertThrows(DatabaseException.class, ()-> bService.createBrand(a));
	}//end test
	
	@Test
	public void updateBrandTest() throws DatabaseException {
	
		Brand a = new Brand(1, "Doritos");
		
		bService.createBrand(a);
		
		a.setBrand("Cheetos");
		
		when(bMock.updateBrand(a)).thenReturn(true);
		assertEquals(bService.updateBrand(a), true);
	}//end test
	
	@Test
	public void updateBrandTestFail(){
	
		Brand a = new Brand(1, "Doritos");
		
		when(bMock.updateBrand(a)).thenReturn(false);
		Assertions.assertThrows(DatabaseException.class, ()-> bService.updateBrand(a));
	}//end test
	
	@Test
	public void deleteBrandByIdTest() throws DatabaseException{
			
		when(bMock.deleteBrandById(1)).thenReturn(true);
		assertEquals(bService.deleteBrandById(1), true);
		
		
	}//end test
	
	@Test
	public void deleteBrandByIdTestFail() {
		when(bMock.deleteBrandById(1)).thenReturn(false);
		Assertions.assertThrows(DatabaseException.class, ()-> bService.deleteBrandById(1));
	}
	
}//end BrandServiceTest
