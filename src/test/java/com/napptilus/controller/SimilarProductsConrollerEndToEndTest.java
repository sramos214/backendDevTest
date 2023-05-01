package com.napptilus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SimilarProductsConrollerEndToEndTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
    void successProductSimilar() {
    	
    	ResponseEntity<String> reponse = 
    			restTemplate.getForEntity("/product/1/similar",String.class);
		
		assertEquals(HttpStatus.OK, reponse.getStatusCode());

		assertNotNull(reponse.getBody());
		
		assertTrue(reponse.getBody().contains("Dress"));

    }
    
    @Test
    void notFoundProductSimilar() {
    	
    	ResponseEntity<String> reponse = 
    			restTemplate.getForEntity("/product/6/similar",String.class);
    	
    	assertEquals(HttpStatus.NOT_FOUND, reponse.getStatusCode());
    	
    	assertNull(reponse.getBody());
    	
    }
}