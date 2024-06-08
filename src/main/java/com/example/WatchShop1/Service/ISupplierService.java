package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.SupplierEntity;

public interface ISupplierService {
	List<SupplierEntity> getAllSuppliers();
	SupplierEntity creatSupplier(SupplierEntity supplier);
}
