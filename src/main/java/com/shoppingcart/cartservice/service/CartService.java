package com.shoppingcart.cartservice.service;

import java.util.List;

import com.shoppingcart.cartservice.model.AddToCart;
import com.shoppingcart.cartservice.model.Order;


public interface CartService {

	List<AddToCart> getCartByUser(int uid);
	void RemoveFromCart(int uid, int pid);
	List<AddToCart> getAllCart();
	List<AddToCart> addToCart(int uid, int pid, int qty, int price) throws Exception;
	void updateCart(int cid, int pid, int qty,int price) throws Exception;
	
	Boolean checkTotalAmountForCart(int totalAmount, int uid);
	
	List<Order> getOrderByUserId(int uid);
	
	List<Order> saveOrder(List<Order> tmp) throws Exception;
	void removeAllCartByUser(int uid);
}