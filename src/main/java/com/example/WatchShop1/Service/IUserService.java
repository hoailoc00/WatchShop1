package com.example.WatchShop1.Service;

import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.AccountEntity;
@Service
public interface IUserService{
	AccountEntity findByUsername(String username);
	

}
