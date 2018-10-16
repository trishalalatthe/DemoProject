package com.myretail.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myretail.entity.Product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Product Detail DTO for sending response back 
 * @author Trishala
 *
 */
@Component
@Data
public class ProductDetails {	
	/**
	 * The product ID
	 */
	@JsonProperty("id")
	@ApiModelProperty(notes = "The product ID")
	private String productId;
	/**
	 * The product Name
	 */
	@JsonProperty("name")
	@ApiModelProperty(notes = "The product Name")
	private String productName;
	/**
	 * The product price details
	 */
	@ApiModelProperty(notes = "The product price details")
	@JsonProperty("current_price")
	private Product product;

}
