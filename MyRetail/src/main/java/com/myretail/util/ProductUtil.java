package com.myretail.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductUtil {
	private static final Logger logger = LoggerFactory.getLogger(ProductUtil.class);
	@Autowired
	RestTemplate restTemplate;

	public String getProductName(String productId) {
		logger.info("ProductUtil:getProductName:Start");
		String productName = null;
		String URL = MyRetailConstants.MYRETAIL_URL_PATH + productId + MyRetailConstants.MYRETAIL_URL_PATH_PARAMS;
		HttpEntity<String> entity = new HttpEntity<String>(MyRetailConstants.PARAMS, getHeaders());
		ResponseEntity<String> result = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
		try {
			JSONObject jsonObj = new JSONObject(result.getBody());
			productName = jsonObj.getJSONObject(MyRetailConstants.PRODUCT).getJSONObject(MyRetailConstants.ITEM)
					.getJSONObject(MyRetailConstants.PRODUCT_DESCRIPTION).getString(MyRetailConstants.TITLE);
		} catch (JSONException e) {
			logger.debug(e.getMessage());
		}
		logger.info("ProductUtil:getProductName:End");
		return productName;
	}

	public static HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		return headers;
	}
}
