package com.example.WatchShop1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WatchShop1.Entity.ReceivedEntity;

public interface ReceivedRepository extends JpaRepository<ReceivedEntity, Integer> {
	
}
