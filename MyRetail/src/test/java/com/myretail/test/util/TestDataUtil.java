package com.myretail.test.util;

import java.math.BigDecimal;

import com.myretail.dto.ProductDetails;
import com.myretail.entity.Product;

public class TestDataUtil {
 
	public static ProductDetails details(){
		ProductDetails details = new ProductDetails();		
		details.setProduct(getProduct());
		details.setProductId("13860428");
		details.setProductName("The Big Lebowski (Blu-ray)");
		return details;
	}
	
	public static Product getProduct(){		
		Product product = new Product();
		product.setCurrencyCode("USD");		
		product.setProductValue(new BigDecimal(10.49));		
		product.setProductId("13860428");		
		return product;
	}
	
	public static Product getSaveProduct(){		
		Product product = new Product();
		product.setCurrencyCode("USD");		
		product.setProductValue(new BigDecimal(8.49));		
		product.setProductId("13860428");		
		return product;
	}
	public static ProductDetails saveDetails(){
		ProductDetails details = new ProductDetails();		
		details.setProduct(getSaveProduct());
		details.setProductId("13860428");
		details.setProductName("The Big Lebowski (Blu-ray)");
		return details;
	}
}
