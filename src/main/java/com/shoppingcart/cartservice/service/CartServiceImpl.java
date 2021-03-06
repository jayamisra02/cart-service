package com.shoppingcart.cartservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.cartservice.model.CartItem;
import com.shoppingcart.cartservice.model.Order;
import com.shoppingcart.cartservice.repository.CartRepo;
import com.shoppingcart.cartservice.repository.OrderRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	CartRepo cartRepo;
	
	@Autowired
	OrderRepo orderRepo;

	@Override
	public List<CartItem> addToCart(int uid, int pid, int qty,int price) throws Exception{
		
		try {
			if(!cartRepo.getCartByUserandProductId(uid, pid).isEmpty()) {
				throw new Exception("Product is already present");
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		CartItem cart = new CartItem();
		cart.setUid(uid);
		cart.setQty(qty);
		cart.setPid(pid);
		cart.setPrice(price);
		cartRepo.save(cart);
		
		return  this.getCartByUser(uid);
	}

	@Override
	public List<CartItem> getCartByUser(int uid) {
		return  cartRepo.getCartByUser(uid);
	}

	@Override
	public void RemoveFromCart(int uid, int pid) {
		cartRepo.removefromCart(uid,pid);
	}

	@Override
	public List<CartItem> getAllCart() {
		return cartRepo.findAll();
	}

	@Override
	public void updateCart(int uid, int pid, int qty,int price) throws Exception {
		cartRepo.updateCart(uid, pid, qty, price);
	}

	@Override
	public Boolean checkTotalAmountForCart(int totalAmount,int uid) {
		int total_amount = cartRepo.getTotalAmountByUserId(uid);
		if(total_amount == totalAmount) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<Order> getOrderByUserId(int uid) {
		return orderRepo.getOrderByUser(uid);
	}

	@Override
	public List<Order> saveOrder(List<Order> tmp) throws Exception {
		
		try {
			
			int uid = tmp.get(0).getUid();
			if(tmp.size() >0) {
			orderRepo.saveAll(tmp);
			this.removeAllCartByUser(uid);
			return this.getOrderByUserId(uid);
			}
			else {
				throw new Exception("Should not be empty");
			}
			
		}catch(Exception e){
			throw new Exception("Error while checkout"+e.getMessage());
		}
		
	}


	@Override
	public void removeAllCartByUser(int uid) {
		cartRepo.removefromCartByUser(uid);
	}

}
