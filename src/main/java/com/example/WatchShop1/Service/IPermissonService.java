package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.PermissionEntity;

public interface IPermissonService {
	PermissionEntity findById(int id);
	List<PermissionEntity> getAllRole();
}
