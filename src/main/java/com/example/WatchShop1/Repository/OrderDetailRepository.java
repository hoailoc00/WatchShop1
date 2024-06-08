package com.example.WatchShop1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.WatchShop1.Entity.OrderDetailEntity;

public interface OrderDetailRepository extends JpaRepository<OrderDetailEntity, Integer>{
	@Query(value="Select * from Order_details where order_id= ?1",nativeQuery = true)
	List<OrderDetailEntity> getAllByOrderId(int id);
	@Query(value="Select Top 1  * from Order_details where order_id= ?1 ORDER BY order_id DESC",nativeQuery = true)
	OrderDetailEntity getOneByOrderID(int order_id);
	@Query(value="SELECT * FROM Order_details od WHERE od.order_id = :orderId",nativeQuery = true)
    List<OrderDetailEntity> findAllByOrderId(@Param("orderId") int orderId);
}
