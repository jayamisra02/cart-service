package com.shoppingcart.cartservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.shoppingcart.cartservice.model.Product;

@Service
public class ProductDataService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackProductData",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
					@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "6"),
					@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
					@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
			}
			)
	public Product getProduct(int pid) {
		Product pr = restTemplate.getForObject("http://product-microservice/getProductbyPid/"+pid, Product.class);
		return pr;
	}
	
	public Product getFallbackProductData(int pid) {
		Product product = new Product();
		product.setPname("Not Available");
		product.setPrice(0);
		product.setQuantity(0);
		return product;
	}
	

}
