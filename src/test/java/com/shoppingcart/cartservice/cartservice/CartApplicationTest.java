package com.shoppingcart.cartservice.cartservice;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.shoppingcart.cartservice.model.AddToCart;
import com.shoppingcart.cartservice.model.Product;
import com.shoppingcart.cartservice.service.CartService;

@SpringBootTest
@AutoConfigureMockMvc
public class CartApplicationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean 
	  private CartService cartServiceMock;
	
	
	@Test
	public void getCartByUsershouldReturnCartDetails() throws Exception {
		
		  AddToCart cart = new AddToCart(1,1,500,1);
		  List<AddToCart> listCart = new ArrayList<AddToCart>();
		  listCart.add(cart);
		  
		  //Setup mock
		  when(cartServiceMock.getCartByUser(1)).thenReturn(listCart);
		
		  mockMvc.perform(get("/addtocart/getCartByUserId")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content("{ \"uid\" : \"1\" }") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$[0].uid").value("1")) 
		           .andExpect(jsonPath("$[0].pid").value("1"))
		           .andExpect(jsonPath("$[0].price").value("500")) 
		  			.andExpect(jsonPath("$[0].qty").value("1"));
	}
	
	@Test
	public void getCartByUserDoesNotReturnCartDetails() throws Exception {
		 
		 when(cartServiceMock.getCartByUser(1)).thenReturn(null);
		
		  mockMvc.perform(get("/addtocart/getCartByUserId")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content("{ \"uid\" : \"1\" }") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk());
	}
	
	@Test
	public void addToCartshouldReturnCartDetails() throws Exception {
		
		  AddToCart cart = new AddToCart(1,1,500,1);
		  List<AddToCart> listCart = new ArrayList<AddToCart>();
		  listCart.add(cart);
		  
		  //Setup mock
		 when(cartServiceMock.addToCart(1,1,1,500)).thenReturn(listCart);
		
		  mockMvc.perform(get("/addtocart/addProduct")
		           .contentType(MediaType.APPLICATION_JSON)
		           .content("{ \"uid\" : \"1\" ,\"pid\" : \"1\",\"qty\" : \"1\",\"price\" : \"500\"}") 
		           .accept(MediaType.APPLICATION_JSON))
		           .andExpect(status().isOk())
		           .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		           .andExpect(jsonPath("$[0].uid").value("1")) 
		           .andExpect(jsonPath("$[0].pid").value("1"))
		           .andExpect(jsonPath("$[0].price").value("500")) 
		  			.andExpect(jsonPath("$[0].qty").value("1"));
	}

}
	
