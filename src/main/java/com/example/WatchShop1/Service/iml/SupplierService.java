package com.example.WatchShop1.Service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.SupplierEntity;
import com.example.WatchShop1.Repository.SupplierRepository;
import com.example.WatchShop1.Service.ISupplierService;

@Service
public class SupplierService implements ISupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	@Override
	public List<SupplierEntity> getAllSuppliers() {
		// TODO Auto-generated method stub
		return supplierRepository.findAll();
	}
	@Override
	public SupplierEntity creatSupplier(SupplierEntity supplier) {
		// TODO Auto-generated method stub
		return supplierRepository.save(supplier);
	}
	

}
