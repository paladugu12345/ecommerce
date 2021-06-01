package com.india.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.india.ecommerce.entity.Orders;
import com.india.ecommerce.entity.User;
import com.india.ecommerce.exception.InavalidUserException;
import com.india.ecommerce.dto.OrderHistoryResponseDto;
import com.india.ecommerce.dto.ProductDto;
import com.india.ecommerce.entity.OrderDetails;
import com.india.ecommerce.repository.OrderHistoryRepository;
import com.india.ecommerce.repository.OrderRespository;
import com.india.ecommerce.repository.UserRespository;
import com.india.ecommerce.service.OrderHistoryService;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
	   private static final Logger logger = LoggerFactory.getLogger(OrderHistoryServiceImpl.class);

	@Autowired
	public OrderHistoryRepository  orderHistoryRepository;
	@Autowired
    public OrderRespository  orderRepository;
	
	@Autowired
	
    public UserRespository  userRespository;
	
	@Override
	public List<OrderHistoryResponseDto> getOrderDetails(long userId) throws InavalidUserException {
		// TODO Auto-generated method stub
		List<OrderDetails> orderDetails=new ArrayList<OrderDetails>();
		List<OrderHistoryResponseDto> OrderHistoryResDtoList=new ArrayList<OrderHistoryResponseDto>();
		List<OrderHistoryResponseDto> OrderHistoryResDtoList2=new ArrayList<OrderHistoryResponseDto>();
		
		Optional<User> userIdvalue=userRespository.findById(userId);

		if(userIdvalue.isPresent())
		{
	    	User user=userIdvalue.get();
			List<Orders> orderlist =user.getOrderId();
			if(orderlist!=null)
			{
			for(Orders orderdetl:orderlist)
			{
				long orderId=orderdetl.getOrderId();
				logger.info("orderid-----"+orderId);
				orderDetails=orderHistoryRepository.findByOrders(orderId);
			for( OrderDetails orderDetls:orderDetails)
			{
				OrderHistoryResponseDto orderHistoryResponseDto=new OrderHistoryResponseDto();
				BeanUtils.copyProperties(orderDetls,orderHistoryResponseDto);
				ProductDto productDto=new ProductDto();
				List<ProductDto> productDtolist=new ArrayList<ProductDto>();
				productDto.setProductName(orderDetls.getProduct().getProductName());
				productDto.setQuantity(orderDetls.getProduct().getQuantity());
				productDto.setUnitPrice(orderDetls.getProduct().getProductPrice());
				productDtolist.add(productDto);
				orderHistoryResponseDto.setOrderId(orderDetls.getOrders().getOrderId());
				orderHistoryResponseDto.setTotalPrice(orderDetls.getPrice());
				orderHistoryResponseDto.setOrderDate(orderDetls.getOrders().getDatetime());
				orderHistoryResponseDto.setProductdto(productDtolist);
				OrderHistoryResDtoList.add(orderHistoryResponseDto);		
			}
			logger.info("order-----"+orderDetails);
			}
			OrderHistoryResDtoList2.addAll(OrderHistoryResDtoList);
			}
			
		}
		else
		{
			throw new NullPointerException("userid is invalid!!!!");
			
		}
		
		return OrderHistoryResDtoList;
	}

}
