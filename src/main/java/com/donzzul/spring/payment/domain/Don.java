package com.donzzul.spring.payment.domain;

import java.sql.Date;

public class Don {
	// 돈쭐내역 erd + 주문내역(메뉴, 가격) + 가게?
	
	private int donNo; // 돈쭐번호
	private int donPrice; // 돈쭐가격
	private int userNo; // 회원번호
	
	private String menuName; // 메뉴이름
	private int amount; // 수량
	private Date paymentDate; // 결제날짜
	private int shopNo; // 가게번호
	private String shopName; // 가게이름
	
	// point
	private int usePoint; // 사용할 포인트
	private double savePoint; // 적립할 포인트
	private String rouletteYN; // 룰렛 사용가능 여부 
	
	public Don() {} // 기본 생성자
	
	public Don(String string, String string2, int i, int j) {}

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

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getUsePoint() {
		return usePoint;
	}

	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}

	public double getSavePoint() {
		return savePoint;
	}

	public void setSavePoint(double savePoint) {
		this.savePoint = savePoint;
	}

	public String getRouletteYN() {
		return rouletteYN;
	}

	public void setRouletteYN(String rouletteYN) {
		this.rouletteYN = rouletteYN;
	}

	@Override
	public String toString() {
		return "Don [donNo=" + donNo + ", donPrice=" + donPrice + ", userNo=" + userNo + ", menuName=" + menuName
				+ ", amount=" + amount + ", paymentDate=" + paymentDate + ", shopNo=" + shopNo + ", shopName="
				+ shopName + ", usePoint=" + usePoint + ", savePoint=" + savePoint + ", rouletteYN=" + rouletteYN + "]";
	}

	
}
