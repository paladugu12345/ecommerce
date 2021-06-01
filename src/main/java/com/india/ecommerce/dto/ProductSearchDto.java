package com.india.ecommerce.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductSearchDto {
	@NotEmpty(message = "product should not be empty")
	//@Size(min = 3, max = 200,message = " fristName should less then 200 character only")
	private String productName;
	
	@NotEmpty(message = "categoryName should not be empty")
	//@Size(min = 2, max = 200,message = " fristName should less then 200 character only")
	private String categoryName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
