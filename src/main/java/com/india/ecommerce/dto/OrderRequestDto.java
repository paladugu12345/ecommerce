package com.india.ecommerce.dto;
import javax.validation.constraints.NotEmpty;

public class OrderRequestDto {
	
	@NotEmpty(message="Please provide user id")
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
