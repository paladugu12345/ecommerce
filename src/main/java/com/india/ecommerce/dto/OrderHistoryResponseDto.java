package com.india.ecommerce.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class OrderHistoryResponseDto {
//@NotNull(message = "please provide valid productId")
//@Size(min = 1, max = 10, message = "please provide valid productId")
private long OrderId;

//@NotNull(message = "please provide valid totalprice")
//@Size(min = 5, max = 10, message = "please provide valid totalprice")
private double totalPrice;
//@DateTimeFormat(pattern="yyyy-MM-dd")
//@NotNull(message="please provide orderdate")
private Date orderDate;
public Date getOrderDate() {
	return orderDate;
}
public void setOrderDate(Date orderDate) {
	this.orderDate = orderDate;
}
//@NotNull
private List<ProductDto> productdto;

public long getOrderId() {
	return OrderId;
}
public void setOrderId(long orderId) {
	OrderId = orderId;
}
public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}

public List<ProductDto> getProductdto() {
	return productdto;
}
public void setProductdto(List<ProductDto> productdto) {
	this.productdto = productdto;
}

		

}
