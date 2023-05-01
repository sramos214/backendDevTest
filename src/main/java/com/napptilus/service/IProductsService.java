package com.napptilus.service;

import java.util.List;

import com.napptilus.dto.ProductDetailDto;

public interface IProductsService {

	List<String> getProductSimilarids(String productId);

	List<ProductDetailDto> getProductDetail(List<String> productsId);

}
