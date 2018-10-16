package com.myretail.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.myretail.config.CassandraConfig;
import com.myretail.dto.ProductDetails;
import com.myretail.repository.ProductDetailsRepository;
import com.myretail.test.util.TestDataUtil;
import com.myretail.util.ProductUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ProductDetailsService.class, CassandraConfig.class })
public class ProductDetailServiceTest {
	@Autowired
	private ProductDetailsService detailService;

	@MockBean
	ProductDetailsRepository productDetailsRepositoryMock;
	
	@MockBean
	ProductUtil productUtilMock;

	@Test
	public void retrieveProductValueTest() {
		when(productUtilMock.getProductName(ArgumentMatchers.any())).thenReturn("The Big Lebowski (Blu-ray)");		
		when(productDetailsRepositoryMock.findProductById(ArgumentMatchers.any()))
				.thenReturn(TestDataUtil.getProduct());			
		ProductDetails details = null;
		try {
			details = detailService.retrieveProductValue("13860428");
		}  catch (Exception e) {			
			e.printStackTrace();
		}
		assertEquals(TestDataUtil.details(), details);
	}
	
	@Test
	public void updateDetailsTest() {				
		when(productDetailsRepositoryMock.productValueExists(ArgumentMatchers.any())).thenReturn(true);	
		when(productDetailsRepositoryMock.updateProductValue(ArgumentMatchers.any())).thenReturn(true);	
		boolean saveFlag = detailService.updateProductValue(TestDataUtil.saveDetails());
		assertTrue(saveFlag);		
	}
	
	@Test
	public void updateDetailsWhenProdcutNotAvailable() {				
		when(productDetailsRepositoryMock.productValueExists(ArgumentMatchers.any())).thenReturn(false);	
		when(productDetailsRepositoryMock.updateProductValue(ArgumentMatchers.any())).thenReturn(true);	
		boolean saveFlag = detailService.updateProductValue(TestDataUtil.saveDetails());
		assertFalse(saveFlag);		
	}

}
