package com.example.WatchShop1.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Received")
public class ReceivedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "received_id")
	private Integer received_id;
	
	@Column(name = "received_date")
	private Date received_date;
	@Column(name = "received_money")
	private Integer received_money;
	
	@ManyToOne
	@JoinColumn(name = "account_id")
	private AccountEntity accountEntity;
	
	public ReceivedEntity() {
		super();
	}

	public ReceivedEntity(Integer received_id, Date received_date, Integer received_money,
			AccountEntity accountEntity) {
		super();
		this.received_id = received_id;
		this.received_date = received_date;
		this.received_money = received_money;
		this.accountEntity = accountEntity;
	}

	public Integer getReceived_id() {
		return received_id;
	}

	public void setReceived_id(Integer received_id) {
		this.received_id = received_id;
	}

	public Date getReceived_date() {
		return received_date;
	}

	public void setReceived_date(Date received_date) {
		this.received_date = received_date;
	}

	public Integer getReceived_money() {
		return received_money;
	}

	public void setReceived_money(Integer received_money) {
		this.received_money = received_money;
	}

	public AccountEntity getAccountEntity() {
		return accountEntity;
	}

	public void setAccountEntity(AccountEntity accountEntity) {
		this.accountEntity = accountEntity;
	}
	
	
}
