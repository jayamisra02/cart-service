# cart-service

Handles following REST based requests:

1) Add product to cart- /addProduct {"user_id","product_id","quantity","price"}
2) Remove product from Cart- /removeProduct {"user_id","product_id"}
3) Update Quantity of Product-/updateProduct {"user_id","product_id","new_quantity","price"}
4) get Cart information for User- /getCartByUserId {"user_id"}
5) Checkout order for User- /checkoutOrder {"user_id","payment_type","delivery_address","total_price"}
6) Fetch Order history- /getOrderByUserId {"user_id"}
