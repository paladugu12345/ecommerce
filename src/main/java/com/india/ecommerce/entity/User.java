package com.india.ecommerce.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	
	private String firstName;
	private String lastName;
    private String emailId;
	private double phoneNumber;
	private String password;
	@OneToMany(mappedBy="user")
	private List<Orders> orderId;
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public List<Orders> getOrderId() {
		return orderId;
	}
	public void setOrderId(List<Orders> orderId) {
		this.orderId = orderId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	


}
