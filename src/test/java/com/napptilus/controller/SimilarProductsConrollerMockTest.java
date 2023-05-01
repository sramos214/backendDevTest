package com.napptilus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.napptilus.dto.ProductDetailDto;
import com.napptilus.service.IProductsService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SimilarProductsConrollerMockTest {
	
	@MockBean
	private IProductsService productsService;
	
	@Autowired
	private TestRestTemplate restTemplate;

	private List<String> similarIds = new ArrayList<>();

	private List<ProductDetailDto> productDetailDtos  = new ArrayList<>();
    	

    @BeforeEach
    void setUp() {
    	
    	similarIds.add("2");
    	similarIds.add("3");
    	similarIds.add("4");
    	
    	productDetailDtos.add( new ProductDetailDto("2", "Dress", 19.99F, true));
    	productDetailDtos.add( new ProductDetailDto("3", "Blazer", 29.99F, false));
    	productDetailDtos.add( new ProductDetailDto("4", "Boots", 39.99F, true));
    	
    	
    	when(productsService.getProductSimilarids(anyString())).thenReturn(new ArrayList<>());
    	when(productsService.getProductDetail(new ArrayList<>())).thenReturn(new ArrayList<>());
    	
    	when(productsService.getProductSimilarids("1")).thenReturn(similarIds);
    	when(productsService.getProductDetail(similarIds)).thenReturn(productDetailDtos);
    }

    @Test
    void successProductSimilar() {
    	
    	ResponseEntity<String> reponse = 
    			restTemplate.getForEntity("/product/1/similar",String.class);
		
		assertEquals(HttpStatus.OK, reponse.getStatusCode());

		assertNotNull(reponse.getBody());
		
		assertTrue(reponse.getBody().contains(productDetailDtos.get(0).getName()));

    }
    
    @Test
    void notFoundProductSimilar() {
    	
    	ResponseEntity<String> reponse = 
    			restTemplate.getForEntity("/product/2/similar",String.class);
    	
    	assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
    	
    	assertNull(reponse.getBody());
    	
    }
}