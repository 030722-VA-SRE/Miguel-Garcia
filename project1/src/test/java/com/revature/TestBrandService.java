package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.models.Brand;
import com.revature.repositories.BrandRepository;
import com.revature.services.BrandService;

@ExtendWith(MockitoExtension.class)
class TestBrandService {
	
	@Mock
	private BrandRepository br;
	
	@InjectMocks
	BrandService bs;
	
	@Test
	void getAllBrandsTest(){
		
		List<Brand> brandList = new ArrayList<>();
		Brand b1 = new Brand(1, "Doritos");
		Brand b2 = new Brand(2, "Cheetos");
		Brand b3 = new Brand(3, "Lays");
		
		brandList.add(b1);
		brandList.add(b2);
		brandList.add(b3);
		
		Mockito.when(br.findAll()).thenReturn(brandList);
		
		assertEquals(bs.getAllBrands(), brandList);
		
	}//end
}
