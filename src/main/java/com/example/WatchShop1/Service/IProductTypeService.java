package com.example.WatchShop1.Service;

import java.util.List;

import com.example.WatchShop1.Entity.ProductTypeEntity;

public interface IProductTypeService {
	ProductTypeEntity findByName(int type);
	List<ProductTypeEntity> getAllProductTypes();
	ProductTypeEntity createProductType(ProductTypeEntity productType);
}
