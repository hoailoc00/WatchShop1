package com.example.WatchShop1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.WatchShop1.Entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{
	@Query(value="Select * from account where account_name= ?1",nativeQuery = true)
	AccountEntity findByUsername(String username);
	
	@Modifying
	@Query(value="UPDATE account SET customer_email = ?2, customer_name = ?3, customer_address = ?4, customer_phone = ?5 WHERE account_id = ?1", nativeQuery = true)
    void update(int id, String email, String name, String address, String phone);
	@Query(value= "SELECT * FROM Account a WHERE a.permission_id=3 AND a.is_deleted = 0", nativeQuery = true)
    List<AccountEntity> findEmployees();
	
	@Query(value="SELECT * FROM Account a WHERE a.permission_id=2 AND a.is_deleted = 0", nativeQuery = true)
    List<AccountEntity> findCustomers();
	
	@Query(value="SELECT * FROM Account a WHERE a.permission_id IN (2,3) AND a.is_deleted = 0", nativeQuery = true)
    List<AccountEntity> findAccounts();
	
	@Query(value="SELECT * FROM Account a WHERE a.is_deleted = 1", nativeQuery = true)
	List<AccountEntity> findBanAccounts();
}
