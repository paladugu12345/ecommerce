package com.india.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class FundTransferRequestDto {
	@NotNull(message="please enter fromaccount")
	private long fromAccount;
	
	@NotNull(message="please enter toaccount")
	private long toAccount;
	
	@NotNull(message="please enter amount")
	private Double amount;
	
	private String remarks;


	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	
}
