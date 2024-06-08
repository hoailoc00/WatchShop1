package com.example.WatchShop1.Entity;

import java.util.Date;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer product_id;
	@Column(name = "product_name")
	private String product_name;
	@Column(name = "product_inventory")
	private int product_inventory;
	@Column(name = "product_warranty")
	private Date product_warranty;
	@Column(name = "product_image")
	private String product_image;
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplierEntity;
	@Column(name = "product_saleprice")
	private int product_saleprice;
	@Column(name = "product_inprice")
	private int product_inprice;
	@Column(name = "product_description")
	private String product_description;
	@ManyToOne
	@JoinColumn(name = "type_id")
	private ProductTypeEntity typeId;
	@Column(name = "is_deleted")
	private int is_deleted;
	@OneToMany(mappedBy = "product")
	private Set<CartEntity> carts;
	@OneToMany(mappedBy = "product_order")
	private Set<OrderDetailEntity> orders;

	public Set<CartEntity> getCarts() {
		return carts;
	}

	public void setCarts(Set<CartEntity> carts) {
		this.carts = carts;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public SupplierEntity getSupplierEntity() {
		return supplierEntity;
	}

	public void setSupplierEntity(SupplierEntity supplierEntity) {
		this.supplierEntity = supplierEntity;
	}

	public ProductTypeEntity getTypeId() {
		return typeId;
	}

	public void setTypeId(ProductTypeEntity typeId) {
		this.typeId = typeId;
	}

	public String getProduct_name() {
		return product_name;
	}

	public String getProduct_image() {
		return product_image;
	}

	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getProduct_inventory() {
		return product_inventory;
	}

	public void setProduct_inventory(int product_inventory) {
		this.product_inventory = product_inventory;
	}

	public Date getProduct_warranty() {
		return product_warranty;
	}

	public void setProduct_warranty(Date product_warranty) {
		this.product_warranty = product_warranty;
	}

	public int getProduct_saleprice() {
		return product_saleprice;
	}

	public void setProduct_saleprice(int product_saleprice) {
		this.product_saleprice = product_saleprice;
	}

	public int getProduct_inprice() {
		return product_inprice;
	}

	public void setProduct_inprice(int product_inprice) {
		this.product_inprice = product_inprice;
	}

	public int getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

}
