package com.shoppingcart.cartservice.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.cartservice.config.ShoppingConfig;
import com.shoppingcart.cartservice.model.AddToCart;
import com.shoppingcart.cartservice.model.Order;
import com.shoppingcart.cartservice.model.Product;
import com.shoppingcart.cartservice.service.CartService;
import com.shoppingcart.cartservice.service.ProductDataService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	CartService cartService;

	@Autowired
	ProductDataService productDataService;

	// To checkout the cart and place the order
	// Params to be passed- User id, Total Price, Payment Type, Delivery Address
	@RequestMapping("/checkoutOrder")
	public ResponseEntity<?> checkoutOrder(@RequestBody HashMap<String, String> checkOutRequest) {

		try {
			String keys[] = { "uid", "total_price", "payment_type", "del_add" };
			ShoppingConfig.validationWithHashMap(keys, checkOutRequest);
			
			int uid = Integer.parseInt(checkOutRequest.get("uid"));

			int total_amt = Integer.parseInt(checkOutRequest.get("total_price"));

			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			Date date = new Date();
			String order_date = formatter.format(date);
			
			//Validate if total price in the request is same as sum total of product's price in the cart
			if (cartService.checkTotalAmountForCart(total_amt, uid)) {
				//Fetches Users cart information
				List<AddToCart> cartItems = cartService.getCartByUser(uid);
				List<Order> tmp = new ArrayList<Order>();
				String productList = "";
				for (AddToCart cart : cartItems) {

					// To check if the product is available in the inventory
					Product product = productDataService.getProduct(cart.getPid());
					productList = productList + "|" + product.getPname() + "-" + cart.getQty();

					/*
					 * if (product.getQuantity() > 0 && product.getQuantity() > cart.getQty()) {
					 * productList = productList + "|" + product.getPname() + "-" + cart.getQty() +
					 * ""; } else { total_amt = total_amt - (cart.getPrice() * cart.getQty()); }
					 */

				}
				int oid = this.getOrderId();
				Order order = new Order();
				order.setDel_add(checkOutRequest.get("del_add"));
				order.setOid(oid);
				order.setProducts(productList);
				order.setPrice(total_amt);
				order.setUid(uid);
				order.setOrder_date(order_date);
				order.setPayment_type(checkOutRequest.get("payment_type"));
				tmp.add(order);

				cartService.saveOrder(tmp);
				return ResponseEntity.ok(cartItems);
			} else {
				throw new Exception("Total Amount Mismatch");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	// Generate 5 digit random number as order id
	public int getOrderId() {
		Random r = new Random(System.currentTimeMillis());
		return 10000 + r.nextInt(20000);
	}

	// To fetch Order history of the User
	@RequestMapping("/getOrderByUserId")
	public ResponseEntity<?> getOrderByUser(@RequestBody HashMap<String, String> getOrderRequest) {

		try {
			String keys[] = { "uid" };
			ShoppingConfig.validationWithHashMap(keys, getOrderRequest);
			int uid = Integer.parseInt(getOrderRequest.get("uid"));

			List<Order> order = cartService.getOrderByUserId(uid);
			return ResponseEntity.ok(order);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
