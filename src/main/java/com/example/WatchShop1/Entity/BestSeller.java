package com.example.WatchShop1.Entity;

public class BestSeller {

	public int productID;
	public String productName;
	public int productAmount;
	public int productTurnover;
	public BestSeller() {
		// TODO Auto-generated constructor stub
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	public int getProductTurnover() {
		return productTurnover;
	}
	public void setProductTurnover(int productTurnover) {
		this.productTurnover = productTurnover;
	}
	@Override
	public String toString() {
		return "BestSeller [productID=" + productID + ", productName=" + productName + ", productAmount="
				+ productAmount + ", productTurnover=" + productTurnover + "]";
	}

	
	
}
