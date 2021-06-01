package com.india.ecommerce.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Orders {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long orderId;
private Date datetime;
private double totalPrice;

@ManyToOne
@JoinColumn(name="userId")
private User user;

public long getOrderId() {
	return orderId;
}
public void setOrderId(long orderId) {
	this.orderId = orderId;
}

public double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(double totalPrice) {
	this.totalPrice = totalPrice;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
/*public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}*/
public Date getDatetime() {
	return datetime;
}
public void setDatetime(Date datetime) {
	this.datetime = datetime;
}

}
