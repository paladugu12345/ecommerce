package com.india.ecommerce.dto;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class BuyProductRequestDto {
	@NotEmpty(message="Please provide user id")
	private Long userId;
	@Digits(integer = 8, fraction = 1, message = "InValid accountNumber")
	private Long accountNo;
	private List<BuyProductDto> products;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public List<BuyProductDto> getProducts() {
		return products;
	}
	public void setProducts(List<BuyProductDto> products) {
		this.products = products;
	}
}
