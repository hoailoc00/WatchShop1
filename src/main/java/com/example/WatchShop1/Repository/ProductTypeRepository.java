package com.example.WatchShop1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.WatchShop1.Entity.ProductTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Integer>{
	ProductTypeEntity findById(int id);
}
