package com.india.ecommerce.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ProductDto {
	//@NotNull(message = "please provide valid productId")
	private String ProductName;
	//@NotEmpty(message = "quantity should not be empty")
	//@NotNull(message = "please provide valid quantity")
	//@Size(min = 1, max = 10, message = "please provide valid quantity")
	private int quantity;
	private double unitPrice;
	
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}

}
