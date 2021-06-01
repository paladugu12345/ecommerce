package com.india.ecommerce.service;

import java.util.List;

import com.india.ecommerce.dto.BuyProductRequestDto;
import com.india.ecommerce.dto.ProductResponseDto;
import com.india.ecommerce.dto.ProductSearchDto;
import com.india.ecommerce.exception.InavalidArgumentException;

public interface ProductService {
	public List<ProductResponseDto> searchProduct(ProductSearchDto  productRequestDto)throws InavalidArgumentException ;
	public boolean purchaseProduct(BuyProductRequestDto buyproductDto) throws Exception;

}
