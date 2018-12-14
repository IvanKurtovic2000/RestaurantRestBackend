package com.restaurant.model;

import java.sql.Timestamp;

public class Product {
	
	private int id;
	private String name;
	private String description;
	private Long price;
	private String category;
	private Timestamp creationdate;
	
	/*
	 * enum Category { VORSPEISE, HAUPTGERICHT, DESSERT }
	 * 
	 */
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Timestamp getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}

	

	
	
	
	
	
	
	
	
	/*
	 
	  
	private int customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zipCode;
	private int isActive;
	
	
	@JsonProperty(value = "customer-id")
	public int getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	@JsonProperty(value = "first-name")
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@JsonProperty(value = "last-name")
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@JsonProperty(value = "address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@JsonProperty(value = "city")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@JsonProperty(value = "state")
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	@JsonProperty(value = "zip-code")
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@JsonProperty(value = "is-active")
	public int getIsActive() {
		return isActive;
	}
	
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", isActive="
				+ isActive + "]";
	}
	
	*/
}
