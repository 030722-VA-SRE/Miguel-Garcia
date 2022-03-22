package com.revature.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dao.FlavorDao;
import com.revature.exceptions.DatabaseException;
import com.revature.exceptions.FlavorNotFoundException;
import com.revature.models.Brand;
import com.revature.models.Flavor;


class FlavorServiceTest {

	private static FlavorDao fMock;
	private static FlavorService fService;
	
	@BeforeAll
	public static void setup(){
		fMock = Mockito.mock(FlavorDao.class);
		fService = new FlavorService(fMock);
	}//end 
	
	@Test
	void getAllFlavorsTest(){
		
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		Flavor c = new Flavor(2, "Blue", 10, 10, a);
		Flavor d = new Flavor(3, "Green", 10, 10, a);
		
		List<Flavor> fList = Arrays.asList(b,c,d);
		
		when(fMock.getAllFlavors()).thenReturn(fList);
		assertEquals(fService.getAllFlavors(), fList);
		
	}//end test
	
	@Test
	void getFlavorByIdTest() throws FlavorNotFoundException {
		
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		when(fMock.getFlavorById(1)).thenReturn(b);
		assertEquals(fService.getFlavorById(1), b);
		
	}//end

	@Test
	void getFlavorByIdTestFail() {
		
		when(fMock.getFlavorById(1)).thenReturn(null);
		Assertions.assertThrows(FlavorNotFoundException.class, () -> fService.getFlavorById(1));
		
	}//end
	
	@Test
	void getFlavorByBrandId() throws DatabaseException{
		Brand a = new Brand(1, "Cheetos");
		
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		Flavor c = new Flavor(2, "Blue", 10, 10, a);
		Flavor d = new Flavor(3, "Green", 10, 10, a);
		
		List<Flavor> fList = Arrays.asList(b,c,d);
		
		when(fMock.getFlavorByBrandId(1)).thenReturn(fList);
		assertEquals(fService.getFlavorByBrandId(1), fList);
		
	}//end test
	
	@Test
	void getFlavorByBrandIdFail() {
		when(fMock.getFlavorByBrandId(1)).thenReturn(null);
		Assertions.assertThrows(DatabaseException.class, () -> fService.getFlavorByBrandId(1));
	}//end
	
	@Test
	void createFlavor() throws DatabaseException{
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		when(fMock.createFlavor(b)).thenReturn(1);
		assertEquals(fService.createFlavor(b), true);
	}//end
	
	@Test
	void createFlavorFalse() {
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		when(fMock.createFlavor(b)).thenReturn(-1);
		Assertions.assertThrows(DatabaseException.class, () -> fService.createFlavor(b));
	}//end
	
	@Test
	void updateFlavorPriceTest() throws DatabaseException {
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		when(fMock.updateFlavorPrice(b)).thenReturn(true);
		assertEquals(fService.updateFlavorPrice(b), true);
		
	}//public
	
	
	@Test
	void updateFlavorPriceTestFail() {
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		when(fMock.updateFlavorPrice(b)).thenReturn(false);
		Assertions.assertThrows(DatabaseException.class, () -> fService.updateFlavorPrice(b));
	}
	
	@Test
	void deleteFlavorByIdTest() throws DatabaseException{
		
		when(fMock.deleteFlavorById(1)).thenReturn(true);
		assertEquals(fService.deleteFlavorById(1), true);
	}
	
	@Test
	void deleteFlavorByIdTestFail() {
		when(fMock.deleteFlavorById(1)).thenReturn(false);
		Assertions.assertThrows(DatabaseException.class, () -> fService.deleteFlavorById(1));
	}
	
	@Test
	void getFlavorByNameTest() throws DatabaseException {
		
		List<Flavor> f = new ArrayList<>();
		
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		
		when(fMock.getFlavorByName("Red")).thenReturn(f);
		assertEquals(fService.getFlavorByName("Red"), f);
		
	}//end
	
