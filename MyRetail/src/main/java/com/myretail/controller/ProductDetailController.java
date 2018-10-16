package com.myretail.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myretail.dto.ErrorDetails;
import com.myretail.dto.ProductDetails;
import com.myretail.dto.SuccessDetail;
import com.myretail.exception.DataSaveFailureException;
import com.myretail.exception.ProductNotFoundException;
import com.myretail.service.ProductDetailsService;
import com.myretail.util.MyRetailConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Product detail class for the My retail application
 * 
 * @author Trishala
 *
 */
@RestController
@RequestMapping("/products")
@Api(value = "myRetailStore", description = "Operations pertaining to products in myRetail Store")
public class ProductDetailController {
	private static final Logger logger = LoggerFactory.getLogger(ProductDetailController.class);
	@Autowired
	ProductDetailsService productDetailsService;

	/**
	 * The method accepts the GET request for the product id and returns the
	 * products details accordingly.
	 * 
	 * @param productId
	 * @return product details
	 */
	@ApiOperation(value = "Get the details of the Product base on the Product ID ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
	@ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request."),
	@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(path = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getDetails(@PathVariable("product_id") String productId) {
		logger.info("ProductDetailController:getDetails:Start");
		ProductDetails details = new ProductDetails();
		try {
			details = productDetailsService.retrieveProductValue(productId);
			if (details == null || details.getProductId().isEmpty()) {
				throw new ProductNotFoundException(MyRetailConstants.PRODUCT_NOT_FOUND + productId);
			}
		}catch (ProductNotFoundException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getMessage()), HttpStatus.NOT_FOUND);
		}
		catch (Exception e) {	
			logger.error(e.getMessage());
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("ProductDetailController:getDetails:End");
		return new ResponseEntity<ProductDetails>(details, HttpStatus.OK);
	}

	/**
	 * method to Update the product value
	 * 
	 * @param productId
	 * @param details
	 * @return boolean
	 */
	@ApiOperation(value = "Update the price of the Product based on the Product Id ", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully update the product data"),
	@ApiResponse(code = 500, message = "The server encountered an unexpected condition which prevented it from fulfilling the request."),
	@ApiResponse(code = 400, message = "Bad Request") })
	@PutMapping(path = "/{product_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDetails(@PathVariable("product_id") String productId,
			@RequestBody ProductDetails details) {
		logger.info("ProductDetailController:updateDetails:Start");				
		SuccessDetail successDetails = null;
		try {
			if (!productId.equals(details.getProductId())) {
				throw new ProductNotFoundException(MyRetailConstants.PRODUCT_DONT_MATCH);
			}
			if (productId != null) {
				details.getProduct().setProductId(details.getProductId());
			}			
			if(!productDetailsService.productExists(details)){
				throw new ProductNotFoundException(MyRetailConstants.PRODUCT_NOT_FOUND + productId);
			}			
			if (!productDetailsService.updateProductValue(details)) {
				throw new DataSaveFailureException(MyRetailConstants.UNABLE_TO_SAVE + productId);
			} else {
				successDetails = new SuccessDetail(MyRetailConstants.PRODUCT_SAVED);
			}
		}catch(ProductNotFoundException e){	
			logger.error(e.getMessage());
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getMessage()), HttpStatus.BAD_REQUEST);
		}catch (Exception e){
			logger.error(e.getMessage());
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("ProductDetailController:updateDetails:End");
		return new ResponseEntity<SuccessDetail>(successDetails, HttpStatus.OK);
	}

}
