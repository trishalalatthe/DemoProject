package com.myretail.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Repository;

import com.myretail.controller.ProductDetailController;
import com.myretail.entity.Product;

/**
 * Product Detail Repository class to do all the DB transactions
 * @author Trishala
 *
 */
@Repository
public class ProductDetailsRepository {
	private static final Logger logger = LoggerFactory.getLogger(ProductDetailController.class);
	@Autowired	
	CassandraOperations cassandraOperations;
	  
    /** MycassandraOperations Default constructor*/
    public ProductDetailsRepository() {		
    	logger.debug("ProductDetailsRepositor");
    }
   
    
    /**
     * Get the Entity using Id.
     * @param product id
     * @return product entity
     */
    public Product findProductById(String productId) throws DataAccessException{
        return cassandraOperations.selectOneById(productId, Product.class);
    }
    
    /**
     * Update the product value
     * @param product
     * @return boolean
     * @throws DataAccessException
     */
    public Boolean updateProductValue(Product product) throws DataAccessException{
    	cassandraOperations.update(product);
		return true;
    }
    
    /**
     * Method to check if the product exists 
     * @param productId
     * @return boolean
     * @throws DataAccessException
     */
    public boolean productValueExists(String productId) throws DataAccessException{    
    	return cassandraOperations.exists(productId,Product.class);		 
    }
        
}
