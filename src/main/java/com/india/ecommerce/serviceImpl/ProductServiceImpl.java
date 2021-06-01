package com.india.ecommerce.serviceImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.india.ecommerce.dto.ProductSearchDto;
import com.india.ecommerce.dto.AccountDetailsDto;
import com.india.ecommerce.dto.BuyProductDto;
import com.india.ecommerce.dto.BuyProductRequestDto;
import com.india.ecommerce.dto.FundTransferRequestDto;
import com.india.ecommerce.dto.ProductResponseDto;
import com.india.ecommerce.entity.OrderDetails;
import com.india.ecommerce.entity.Orders;
import com.india.ecommerce.entity.Product;
import com.india.ecommerce.entity.User;
import com.india.ecommerce.exception.AccountNumberNotFoundException;
import com.india.ecommerce.exception.InavalidArgumentException;
import com.india.ecommerce.exception.InsufficientBalanceException;
import com.india.ecommerce.feignclient.IciciBankService;
import com.india.ecommerce.repository.OrderHistoryRepository;
import com.india.ecommerce.repository.OrderRespository;
import com.india.ecommerce.repository.ProductRepository;
import com.india.ecommerce.repository.UserRespository;
import com.india.ecommerce.service.ProductService;
import java.util.Optional;
@Service
public class ProductServiceImpl implements ProductService {
	   private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;	
	@Autowired
	UserRespository userRepository;
	@Autowired
	OrderRespository orderReposiroty;
	@Autowired
	OrderHistoryRepository orderDetailsRepository;
	@Autowired
	IciciBankService iciciBankService;
	
	@Override
	public List<ProductResponseDto> searchProduct(ProductSearchDto productSearchDto) throws InavalidArgumentException {
					
		List<ProductResponseDto> productResponseDto=new ArrayList<ProductResponseDto>();
		List<Product> productlist=new ArrayList<Product>();
		
		
		String productName=productSearchDto.getProductName();
		String category=productSearchDto.getCategoryName();
		
		if(StringUtils.isBlank(productName) && StringUtils.isBlank(category)) {
			throw new InavalidArgumentException("Invalid Input");
		}
		if(productName!=null || category!=null )
		{	
			logger.info("enter block");
			productlist= productRepository.findByProductNameContainsOrCategoryContains(productName,category);
					
			for(Product productDetails : productlist) 
			{ 
				ProductResponseDto productResDto=new ProductResponseDto(); 
				productResDto.setProductName(productDetails.getProductName());
				productResDto.setProductCategory(productDetails.getCategory().getName());
				productResDto.setProductPrice(productDetails.getProductPrice());
				productResDto.setQuantity(productDetails.getQuantity());
				productResponseDto.add(productResDto); 
				
			} 
		}
		else
		{
			logger.info("Inavalid input");
			throw new InavalidArgumentException("Inavalid input");
		}
		return productResponseDto;
		
	}
	
