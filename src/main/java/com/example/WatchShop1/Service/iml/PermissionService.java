package com.example.WatchShop1.Service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.PermissionEntity;
import com.example.WatchShop1.Repository.PermissionRepository;
import com.example.WatchShop1.Service.IPermissonService;

@Service
public class PermissionService implements IPermissonService {
	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public PermissionEntity findById(int id) {
		return this.permissionRepository.findById(id).get();
	}
	@Override
	public List<PermissionEntity> getAllRole() {
		// TODO Auto-generated method stub
		return permissionRepository.findAll();
	}

}
