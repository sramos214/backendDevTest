package com.napptilus;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


import com.napptilus.controller.SimilarProductsConroller;
import com.napptilus.service.IProductsService;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class BackendDevTestApplicationTests {

	@Autowired
	private SimilarProductsConroller similarProductsConroller;
	
	@Autowired
	private IProductsService productsService;

	@Test
	void contextLoads() {
		assertThat(similarProductsConroller).isNotNull();
		assertThat(productsService).isNotNull();
	}

}
