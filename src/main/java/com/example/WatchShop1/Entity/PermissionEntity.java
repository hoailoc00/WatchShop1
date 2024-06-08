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
@Table(name = "Account_permission")
public class PermissionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "permission_id")
	private Integer permission_id;
	@Column(name = "permission_name")
	private String name;
	@OneToMany(mappedBy = "permission")
	private Set<AccountEntity> User;

	public PermissionEntity() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PermissionEntity(Integer permission_id, String name, Set<AccountEntity> user) {
		super();
		this.permission_id = permission_id;
		this.name = name;
		User = user;
	}

	public Integer getPermission_id() {
		return permission_id;
	}

	public void setPermission_id(int i) {
		this.permission_id = i;
	}

	public Set<AccountEntity> getUser() {
		return User;
	}

	public void setUser(Set<AccountEntity> user) {
		User = user;
	}

	public void setPermission_id(Integer permission_id) {
		this.permission_id = permission_id;
	}

}
