package com.india.ecommerce.service;

import java.util.List;

import com.india.ecommerce.dto.OrderHistoryResponseDto;
import com.india.ecommerce.entity.OrderDetails;
import com.india.ecommerce.exception.InavalidUserException;

public interface OrderHistoryService {
	 List<OrderHistoryResponseDto> getOrderDetails(long userId) throws InavalidUserException;

}