	@Override
	public boolean purchaseProduct(BuyProductRequestDto buyproductDto) throws AccountNumberNotFoundException, InsufficientBalanceException {

		List<BuyProductDto> products = buyproductDto.getProducts();
		AccountDetailsDto accountDetails = iciciBankService.getAccountDetails(buyproductDto.getAccountNo());
		Optional<User> user = userRepository.findById(buyproductDto.getUserId());

		Date date = new Date();
		if (!user.isPresent()) {
			logger.info("User Not found");
			throw new AccountNumberNotFoundException("User Not found");
		}
		if (accountDetails == null) {
			logger.info("Account number Not found");
			throw new AccountNumberNotFoundException("Please provide valid Account number");
		}

		// get total amount
		double sum = 0;
		for (BuyProductDto productDto : products) {
			Optional<Product> product = productRepository.findById(productDto.getProductId());
			int Quantity = productDto.getQuantity();
			if (product.isPresent()) {
				if (Quantity > 0) {
					if (product.get().getQuantity() >= productDto.getQuantity()) {
						sum = sum + (productDto.getQuantity() * product.get().getProductPrice());
					} else {
						return false;
					}
				} else {
					throw new AccountNumberNotFoundException("Please provide valid quantity");
				}

			}

			else {
				throw new NullPointerException("product id  not found in db");
			}
		}

		// If amount is insufficient then return false
		if (accountDetails.getCurrentbalance() < sum) {
        throw new InsufficientBalanceException("balance not avaialable");
		}

		// saving order details

		Orders order = new Orders();
		if (user.isPresent()) {
			try {
				order.setUser(user.get());
				order.setTotalPrice(sum);

				order.setDatetime(date);
				orderReposiroty.save(order);
			} catch (Exception exception) {
				logger.error("Exception occurred while saveing the order", exception);
				return false;
			}

			for (BuyProductDto productDto : products) {
				Optional<Product> product = productRepository.findById(productDto.getProductId());
				if (product.isPresent()) {
					try {
						OrderDetails orderDetails = new OrderDetails();
						orderDetails.setOrders(order);
						orderDetails.setQuantity(productDto.getQuantity());
						orderDetails.setPrice(productDto.getQuantity() * product.get().getProductPrice());
						orderDetails.setProduct(product.get());
						orderDetailsRepository.save(orderDetails);
					} catch (Exception exception) {
						logger.error("Exception occurred while saveing the order", exception);
					}
				}

			}
			// Transaction amount
			try {
				FundTransferRequestDto transaction = new FundTransferRequestDto();
				transaction.setFromAccount(buyproductDto.getAccountNo());
				transaction.setAmount(sum);
				transaction.setToAccount(890497226);// set the default to account number
				iciciBankService.sendMoney(transaction);
			} catch (Exception exception) {
				logger.error("Bank details is not available", exception);
				return false;
			}

		} else {
			return false;

		}
		return true;
	}

}

	/*@Override
   public boolean buyProduct(BuyProductRequestDto buyproductDto) throws Exception {
	   OrderDetails orderItem=new  OrderDetails();
	   Orders orders=new Orders();
		List<BuyProductDto> products=buyproductDto.getProducts();
		double sum=0;
         
		System.out.println("details of account"+iciciBankService.getAccountDetails(buyproductDto.getAccountNo()));
		AccountDetailsDto accountDetails=iciciBankService.getAccountDetails(buyproductDto.getAccountNo());
		if(accountDetails!=null)
		{
			
			
		if(accountDetails.getCurrentbalance()<sum) {
			return false;
		}
		if(products!=null) {
			for(BuyProductDto productDto:products) {
				Optional<Product> product1=productRepository.findById(productDto.getProductId());
				if(product1.isPresent())
				{
				Product product=product1.get();
				logger.info("productid present");
				if(product.getQuantity()>=productDto.getQuantity()) {
					sum=sum+(productDto.getQuantity()*product.getProductPrice());
				}else {
					return false;
				}
				}
				else 
				{
					throw new NullPointerException("productid not found");
				}
			}
			Optional<User> user1=userRepository.findByUserId(buyproductDto.getUserId());
			if(user1.isPresent())
			{
				User user=user1.get();
			orders.setUser(user);
			orders.setTotalPrice(sum);
			Date date = new Date();
			orders.setDatetime(date);
			orderReposiroty.save(orders);
			}
			else if(user1.isPresent()==false)
			{
				throw new NullPointerException("userid not found");
			}
			
			for(BuyProductDto productDto:products) {
				Optional<Product> product1=productRepository.findById(productDto.getProductId());
				if(product1.isPresent())
				{
				Product product=product1.get();
				orderItem.setOrders(orders);
				orderItem.setQuantity(productDto.getQuantity());
				orderItem.setPrice(productDto.getQuantity()*product.getProductPrice());
				orderItem.setProduct(product);
				orderDetailsRepository.save(orderItem);
				}
				else
				{
					throw new NullPointerException("productid not found");
				}
			}
			FundTransferRequestDto transaction=new FundTransferRequestDto();
			transaction.setFromAccount(buyproductDto.getAccountNo());
			transaction.setAmount(sum);
			transaction.setToAccount(890497226L);//set the default to account number
			iciciBankService.sendMoney(transaction);
			
		}
		}
		else if(accountDetails==null)
		{
			logger.info("Account number Not found");
			throw new NullPointerException("Account number Not found");
		}
		
		return true;
		
	}
}

		
	
*/