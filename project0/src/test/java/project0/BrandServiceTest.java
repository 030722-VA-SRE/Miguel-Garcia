package project0;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dao.BrandDao;
import com.revature.dao.BrandPostgres;
import com.revature.models.Brand;
import com.revature.services.BrandService;

class BrandServiceTest {
	
	
	@Test
	void getAllBrandsTest() {
		
		BrandDao bMock = Mockito.mock(BrandDao.class);
		BrandService bService = new BrandService(bMock);
		
		Brand a = new Brand(1, "Cheetos");
		Brand b = new Brand(2, "Fritos");
		Brand c = new Brand(3, "Doritos");
		
		List<Brand> brandList = Arrays.asList(a,b,c);
		
		when(bMock.getAllBands()).thenReturn(brandList);
		assertEquals(brandList, bService.getAllBrands());
		
		
		
	}//end test

}
