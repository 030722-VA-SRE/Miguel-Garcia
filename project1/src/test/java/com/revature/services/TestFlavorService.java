package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.exceptions.BrandNotFoundException;
import com.revature.exceptions.FlavorAlreadyExistException;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Brand;
import com.revature.models.Flavor;
import com.revature.repositories.BrandRepository;
import com.revature.repositories.FlavorRepository;

@ExtendWith(MockitoExtension.class)
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
		
		Flavor flavor = new Flavor(1,"Red", 10, 10, a);
		
		Mockito.when(fr.findById(1)).thenReturn(Optional.of(flavor));
		assertEquals(fs.getFlavorById(1), flavor);
		
	}//end
	
	@Test
	void getFlavorByIdFailTest() {
		
		
		Assertions.assertThrows(FlavorNotFoundException.class, () -> fs.getFlavorById(1));
		
	}//end
	
	@Test
	void getAllFlavorsTest() {
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		Flavor c = new Flavor(2, "Blue", 10, 10, a);
		Flavor d = new Flavor(3, "Green", 10, 10, a);
		
		List<Flavor> fList = Arrays.asList(b,c,d);
		
		Mockito.when(fr.findAll()).thenReturn(fList);
		assertEquals(fs.getAllFlavors(), fList);
		
	}//end
	
	@Test
	void createFlavorTest() {
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		Mockito.when(fr.save(b)).thenReturn(b);
		assertEquals(fs.createFlavor(b), b);
		
	}//end
	
	@Test
	void createFlavorWithBrandIdFailTest() {
		
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		Assertions.assertThrows(BrandNotFoundException.class, () ->fs.createFlavorWithBrandId(b, 2));
	}
	
	@Test
	void updateFlavorTest() {
		
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		Mockito.when(fr.findById(1)).thenReturn(Optional.of(b));
		Mockito.when(fr.save(b)).thenReturn(b);
		
		assertEquals(fs.flavorUpdate(1, b), b);
	}
	
	@Test
	void updateFlavorTestFail(){
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		boolean thrown = false;
				
		try {
			fs.flavorUpdate(1, b);
		}catch(FlavorNotFoundException e) {
			thrown = true;
		}
		
		assertTrue(thrown);
	}//end
	
	
	
}//end
