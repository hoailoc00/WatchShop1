package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.OrderEntity;

public interface IOrderService {
	Boolean AddtoOrder(OrderEntity orderEntity);
	OrderEntity getOneById(int id);
	List<OrderEntity> getAllByAccountId(int account_id);
	List<OrderEntity> getAllOrders();
	Boolean approveOrder(Integer orderId);
}
