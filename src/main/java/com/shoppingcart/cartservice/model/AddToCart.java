package com.shoppingcart.cartservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "addtocart")
public class AddToCart {
	
	public AddToCart(int uid, int pid, int price, int qty) {
		super();
		this.uid = uid;
		this.pid = pid;
		this.price = price;
		this.qty = qty;
	}
	
	public AddToCart() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "cid", nullable = false)
	private int cid;
	
	@Column(name = "uid", nullable = false)
	private int uid;
	
	
	@Column(name = "pid", nullable = false)
	private int pid;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "qty")
	private int qty;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
