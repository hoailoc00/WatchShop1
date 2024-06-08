package com.example.WatchShop1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WatchShop1.Entity.ReceivedDetailEntity;

public interface ReceivedDetailRepository extends JpaRepository<ReceivedDetailEntity, Integer> {
	@Query(value="Select * from Received_details p where p.received_id= ?1",nativeQuery = true)
	List<ReceivedDetailEntity> findAllById(int received_id);
	
}
