package com.example.pruebaalianza2.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

	private static final long serialVersionUID = 7593365997413221510L;

	@Id
	@Column(name = "shared_key")
	private String sharedKey;

	@Column(name = "business_id")
	private String businessId;

	@Column
	private String email;

	@Column
	private String phone;

	@Column(name = "data_added")
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dataAdded;

	@PrePersist
	public void prePersist() {
		this.dataAdded = new Date();
	}

	public String getSharedKey() {
		return sharedKey;
	}

	public void setSharedKey(String sharedKey) {
		this.sharedKey = sharedKey;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String bussinessId) {
		this.businessId = bussinessId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDataAdded() {
		return dataAdded;
	}

	public void setDataAdded(Date dataAdded) {
		this.dataAdded = dataAdded;
	}

}
