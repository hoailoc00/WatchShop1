package com.example.WatchShop1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.WatchShop1.Entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer> {
	@Query(value = "Delete from cart where account_id= ?1", nativeQuery = true)
	Boolean delProductCart(int account_id);

	@Query(value = "Select * from cart where account_id= ?1", nativeQuery = true)
	List<CartEntity> listCart(int account_id);

	@Query(value = "Select sum(product_quantity*p.product_saleprice) from cart as c, product as p where account_id= ?1 and c.product_id=p.product_id ", nativeQuery = true)
	int Total(int account_id);

	@Query(value = "update cart set product_quantity= ?1 where product_id= ?2", nativeQuery = true)
	Boolean updateProductCart(int quantity, int product_id);

	@Query(value = "Select * from cart where product_id= ?1 and account_id= ?2", nativeQuery = true)
	CartEntity findProductFromCart(int product_id, int account_id);
	
	@Query(value = "Delete from cart where account_id= ?1 and product_id= ?2", nativeQuery = true)
	Boolean delOneProductCart(int account_id, int product_id);
}
