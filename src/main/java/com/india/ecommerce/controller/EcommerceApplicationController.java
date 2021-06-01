package com.india.ecommerce.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.india.ecommerce.dto.BuyProductRequestDto;
import com.india.ecommerce.dto.OrderHistoryResponseDto;
import com.india.ecommerce.dto.ProductResponseDto;
import com.india.ecommerce.dto.ProductSearchDto;
import com.india.ecommerce.exception.InavalidArgumentException;
import com.india.ecommerce.exception.InavalidUserException;
import com.india.ecommerce.service.OrderHistoryService;
import com.india.ecommerce.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Validated
@RestController
@RequestMapping("/ecommerce")
public class EcommerceApplicationController {

	@Autowired
	private OrderHistoryService orderHistoryService;
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping("/searchProduct")
	public ResponseEntity<List<ProductResponseDto>> searchProduct( @RequestBody ProductSearchDto productSearchDto) throws InavalidArgumentException {
		
		List<ProductResponseDto> productResponseDto=new ArrayList<ProductResponseDto>();		
		productResponseDto=productService.searchProduct(productSearchDto);
		return new ResponseEntity<>(productResponseDto,HttpStatus.CREATED);
	}
	
	@PostMapping("/buyProducts")
	public String purchaseProduct(@RequestBody BuyProductRequestDto requestDto) throws Exception {
		boolean order=productService.purchaseProduct(requestDto);
		if(order==false)
		{
			return "issue while placing the order";
		}
		return "order successfully placed";
			
	}

	
	@GetMapping("/orderhistory/{userid}/")
	public List<OrderHistoryResponseDto> getorderdetails( @PathVariable("userid") long userid) throws InavalidUserException,ParseException  {
		
		List<OrderHistoryResponseDto> orderHistoryResponseDto=orderHistoryService.getOrderDetails(userid);
		return orderHistoryResponseDto;
	}
}
