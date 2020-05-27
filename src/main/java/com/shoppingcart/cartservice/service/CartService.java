package com.shoppingcart.cartservice.service;

import java.util.List;

import com.shoppingcart.cartservice.model.AddToCart;
import com.shoppingcart.cartservice.model.Order;


public interface CartService {

	//Get cart details for User
	List<AddToCart> getCartByUser(int uid);
	
	//Remove product from User's cart
	void RemoveFromCart(int uid, int pid);
	
	//Get All Carts for user
	List<AddToCart> getAllCart();
	
	//Add product to User's cart
	List<AddToCart> addToCart(int uid, int pid, int qty, int price) throws Exception;
	
	//Update quantity of a product in Users cart
	void updateCart(int cid, int pid, int qty,int price) throws Exception;
	
	//Check if amount entered is same a total price in the cart
	Boolean checkTotalAmountForCart(int totalAmount, int uid);
	
	//Get Order history for User
	List<Order> getOrderByUserId(int uid);
	
	//Submit the order
	List<Order> saveOrder(List<Order> tmp) throws Exception;
	//Remove from cart when order is submitted
	void removeAllCartByUser(int uid);
}