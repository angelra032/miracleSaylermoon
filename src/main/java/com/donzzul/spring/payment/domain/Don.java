package com.donzzul.spring.payment.domain;

import java.sql.Date;

public class Don {
	// 돈쭐내역 erd + 주문내역(메뉴, 가격) + 가게?
	
	private int donNo; // 돈쭐번호
	private int donPrice; // 돈쭐가격
	private int userNo; // 회원번호
	
	private String menuName; // 메뉴이름
	private int amount; // 수량
//	private int shopNo; // 가게번호
	private String shopName; // 가게이름
	private Date paymentDate; // 결제날짜
	
	public Don() {}

	public int getDonNo() {
		return donNo;
	}

	public void setDonNo(int donNo) {
		this.donNo = donNo;
	}

	public int getDonPrice() {
		return donPrice;
	}

	public void setDonPrice(int donPrice) {
		this.donPrice = donPrice;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	
}
