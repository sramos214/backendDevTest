package com.napptilus.service.impl;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.napptilus.dto.ProductDetailDto;
import com.napptilus.service.IProductsService;

@Service
public class ProductsServiceImpl implements IProductsService {
	
	@Value("${api.similar-ids-url}")
	private String similarIdsUrl;
	
	@Value("${api.product-detail-url}")
	private String productDetailUrl;
	
	@Value("${api.base-url}")
	private String baseUrl;
	
	@Value("${api.time-out-seconds}")
	private Integer timeOut;
	
	Logger log = LoggerFactory.getLogger(ProductsServiceImpl.class);

	private WebClient webClient() {
		return WebClient.builder().baseUrl(baseUrl).build();
	}
	
	
	private ProductDetailDto rquestProductDetail(String id) {
		
		try {
			return webClient().get()
					.uri(productDetailUrl, id)
					.retrieve()
					.bodyToMono(ProductDetailDto.class)
					.timeout(Duration.ofSeconds(timeOut))
					.block();
		}catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}

	@Override
	@Cacheable("productSimilarids")
	public List<String> getProductSimilarids(String productId) {
		
		try {
			String[] productSimilarids = webClient()
					.get()
					.uri(similarIdsUrl, productId)
					.retrieve()
					.bodyToMono(String[].class)
					.timeout(Duration.ofSeconds(timeOut))
					.block();
			
			return Arrays.asList(productSimilarids);
			
		} catch (Exception e) {
			log.error(e.getMessage());
			return new ArrayList<>();
		}
		
	}

	@Override
	@Cacheable("productDetail")
	public List<ProductDetailDto> getProductDetail(List<String> productsId) {

		List<ProductDetailDto> productDetailDtos = productsId.stream()
				.parallel()
				.map(this::rquestProductDetail)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());

		return productDetailDtos != null ? productDetailDtos : new ArrayList<>();

	}

}
