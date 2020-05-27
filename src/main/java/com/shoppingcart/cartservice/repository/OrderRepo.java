package com.shoppingcart.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shoppingcart.cartservice.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

	@Query("Select order from Order order where order.uid =:uid")
	  List<Order> getOrderByUser(@Param("uid") int uid);
	
}
