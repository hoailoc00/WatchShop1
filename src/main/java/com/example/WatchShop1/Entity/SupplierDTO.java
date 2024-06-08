package com.example.WatchShop1.Entity;

//import jakarta.validation.constraints.NotEmpty;

public class SupplierDTO {

//	@NotEmpty(message = "Cần điền tên nhà cung cấp")
	private String supplier_name;
	public SupplierDTO() {
		// TODO Auto-generated constructor stub
	}
	public SupplierDTO( String supplier_name) {
		super();
		this.supplier_name = supplier_name;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}

	
}
