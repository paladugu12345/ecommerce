package com.india.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderDetails")
public class OrderDetails {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long orderdetailsId;
private int quantity;
private double price;
@ManyToOne
@JoinColumn(name="productId")
private Product product;

@ManyToOne
@JoinColumn(name="orderId")
private Orders orders;

public long getOrderdetailsId() {
	return orderdetailsId;
}
public void setOrderdetailsId(long orderdetailsId) {
	this.orderdetailsId = orderdetailsId;
}
public Orders getOrders() {
	return orders;
}
public void setOrders(Orders orders) {
	this.orders = orders;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
//public Order getOrder() {
//	return order;
//}
//public void setOrder(Order order) {
//	this.order = order;
//}


}
