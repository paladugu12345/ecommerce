package com.india.ecommerce.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.india.ecommerce.entity.OrderDetails;

@Repository
public interface OrderHistoryRepository extends JpaRepository<OrderDetails,Long> {
	

	@Query("select od from OrderDetails od where od.orders.orderId=:orderId")
		List<OrderDetails> findByOrders(long orderId);

	 	 

}
