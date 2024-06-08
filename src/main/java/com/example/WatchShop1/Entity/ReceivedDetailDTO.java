package com.example.WatchShop1.Entity;

public class ReceivedDetailDTO {
	private int receiveddetail_id;
	
	private int received_id;

	private String product_name;

	private int product_quantity;

	private int product_inprice;

	public ReceivedDetailDTO() {
		super();
	}

	public ReceivedDetailDTO(int receiveddetail_id, int received_id, String product_name, int product_quantity,
			int product_inprice) {
		super();
		this.receiveddetail_id = receiveddetail_id;
		this.received_id = received_id;
		this.product_name = product_name;
		this.product_quantity = product_quantity;
		this.product_inprice = product_inprice;
	}

	public int getReceiveddetail_id() {
		return receiveddetail_id;
	}

	public void setReceiveddetail_id(int receiveddetail_id) {
		this.receiveddetail_id = receiveddetail_id;
	}

	public int getReceived_id() {
		return received_id;
	}

	public void setReceived_id(int received_id) {
		this.received_id = received_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}

	public int getProduct_inprice() {
		return product_inprice;
	}

	public void setProduct_inprice(int product_inprice) {
		this.product_inprice = product_inprice;
	}

	

	

	
}
