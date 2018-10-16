package com.myretail.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.service.ProductDetailsService;
import com.myretail.test.util.TestDataUtil;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { ProductDetailController.class })
@WebMvcTest(ProductDetailController.class)
public class ProductDetailControllerTest {

	protected MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext applicationContext;

	@MockBean
	private ProductDetailsService detailsServiceMock;

	@SpyBean
	ObjectMapper mapper;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	public void getDetailsTest() throws Exception {
		when(detailsServiceMock.retrieveProductValue(ArgumentMatchers.any())).thenReturn(TestDataUtil.details());
		mockMvc.perform(MockMvcRequestBuilders.get("/products/13860428")).andExpect(status().isOk())
				.andExpect(content().string(mapper.writeValueAsString(TestDataUtil.details())));
	}

	@Test
	public void getDetailsEmptyTest() throws Exception {
		when(detailsServiceMock.retrieveProductValue(ArgumentMatchers.any())).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/products/13860428")).andExpect(status().isNotFound());
	}

	@Test
	public void updateDetailsTest() throws Exception {
		when(detailsServiceMock.updateProductValue(TestDataUtil.saveDetails())).thenReturn(true);
		mockMvc.perform(MockMvcRequestBuilders.put("/products/13860428").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(TestDataUtil.saveDetails()))).andExpect(status().isOk());
	}	
	
	@Test
	public void updateDetailsFailTest() throws Exception {
		when(detailsServiceMock.updateProductValue(TestDataUtil.saveDetails())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.put("/products/13860428").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(TestDataUtil.saveDetails()))).andExpect(status().isInternalServerError());
	}
	
	@Test
	public void productIDNotMatching() throws Exception {
		when(detailsServiceMock.updateProductValue(TestDataUtil.saveDetails())).thenReturn(false);
		mockMvc.perform(MockMvcRequestBuilders.put("/products/13860423").contentType(MediaType.APPLICATION_JSON)
				.content(convertObjectToJsonBytes(TestDataUtil.saveDetails()))).andExpect(status().isBadRequest());
	}
	public byte[] convertObjectToJsonBytes(Object object) throws IOException {		
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
