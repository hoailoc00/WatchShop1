package com.example.WatchShop1.Entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_type")
public class ProductTypeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer type_id;
	@Column(name = "type_name")
	private String typeName;
	@OneToMany(mappedBy = "typeId")
	private Set<ProductEntity> products;
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public Set<ProductEntity> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductEntity> products) {
		this.products = products;
	}
	
}
