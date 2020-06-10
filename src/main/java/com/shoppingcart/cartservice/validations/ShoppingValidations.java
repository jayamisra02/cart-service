package com.shoppingcart.cartservice.validations;

import java.util.HashMap;

public class ShoppingValidations {
	
public static Boolean validationWithHashMap(String keys[], HashMap<String,String> request) throws Exception{
	Boolean status = false;
	try {
		for(int start = 0;start<keys.length;start++) {
			
			if(request.containsKey(keys[start])) {
				if(request.get(keys[start]).equals("")) {
					throw new Exception(keys[start]+"should not be Empty");
				}
			}else {
				throw new Exception(keys[start]+"is missing");
			}
		}
	}catch(Exception e) {
		throw new Exception("Error is"+e.getMessage());
	}
	return status;
}
}
