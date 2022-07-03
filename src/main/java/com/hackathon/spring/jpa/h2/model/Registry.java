package com.hackathon.spring.jpa.h2.model;

import javax.persistence.*;

@Entity
@Table(name = "registry")
public class Registry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@Column(name = "GovtID")
	private String GovtID;

	@Column(name = "address")
	private String address;

	@Column(name = "registered")
	private boolean registered;

	public Registry() {

	}

	public Registry(String name, String GovtID, String address, boolean registered) {
		this.name = name;
		this.GovtID = GovtID;
		this.registered = registered;
		this.address = address;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGovtID() {
		return GovtID;
	}

	public void setGovtID(String GovtID) {
		this.GovtID = GovtID;
	}

	public boolean isregistered() {
		return registered;
	}

	public void setregistered(boolean isregistered) {
		this.registered = isregistered;
	}

	@Override
	public String toString() {
		return "Registry [id=" + id + ", name=" + name + ", govtID=" + GovtID + ", address=" + address + ", registered="
				+ registered + "]";
	}

}
