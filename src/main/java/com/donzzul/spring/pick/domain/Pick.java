package com.donzzul.spring.pick.domain;

public class Pick {

	private int pickNo;
	private int shopNo;
	private int userNo;
	private String shopName;
	private String shopShortAddr;
	
	public Pick() {}

	public Pick(int pickNo, int shopNo, int userNo, String shopName, String shopShortAddr) {
		super();
		this.pickNo = pickNo;
		this.shopNo = shopNo;
		this.userNo = userNo;
		this.shopName = shopName;
		this.shopShortAddr = shopShortAddr;
	}

	public int getPickNo() {
		return pickNo;
	}

	public void setPickNo(int pickNo) {
		this.pickNo = pickNo;
	}

	public int getShopNo() {
		return shopNo;
	}

	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopShortAddr() {
		return shopShortAddr;
	}

	public void setShopShortAddr(String shopShortAddr) {
		this.shopShortAddr = shopShortAddr;
	}

	@Override
	public String toString() {
		return "Pick [pickNo=" + pickNo + ", shopNo=" + shopNo + ", userNo=" + userNo + ", shopName=" + shopName
				+ ", shopShortAddr=" + shopShortAddr + "]";
	}
	
}
