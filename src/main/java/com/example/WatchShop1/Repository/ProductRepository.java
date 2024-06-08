package com.example.WatchShop1.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WatchShop1.Entity.AccountEntity;
import com.example.WatchShop1.Entity.ProductEntity;
import com.example.WatchShop1.Entity.ProductTypeEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
	@Query(value = "SELECT * from product p where p.product_name like %?1%", nativeQuery = true)
	List<ProductEntity> searchProduct(String key);

	Page<ProductEntity> findByTypeId(ProductTypeEntity typeId, Pageable pageable);

	@Query(value = "update product set product_inventory= ?1 where product_id= ?2", nativeQuery = true)
	Boolean updateInventoryProduct(int quantity, int product_id);
	@Query(value ="SELECT * FROM Product p WHERE p.is_deleted = 0", nativeQuery = true)
    List<ProductEntity> findAllProducts();
	
	@Query(value ="SELECT * FROM Product p WHERE p.is_deleted = 0 AND p.product_inventory = 0", nativeQuery = true)
	List<ProductEntity> findSoldOut();
	
	@Query(value="Select * from Product where product_name= ?1",nativeQuery = true)
	ProductEntity findByUsername(String username);
	
	
	@Query(value ="SELECT * FROM Product p WHERE p.is_deleted = 0 AND p.product_inventory <= 5", nativeQuery = true)
	List<ProductEntity> findRunningOutOfStock();
}
