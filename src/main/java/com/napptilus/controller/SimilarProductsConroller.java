package com.napptilus.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.napptilus.dto.ProductDetailDto;
import com.napptilus.service.IProductsService;

@RestController
@RequestMapping(path = "/product", produces = "application/json")
public class SimilarProductsConroller {

	@Autowired
	private IProductsService productsService;
	
	Logger log = LoggerFactory.getLogger(SimilarProductsConroller.class);

	@GetMapping("/{productId}/similar")
	@ResponseBody
	public ResponseEntity<List<ProductDetailDto>> getProductSimilar(@PathVariable String productId)
			throws InterruptedException {
		//Long init = System.currentTimeMillis();

		List<String> productSimilarIds = productsService.getProductSimilarids(productId);

		List<ProductDetailDto> productSimilar = productsService.getProductDetail(productSimilarIds);

		//log.info("getProductSimilar execution time : " + (System.currentTimeMillis() - init));

		return productSimilar.isEmpty() ? 
				new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : 
					new ResponseEntity<>(productSimilar,  HttpStatus.OK);

	}
}
