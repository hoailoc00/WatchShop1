package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.OrderDetailEntity;

public interface IOrderDetailService {
	Boolean AddtoOrderDetail(OrderDetailEntity orderDetailEntity);
	List<OrderDetailEntity> getByOrderId(int order_id);
	OrderDetailEntity getOneByOrderID(int order_id);
	List<OrderDetailEntity> getAllOrderDetails();
	List<OrderDetailEntity> findAllByOrderId(int orderId);
	
}
