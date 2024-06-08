package com.example.WatchShop1.Service.iml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WatchShop1.Entity.ProductTypeEntity;
import com.example.WatchShop1.Repository.ProductTypeRepository;
import com.example.WatchShop1.Service.IProductTypeService;

@Service
public class ProductTypeService implements IProductTypeService{
	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Override
	public ProductTypeEntity findByName(int typeId) {
		return this.productTypeRepository.findById(typeId);
	}
	@Override
	public List<ProductTypeEntity> getAllProductTypes() {
		// TODO Auto-generated method stub
		return productTypeRepository.findAll();
	}
	@Override
	public ProductTypeEntity createProductType(ProductTypeEntity productType) {
		// TODO Auto-generated method stub
		return productTypeRepository.save(productType);
	}

}
