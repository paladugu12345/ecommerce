package com.india.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.india.ecommerce.controller.EcommerceApplicationController;
import com.india.ecommerce.dto.BuyProductDto;
import com.india.ecommerce.dto.BuyProductRequestDto;
import com.india.ecommerce.dto.FundTransferRequestDto;
import com.india.ecommerce.dto.OrderHistoryResponseDto;
import com.india.ecommerce.dto.OrderRequestDto;
import com.india.ecommerce.dto.ProductDto;
import com.india.ecommerce.dto.ProductResponseDto;
import com.india.ecommerce.dto.ProductSearchDto;
import com.india.ecommerce.service.OrderHistoryService;
import com.india.ecommerce.service.ProductService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class EcommerceApplicationTests {
    @Mock
	OrderHistoryService orderHistoryService;
    @Mock
    ProductService productService;
    @InjectMocks
    EcommerceApplicationController ecommerceApplicationController;
    
    static ProductSearchDto productSearchDto;
	static ProductResponseDto productResponseDto;
	static OrderRequestDto orderRequestDto;
	static OrderHistoryResponseDto orderHistoryResponseDto;
	static List<ProductDto> productDto;
    static BuyProductDto buyProductDto;
    static BuyProductRequestDto buyProductRequestDto;
    static BuyProductRequestDto buyProductRequestDto2;
    static FundTransferRequestDto fundTransferRequestDto;
   static List<ProductResponseDto> productResponseDtolist=new ArrayList<ProductResponseDto>();
   static List<OrderHistoryResponseDto> orderHistoryResponseDtolist=new ArrayList<OrderHistoryResponseDto>();

    
    @BeforeAll
	public static void setup() {
    	productSearchDto =new ProductSearchDto();
    	productSearchDto.setCategoryName("mobile");
    	productSearchDto.setProductName("electornic");
    	productResponseDto=new ProductResponseDto();
    	productResponseDto.setProductName("mobile");
    	productResponseDto.setProductPrice(100);
    	productResponseDto.setQuantity(1);
    	productResponseDto.setProductCategory("electornic");
    	productResponseDtolist.add(productResponseDto);
    	buyProductDto =new  BuyProductDto();
    	List<BuyProductDto> buyProductDtolist=new ArrayList<BuyProductDto>();
    	buyProductDto.setProductId(101l);
    	buyProductDto.setQuantity(1);
    	buyProductRequestDto=new BuyProductRequestDto();
    	buyProductRequestDto.setAccountNo(373344426l);
    	buyProductRequestDto.setProducts(buyProductDtolist);
    	buyProductRequestDto.setUserId(1l);
    	
    	buyProductRequestDto2=new BuyProductRequestDto();
    	buyProductRequestDto2.setAccountNo(373344427l);
    	buyProductRequestDto2.setProducts(buyProductDtolist);
    	buyProductRequestDto2.setUserId(1l);
    	orderHistoryResponseDto=new OrderHistoryResponseDto();
    	orderHistoryResponseDto.setOrderId(41);
    	Date date=new Date();
    	date.setDate(26);
    	date.setMonth(05);
    	date.setYear(2021);
    	orderHistoryResponseDto.setOrderDate(date);
    	orderHistoryResponseDto.setTotalPrice(200);
    	ProductDto productDto=new ProductDto();
    	productDto.setProductName("mobile");
    	productDto.setQuantity(10);
    	productDto.setUnitPrice(100);
    	List<ProductDto> ProductDtolist=new ArrayList<ProductDto>();
    	ProductDtolist.add(productDto);
    	//orderHistoryResponseDto.setProductdto(ProductDtolist);
    	orderHistoryResponseDto.setProductdto(ProductDtolist);
    	//orderHistoryResponseDto.setOrderDate(orderDate);
    	orderHistoryResponseDtolist.add(orderHistoryResponseDto);
	}
    @Test
	@DisplayName("searchProduct")
	public void searchProductTest() throws Exception {
		when(productService.searchProduct(productSearchDto)).thenReturn(productResponseDtolist);
		 ResponseEntity<List<ProductResponseDto>> result = ecommerceApplicationController.searchProduct(productSearchDto);
    	assertEquals(productResponseDtolist, result.getBody());
	}
    
    @Test
   	@DisplayName("orderhistory")
   	public void getorderdetailsTest() throws Exception {
    	int userid=1;
   		when(orderHistoryService.getOrderDetails(userid)).thenReturn(orderHistoryResponseDtolist);
   		 List<OrderHistoryResponseDto> result = ecommerceApplicationController.getorderdetails(userid);
       	assertEquals(orderHistoryResponseDtolist, result);
   	}
   
    @Test
   	@DisplayName("buyProducts")
   	public void buyProductsTest() throws Exception {
    	
    	when(productService.purchaseProduct(buyProductRequestDto)).thenReturn(true);
		 String result = ecommerceApplicationController.purchaseProduct(buyProductRequestDto);
   	     assertEquals("order successfully placed", result);
   	}
   
    @Test
   	@DisplayName("buyProducts")
   	public void buyProductsTest2() throws Exception {
    	
    	 when(productService.purchaseProduct(buyProductRequestDto2)).thenReturn(false);
		 String result = ecommerceApplicationController.purchaseProduct(buyProductRequestDto2);
   	     assertEquals("issue while placing the order", result);
   	}
   
}