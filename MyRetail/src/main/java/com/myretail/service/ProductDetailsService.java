package com.myretail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import com.myretail.dto.ProductDetails;
import com.myretail.entity.Product;
import com.myretail.repository.ProductDetailsRepository;
import com.myretail.util.ProductUtil;

/**
 * Service class for the Product details.
 * 
 * @author Trishala
 *
 */
@Service
public class ProductDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(ProductDetailsService.class);

	@Autowired
	ProductDetailsRepository productDetailsRepository;

	@Autowired
	ProductUtil productUtil;

	/**
	 * Retrive the Product details from the Cassandra DB and from the External
	 * URL
	 * 
	 * @param productId
	 * @return product detail dto
	 */
	public ProductDetails retrieveProductValue(String productId) {
		ProductDetails productDetails = null;
		try {
			/* Get the Product details from the DB based on the product id */
			Product product = productDetailsRepository.findProductById(productId);
			if (product != null) {
				productDetails = new ProductDetails();
				productDetails.setProductId(product.getProductId());
				productDetails.setProduct(product);
				/*
				 * If the product exists in the DB then hit the external service
				 * for the Product Name
				 */
				String name = productUtil.getProductName(productId);
				if (name != null) {
					productDetails.setProductName(name);
				}
			}

		} catch (DataAccessException dae) {
			logger.debug(dae.getMessage());
		}
		return productDetails;
	}

	/**
	 * Update the Product details in the Cassandra DB
	 * 
	 * @param details
	 * @return boolean
	 */
	public boolean updateProductValue(ProductDetails details) {
		boolean updateFlag = false;
		try {
			/* If the data exists then update the value */
			updateFlag = productDetailsRepository.updateProductValue(details.getProduct());
		} catch (DataAccessException e) {
			logger.debug(e.getMessage());
		}
		return updateFlag;
	}

	/**
	 * Checks if the given product id is avaiable in DB
	 * @param details
	 * @return boolean
	 */
	public boolean productExists(ProductDetails details) {
		/* Check if the product exists in the DataBase */
		return productDetailsRepository.productValueExists(details.getProductId());

	}

}
