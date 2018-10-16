package com.myretail.entity;

import java.math.BigDecimal;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Entity class for the Product detail table
 * @author Trishala
 *
 */
@Table(value="product_detail")
@Data
public class Product {	
	/**
	 * Product Id
	 */
	@PrimaryKey("product_id")
	@JsonIgnore
	@ApiModelProperty(notes = "The product ID")
	private String productId;
	/**
	 * Product Value
	 * 
	 */
	@Column(value="product_value")
	@JsonProperty("value")
	@ApiModelProperty(notes = "The product value/cost")
	private BigDecimal productValue;
	/**
	 * Currency of the value
	 * 
	 */
	@Column("currency_code")
	@JsonProperty("currency_code")
	@ApiModelProperty(notes = "The currency code")
	private String currencyCode;	
}
