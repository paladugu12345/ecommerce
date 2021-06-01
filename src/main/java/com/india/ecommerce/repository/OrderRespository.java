package com.india.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.india.ecommerce.entity.Orders;
@Repository
public interface OrderRespository extends JpaRepository<Orders,Long> {
	@Query("select od from Orders od where od.user=:userId")
	List<Orders> findByUser(long userId);

}
