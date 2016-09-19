package com.library.entity;

public class Borrower implements BaseEntity{
	private int cardNo;
	private String name;
	private String address;
	private String phone;
	
	public Borrower(){}
	
	public Borrower(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public int getCardNo() {
		return cardNo;
	}
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
