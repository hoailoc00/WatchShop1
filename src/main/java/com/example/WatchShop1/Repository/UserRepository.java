package com.example.WatchShop1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WatchShop1.Entity.AccountEntity;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findByUsername(String username);
	
}