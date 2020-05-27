package com.shoppingcart.cartservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "checkout_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "user_id", nullable = false)
	private int uid;
	
	@Column(name = "order_id")
	private int oid;
	
	@Column(name = "order_date")
	private String order_date;
	
	@Column(name = "products")
	private String products;
	
	/*
	 * @Column(name = "pid") private int pid;
	 */
	
	/*
	 * @Column(name = "qty") private int qty;
	 */
	
	@Column(name = "total_price")
	private int price;
	
	@Column(name = "payment_type")
	private String payment_type;
	
	@Column(name = "delivery_add")
	private String del_add;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getDel_add() {
		return del_add;
	}

	public void setDel_add(String del_add) {
		this.del_add = del_add;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	
	public String getProducts() {
		return products;
	}

	public void setProducts(String products) {
		this.products = products;
	}


}
