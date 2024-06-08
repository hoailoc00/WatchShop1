package com.example.WatchShop1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "order_details")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer OrderDetail_id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity orders;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product_order;
	@Column(name = "product_quantity")
	private Integer product_quantity;

	public OrderDetailEntity() {
		super();
	}

	public OrderDetailEntity(Integer orderDetail_id, OrderEntity orders, ProductEntity product_order,
			Integer product_quantity) {
		super();
		OrderDetail_id = orderDetail_id;
		this.orders = orders;
		this.product_order = product_order;
		this.product_quantity = product_quantity;
	}

	public Integer getOrderDetail_id() {
		return OrderDetail_id;
	}

	public void setOrderDetail_id(Integer orderDetail_id) {
		OrderDetail_id = orderDetail_id;
	}

	public OrderEntity getOrders() {
		return orders;
	}

	public void setOrders(OrderEntity orders) {
		this.orders = orders;
	}

	public ProductEntity getProduct_order() {
		return product_order;
	}

	public void setProduct_order(ProductEntity product_order) {
		this.product_order = product_order;
	}

	public Integer getProduct_quantity() {
		return product_quantity;
	}

	public void setProduct_quantity(Integer product_quantity) {
		this.product_quantity = product_quantity;
	}

}
