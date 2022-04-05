package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.revature.exceptions.FlavorAlreadyExistException;
import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.repositories.BrandRepository;
import com.revature.repositories.FlavorRepository;

class TestFlavorService {

	@Mock
	private FlavorRepository fr;
	
	@Mock
	private BrandRepository br;
	
	@InjectMocks
	FlavorService fs;
	
	@Test
	void getFlavorByIdTest() {
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		//Mockito.when(fr.find)
	}
	


}
