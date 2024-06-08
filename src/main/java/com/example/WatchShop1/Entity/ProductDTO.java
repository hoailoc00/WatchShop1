package com.example.WatchShop1.Entity;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {

	private int product_id;
	
	private String product_name;

	private int product_inventory;

	private int supplier_id;

	private int product_saleprice;

	private int product_inprice;


	private String product_description;

	private int type_id;
	
	private MultipartFile product_image;
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_inventory() {
		return product_inventory;
	}
	public void setProduct_inventory(int product_inventory) {
		this.product_inventory = product_inventory;
	}
	public int getSupplier_id() {
		return supplier_id;
	}
	public void setSupplier_id(int supplier_id) {
		this.supplier_id = supplier_id;
	}
	public int getProduct_saleprice() {
		return product_saleprice;
	}
	public void setProduct_saleprice(int product_saleprice) {
		this.product_saleprice = product_saleprice;
	}
	public int getProduct_inprice() {
		return product_inprice;
	}
	public void setProduct_inprice(int product_inprice) {
		this.product_inprice = product_inprice;
	}
	public String getProduct_description() {
		return product_description;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public MultipartFile getProduct_image() {
		return product_image;
	}
	public void setProduct_image(MultipartFile product_image) {
		this.product_image = product_image;
	}

	
}
