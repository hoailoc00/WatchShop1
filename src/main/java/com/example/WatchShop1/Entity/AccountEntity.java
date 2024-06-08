package com.example.WatchShop1.Entity;

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
@Table(name = "Account")
public class AccountEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer account_id;
	@Column(name = "account_name")
	private String username;
	@Column(name = "account_password")
	private String password;
	@Column(name = "customer_name")
	private String customer_name;
	@Column(name = "customer_phone")
	private String customer_phone;
	@Column(name = "customer_address")
	private String customer_address;
	@Column(name = "customer_email")
	private String customer_email;
	@ManyToOne
	@JoinColumn(name = "permission_id")
	private PermissionEntity permission;
	@OneToMany(mappedBy = "customer_id")
	private Set<OrderEntity> order;
	@Column(name="is_deleted")
	private int Is_deleted;
	
	public int getIs_deleted() {
		return Is_deleted;
	}

	public void setIs_deleted(int is_deleted) {
		Is_deleted = is_deleted;
	}

	public Set<OrderEntity> getOrder() {
		return order;
	}

	public void setOrder(Set<OrderEntity> order) {
		this.order = order;
	}

	public AccountEntity() {
	}

	public Integer getAccount_id() {
		return account_id;
	}

	public void setAccount_id(Integer account_id) {
		this.account_id = account_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PermissionEntity getPermission() {
		return permission;
	}

	public void setPermission(PermissionEntity permission) {
		this.permission = permission;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

}