	@Test
	void getFlavorByNameTestFail() {
		when(fMock.getFlavorByName("name")).thenReturn(null);
		Assertions.assertThrows(DatabaseException.class, () -> fService.getFlavorByName("name"));
	}
	
	@Test
	void getFlavorByOuncesTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		
		when(fMock.getFlavorByOunces(10)).thenReturn(f);
		assertEquals(fService.getFlavorByOunces(10), f);
		
	}//end
	
	@Test
	void getFlavorByOuncesTestFail() {
		when(fMock.getFlavorByOunces(10)).thenReturn(null);
		Assertions.assertThrows(DatabaseException.class, () -> fService.getFlavorByOunces(10));
	}
	
	@Test
	void getFlavorByPriceTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.getFlavorByPrice(10)).thenReturn(f);
		assertEquals(fService.getFlavorByPrice(10), f);
		
	}//end
	
	@Test
	void getFlavorByPriceTestFail() {
		when(fMock.getFlavorByPrice(10)).thenReturn(null);
		Assertions.assertThrows(DatabaseException.class, () -> fService.getFlavorByPrice(10));
	}

	@Test
	void getFlavorByNameAndOuncesTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.getFlavorByNameAndOunces("Red",10)).thenReturn(f);
		assertEquals(fService.getFlavorByNameAndOunces("Red", 10), f);
		
	}//end
	
	@Test
	void getFlavorByNameAndPriceTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.getFlavorByNameAndPrice("Red",10)).thenReturn(f);
		assertEquals(fService.getFlavorByNameAndPrice("Red", 10), f);
		
	}//end
	
	@Test
	void getFlavorByOuncesAndPriceTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.getFlavorByOuncesAndPrice(10,10)).thenReturn(f);
		assertEquals(fService.getFlavorByOuncesAndPrice(10, 10), f);
		
	}//end
	
	@Test
	void getFlavorByNameOuncesAndPriceTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock. getFlavorByNameOuncesAndPrice("Red",10,10)).thenReturn(f);
		assertEquals(fService.getFlavorByNameOuncesAndPrice("Red", 10, 10), f);
		
	}//end
	
	@Test
	void getFlavorByNameWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock. getFlavorByNameWithBrandId("Red",1)).thenReturn(f);
		assertEquals(fService.getFlavorByNameWithBrandId("Red", 1), f);
		
	}//end
	
	@Test
	void getFlavorByOuncesWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock. getFlavorByOuncesWithBrandId(10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByOuncesWithBrandId(10, 1), f);
		
	}//end
	
	@Test
	void getFlavorByPriceWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock. getFlavorByPriceWithBrandId(10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByPriceWithBrandId(10, 1), f);
		
	}//end
	
	@Test
	void  getFlavorByNameAndOuncesWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.  getFlavorByNameAndOuncesWithBrandId("Red", 10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByNameAndOuncesWithBrandId("Red", 10, 1), f);
		
	}//end
	
	@Test
	void  getFlavorByNameAndPriceWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.  getFlavorByNameAndPriceWithBrandId("Red", 10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByNameAndPriceWithBrandId("Red", 10, 1), f);
		
	}//end
	
	@Test
	void  getFlavorByOuncesAndPriceWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.  getFlavorByOuncesAndPriceWithBrandId(10, 10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByOuncesAndPriceWithBrandId(10, 10, 1), f);
		
	}//end
	
	@Test
	void  getFlavorByNameOuncesAndPriceWithBrandIdTest() throws DatabaseException {		
		List<Flavor> f = new ArrayList<>();
		Brand a = new Brand(1, "Cheetos");
		Flavor b = new Flavor(1,"Red", 10, 10, a);
		
		f.add(b);
		when(fMock.  getFlavorByNameOuncesAndPriceWithBrandId("Red", 10, 10,1)).thenReturn(f);
		assertEquals(fService.getFlavorByNameOuncesAndPriceWithBrandId("Red", 10, 10, 1), f);
		
	}//end
	
}//end 
