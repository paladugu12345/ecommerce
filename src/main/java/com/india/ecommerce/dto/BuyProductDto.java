package com.india.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BuyProductDto {
	@NotNull(message = "please provide productid ")
	@Size(min = 1, max = 10, message = "please provide productid")
	private Long productId;
	
	@NotNull(message = "please provide quantity details ")
	@Size(min = 1, max = 10, message = "please provide quantity details")
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	
}
