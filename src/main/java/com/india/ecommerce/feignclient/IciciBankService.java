package com.india.ecommerce.feignclient;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.india.ecommerce.dto.TransactionDto;
import com.india.ecommerce.dto.AccountDetailsDto;
import com.india.ecommerce.dto.FundTransferRequestDto;
@FeignClient(value = "icicibank-Service", url = "localhost:1501/icicibank")
@RequestMapping(path = "/icicibankdetails")
public interface IciciBankService {
	
@PostMapping("/fundtranfer")
public List<TransactionDto> sendMoney(@Valid @RequestBody FundTransferRequestDto fundTransferRequestDto) throws Exception;
	
 @GetMapping("/acouunt/{accountNo}")
	
	public AccountDetailsDto getAccountDetails(@PathVariable Long accountNo);
}
