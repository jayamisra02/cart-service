package com.shoppingcart.cartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shoppingcart.cartservice.model.Product;

@Service
public class ProductDataService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackProductData")
	public Product getProduct(int pid) {
		Product pr = restTemplate.getForObject("http://localhost:9090/getProductbyPid/"+pid, Product.class);
		return pr;
	}
	
	public Product getFallbackProductData(int pid) {
		Product product = new Product();
		product.setPname("Not Available");
		return product;
	}
	

}
