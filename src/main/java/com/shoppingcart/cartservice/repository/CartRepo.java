package com.shoppingcart.cartservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.cartservice.model.AddToCart;

@Repository
public interface CartRepo extends JpaRepository<AddToCart, Long> {

	 @Query("Select c from AddToCart c where c.uid =:uid")
	  List<AddToCart> getCartByUser(@Param("uid") int uid);
	 
	 @Query("Select sum(c.price) from AddToCart c where c.uid =:uid")
	  int getTotalAmountByUserId(@Param("uid") int uid);
	 
	 
	 @Query("Select c from AddToCart c where c.uid =:uid and c.pid =:pid")
	 List<AddToCart> getCartByUserandProductId(@Param("uid") int uid, @Param("pid") int pid);
	 
	 @Modifying
	 @Transactional
	 @Query("Delete from AddToCart c where c.uid =:uid and c.pid =:pid ")
	void removefromCart(@Param("uid") int uid, @Param("pid") int pid);
	 
	 @Modifying
	 @Transactional
	 @Query("Delete from AddToCart c where c.uid =:uid")
	void removefromCartByUser(@Param("uid") int uid);
	
	 @Modifying
	 @Transactional
	@Query("Update AddToCart c set qty = :qty,price = :price where c.uid =:uid and c.pid =:pid ")
	void updateCart(@Param("uid") int uid,@Param("pid") int pid,@Param("qty") int qty,@Param("price") int price);

}
