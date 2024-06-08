package com.example.WatchShop1.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Received_details")
public class ReceivedDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "receiveddetails_id")
	private Integer receiveddetails_id;
	
	@Column(name = "product_quantity")
	private Integer product_quantity;
	@Column(name = "product_inprice")
	private Integer product_inprice;
	
	@ManyToOne
	@JoinColumn(name = "received_id")
	private ReceivedEntity receivedEntity;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product_received;
	public ReceivedDetailEntity() {
		super();
	}
	public ReceivedDetailEntity(Integer receiveddetails_id, Integer product_quantity, Integer product_inprice,
			ReceivedEntity receivedEntity, ProductEntity product_received) {
		super();
		this.receiveddetails_id = receiveddetails_id;
		this.product_quantity = product_quantity;
		this.product_inprice = product_inprice;
		this.receivedEntity = receivedEntity;
		this.product_received = product_received;
	}
	public Integer getReceiveddetails_id() {
		return receiveddetails_id;
	}
	public void setReceiveddetails_id(Integer receiveddetails_id) {
		this.receiveddetails_id = receiveddetails_id;
	}
	public Integer getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}
	public Integer getProduct_inprice() {
		return product_inprice;
	}
	public void setProduct_inprice(Integer product_inprice) {
		this.product_inprice = product_inprice;
	}
	public ReceivedEntity getReceivedEntity() {
		return receivedEntity;
	}
	public void setReceivedEntity(ReceivedEntity receivedEntity) {
		this.receivedEntity = receivedEntity;
	}
	public ProductEntity getProduct_received() {
		return product_received;
	}
	public void setProduct_received(ProductEntity product_received) {
		this.product_received = product_received;
	}
	
	
	
	
	
}
