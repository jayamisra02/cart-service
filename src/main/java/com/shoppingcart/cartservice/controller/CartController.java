package com.shoppingcart.cartservice.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.cartservice.config.ShoppingConfig;
import com.shoppingcart.cartservice.model.CartItem;
import com.shoppingcart.cartservice.service.CartService;

@RestController
public class CartController {

	@Autowired
	CartService cartService;

	//The user should be able to go back to view the list of products in the cart
	//Params to be passes-User Id
	@GetMapping("/shopping-cart")
	public ResponseEntity<?> getCartByUser(@RequestBody HashMap<String, String> getCartRequest) {

		try {
			String keys[] = { "uid" };
			//Validates if the request has required fields
			ShoppingConfig.validationWithHashMap(keys, getCartRequest);
			int uid = Integer.parseInt(getCartRequest.get("uid"));

			List<CartItem> cart = cartService.getCartByUser(uid);
			return ResponseEntity.ok(cart);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	//The user should be able to add product to their cart
	//Params to be passed- User id, Product id, Quantity, Price
	@PostMapping("/shopping-cart/product")
	public ResponseEntity<?> addProduct(@RequestBody HashMap<String, String> addCartRequest) {

		try {
			String keys[] = { "uid", "pid", "qty", "price" };
			ShoppingConfig.validationWithHashMap(keys, addCartRequest);

			int pid = Integer.parseInt(addCartRequest.get("pid"));
			int uid = Integer.parseInt(addCartRequest.get("uid"));
			int qty = Integer.parseInt(addCartRequest.get("qty"));
			int price = Integer.parseInt(addCartRequest.get("price"));
			List<CartItem> cart = cartService.addToCart(uid, pid, qty, price);
			return ResponseEntity.ok(cart);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	//The user should be able to review their shopping cart to remove the product
	//Params to be passed: User Id, Product id
	@DeleteMapping("/shopping-cart/product")
	public ResponseEntity<?> removeProductfromCart(@RequestBody HashMap<String, String> removeCartRequest) {

		try {
			String keys[] = { "pid", "uid" };
			ShoppingConfig.validationWithHashMap(keys, removeCartRequest);
			int pid = Integer.parseInt(removeCartRequest.get("pid"));
			// int cid = Integer.parseInt(removeCartRequest.get("cid"));
			int uid = Integer.parseInt(removeCartRequest.get("uid"));
			cartService.RemoveFromCart(uid, pid);
			List<CartItem> cart = cartService.getCartByUser(uid);
			return ResponseEntity.ok(cart);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	//The user should be able to review their shopping cart to update the quantity
	//Params to be passed- Product id, User Id, Quantity, Price
	@PutMapping("/shopping-cart/product")
	public ResponseEntity<?> updateProductfromCart(@RequestBody HashMap<String, String> removeCartRequest) {

		try {
			String keys[] = { "pid", "qty", "uid", "price" };
			ShoppingConfig.validationWithHashMap(keys, removeCartRequest);
			int pid = Integer.parseInt(removeCartRequest.get("pid"));
			// int cid = Integer.parseInt(removeCartRequest.get("cid"));
			int qty = Integer.parseInt(removeCartRequest.get("qty"));
			int price = Integer.parseInt(removeCartRequest.get("price"));
			int uid = Integer.parseInt(removeCartRequest.get("uid"));
			cartService.updateCart(uid, pid, qty, price);
			List<CartItem> cart = cartService.getCartByUser(uid);
			return ResponseEntity.ok(cart);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

}
