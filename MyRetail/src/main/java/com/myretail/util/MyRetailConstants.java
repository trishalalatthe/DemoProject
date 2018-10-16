package com.myretail.util;

import org.springframework.stereotype.Component;

@Component
public class MyRetailConstants {
	
	public static final String MYRETAIL_URL_PATH = "https://redsky.target.com/v2/pdp/tcin/";
	public static final String MYRETAIL_URL_PATH_PARAMS = "?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics";
	public static final String PRODUCT = "product";
	public static final String ITEM ="item";
	public static final String PRODUCT_DESCRIPTION ="product_description";
	public static final String TITLE ="title";
	public static final String PRODUCT_NOT_FOUND="The Product Id given not found :";
	public static final String UNABLE_TO_SAVE="Unable to save given data for the Product Id : ";
	public static final String PARAMS="parameters";
	public static final String PRODUCT_SAVED="Product Detail Save Successfully";
	public static final String PRODUCT_DONT_MATCH="Product ID given in the Body and URL do not match";
}
